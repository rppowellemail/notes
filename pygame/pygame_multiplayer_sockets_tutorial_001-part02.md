# Pygame multiplayer with sockets tutorial 001 part 02

Reference:

* Part 1 - https://www.youtube.com/watch?v=_fx7FQ3SP0U - Python Online Game Tutorial #1 - Creating a Client Using Sockets
* Part 2 - https://www.youtube.com/watch?v=_whymdfq-R4 - Python Online Game Tutorial #2 - Creating a Server
* Part 3 - https://www.youtube.com/watch?v=qbL4hPWcnFM - Python Online Game Tutorial #3 - Sending & Receiving Information From Server
* Part 4 - https://www.youtube.com/watch?v=F257x_E6H4k - Python Online Game Tutorial #4 - Connecting Multiple Clients


Updated files:

* `client.py`
* `network.py`
* `server.py`

----

`client.py`

    # This is a sample Python script.
    
    import pygame
    import sys
    from network import Network
    
    WIDTH = 500
    HEIGHT = 500
    FPS = 30
    
    
    class Player:
    
        def __init__(self, x, y, width, height, color):
            self.x = x
            self.y = y
            self.width = width
            self.height = height
            self.color = color
            self.rect = pygame.Rect(x, y, width, height)
            self.vel = 3
    
        def draw(self, surface):
            pygame.draw.rect(surface, self.color, self.rect)
    
        def update_input(self):
            keys = pygame.key.get_pressed()
            if keys[pygame.K_LEFT]:
                self.x -= self.vel
            if keys[pygame.K_RIGHT]:
                self.x += self.vel
            if keys[pygame.K_UP]:
                self.y -= self.vel
            if keys[pygame.K_DOWN]:
                self.y += self.vel
            self.update()
    
        def update(self):
            self.rect.update(self.x, self.y, self.width, self.height)
    
    
    def decode_pos(str):
        str = str.split(",")
        return int(str[0]), int(str[1])
    
    
    def encode_pos(tup):
        return str(tup[0])+","+str(tup[1])
    
    
    if __name__ == '__main__':
    
        pygame.init()
        screen = pygame.display.set_mode((WIDTH,HEIGHT), 0, 32)
        pygame.display.set_caption("PyGame")
    
        clock = pygame.time.Clock()
        font = pygame.font.SysFont("Arial", 18)
    
        n = Network()
        startPos = decode_pos(n.get_pos())
        print("CLIENT::startingPos:", startPos)
    
        player1 = Player(startPos[0], startPos[1], 32, 32, pygame.Color(255, 0, 0))
        player2 = Player(0, 0, 32, 32, pygame.Color(0, 0, 255))
    
        run = True
        while run:
            fps = str(int(clock.get_fps()))
            fps_text = font.render(fps, True, pygame.Color("coral"))
    
            p2pos = decode_pos(n.send(encode_pos((player1.x, player1.y))))
            player2.x = p2pos[0]
            player2.y = p2pos[1]
    
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
    
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        pygame.quit()
                        sys.exit()
    
            screen.fill((0, 0, 0))
    
            player1.update_input()
            player1.draw(screen)
            player2.update()
            player2.draw(screen)
            screen.blit(fps_text, (10, 10))
    
            clock.tick(FPS)
    
            pygame.display.update()

`network.py`

    import socket
    
    
    class Network:
        def __init__(self):
            self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.server = "192.168.1.5"
            self.port = 5150
            self.addr = (self.server, self.port)
            self.pos = self.connect()
            print("NETWORK::pos:", self.pos)
    
        def connect(self):
            try:
                self.client.connect(self.addr)
                return self.client.recv(2048).decode()
            except:
                pass
    
        def get_pos(self):
            return self.pos
    
        def send(self, data):
            try:
                self.client.send(str.encode(data))
                return self.client.recv(2048).decode()
            except socket.error as e:
                print(e)
    
    
    def decode_pos(str):
        str = str.split(",")
        return int(str[0]), int(str[1])
    
    
    def encode_pos(tup):
        return str(tup[0])+","+str(tup[1])
    
    
    if __name__ == '__main__':
        n = Network()
        print("NETWORK::get_pos: ", n.get_pos())
        print(n.send(encode_pos((0, 0))))

`server.py`

    import socket
    from _thread import *
    
    server = "192.168.1.5"
    port = 5150
    s = None
    
    playerpositions = [(0, 0), (100, 100)]
    numberofplayers = 0
    
    
    def threaded_client(conn, playerid):
        conn.send(
            str.encode(encode_pos(playerpositions[playerid]))
        )
    
        reply = ""
        while True:
            try:
                data = conn.recv(2048)
                received_position = decode_pos(data.decode("utf-8"))
                playerpositions[playerid] = received_position
    
                if not data:
                    print("CLIENT::Disconnected")
                    break
                else:
                    if playerid == 0:
                        reply_position = playerpositions[1]
                    else:
                        reply_position = playerpositions[0]
    
                    reply = encode_pos(reply_position)
                    print("CLIENT::Received: ", playerid, received_position)
                    print("CLIENT::Sending : ", playerid, reply_position)
    
                conn.sendall(str.encode(reply))
    
            except:
                break
    
        print("CLIENT::Lost connection")
        conn.close()
    
    
    def decode_pos(str):
        str = str.split(",")
        return int(str[0]), int(str[1])
    
    
    def encode_pos(tup):
        return str(tup[0])+","+str(tup[1])
    
    
    def init():
        global s
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
        try:
            s.bind((server, port))
    
        except socket.error as e:
            print(str(e))
    
        s.listen()
        print("SERVER::Started - Waiting for a connection")
    
    
    def run():
        global numberofplayers
        while True:
            conn, addr = s.accept()
            print("Connected to:", addr)
    
            start_new_thread(threaded_client, (conn, numberofplayers))
            numberofplayers += 1
    
    
    if __name__ == '__main__':
        init()
        run()
