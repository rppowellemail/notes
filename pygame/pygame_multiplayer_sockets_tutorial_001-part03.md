# Pygame multiplayer with sockets tutorial 001 part 02

Reference:

* Part 1 - https://www.youtube.com/watch?v=_fx7FQ3SP0U - Python Online Game Tutorial #1 - Creating a Client Using Sockets
* Part 2 - https://www.youtube.com/watch?v=_whymdfq-R4 - Python Online Game Tutorial #2 - Creating a Server
* Part 3 - https://www.youtube.com/watch?v=qbL4hPWcnFM - Python Online Game Tutorial #3 - Sending & Receiving Information From Server
* Part 4 - https://www.youtube.com/watch?v=F257x_E6H4k - Python Online Game Tutorial #4 - Connecting Multiple Clients
* Part 5 - https://www.youtube.com/watch?v=UIlO7TbwrBE - Python Online Game Tutorial #5 - Sending Objects With Pickle!

Refactored to use `pickle`:

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
    
    
    if __name__ == '__main__':
    
        pygame.init()
        screen = pygame.display.set_mode((WIDTH,HEIGHT), 0, 32)
        pygame.display.set_caption("PyGame")
    
        clock = pygame.time.Clock()
        font = pygame.font.SysFont("Arial", 18)
        n = Network()
        p1 = n.get_playerdata()
    
        run = True
        while run:
            fps = str(int(clock.get_fps()))
            fps_text = font.render(fps, True, pygame.Color("coral"))
    
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
    
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        pygame.quit()
                        sys.exit()
    
            p1.update_input()
            p1.update()
    
            p2 = n.send(p1)
    
            screen.fill((0, 0, 0))
            p1.draw(screen)
            p2.draw(screen)
    
            screen.blit(fps_text, (10, 10))
            pygame.display.update()
            clock.tick(FPS)
    
`network.py`

    import socket
    import pickle
    
    
    class Network:
        def __init__(self):
            self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.server = "192.168.1.5"
            self.port = 5150
            self.addr = (self.server, self.port)
            self.playerdata = self.connect()
    
        def connect(self):
            try:
                self.client.connect(self.addr)
                return pickle.loads(self.client.recv(2048))
            except:
                pass
    
        def get_playerdata(self):
            return self.playerdata
    
        def send(self, data):
            try:
                self.client.send(pickle.dumps(data))
                return pickle.loads(self.client.recv(2048))
            except socket.error as e:
                print(e)
    
    
    if __name__ == '__main__':
        n = Network()
        print("NETWORK::get_playerdata: ", n.get_playerdata)
    
`server.py`

    import socket
    from _thread import *
    from player import Player
    import pickle
    
    server = "192.168.1.5"
    port = 5150
    s = None
    
    players = [Player(0, 0, 32, 32, (255, 0, 0)), Player(64, 64, 32, 32, (0, 0, 255))]
    numberofplayers = 0
    
    
    def threaded_client(conn, player):
        conn.send(pickle.dumps(players[player]))
    
        reply = None
        while True:
            try:
                data = pickle.loads(conn.recv(2048))
                players[player] = data
    
                if not data:
                    print("CLIENT::Disconnected")
                    break
                else:
                    if player == 0:
                        reply = players[1]
                    else:
                        reply = players[0]
    
                conn.sendall(pickle.dumps(reply))
    
            except:
                break
    
        print("CLIENT::Lost connection")
        conn.close()
    
    
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
    
