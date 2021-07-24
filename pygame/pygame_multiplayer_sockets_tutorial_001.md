# Pygame multiplayer with sockets tutorial 001

Create a simple `pygame` startup:

`client.py`

    # This is a sample Python script.
    
    import pygame
    import sys
    
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
            self.rect.update(self.x, self.y, self.width, self.height)
    
    
    if __name__ == '__main__':
        
        pygame.init()
        screen = pygame.display.set_mode((WIDTH,HEIGHT), 0, 32)
        pygame.display.set_caption("PyGame")
    
        clock = pygame.time.Clock()
        font = pygame.font.SysFont("Arial", 18)
    
        players = [Player(32, 32, 32, 32, pygame.Color(255, 0, 0))]
    
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
    
            screen.fill((0, 0, 0))
            for player in players:
                player.update_input()
                player.draw(screen)
            screen.blit(fps_text, (10, 10))
            clock.tick(FPS)
    
            pygame.display.update()
    
Create the `server`:

`server.py`

    import socket
    from _thread import *
    
    server = "192.168.1.5"
    port = 5150
    s = None
    
    
    def threaded_client(conn):
        conn.send(str.encode("Connected"))
        reply = ""
        while True:
            try:
                data = conn.recv(2048)
                reply = data.decode("utf-8")
    
                if not data:
                    print("CLIENT::Disconnected")
                    break
                else:
                    print("CLIENT::Received: ", reply)
                    print("CLIENT::Sending : ", reply)
    
                conn.sendall(str.encode(reply))
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
        while True:
            conn, addr = s.accept()
            print("Connected to:", addr)
    
            start_new_thread(threaded_client, (conn,))
    
    
    if __name__ == '__main__':
        init()
        run()

Create `network` module:

`network.py`

    import socket
    
    
    class Network:
        def __init__(self):
            self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.server = "192.168.1.5"
            self.port = 5150
            self.addr = (self.server, self.port)
            self.connect()
            self.id = self.connect()
    
        def connect(self):
            try:
                self.client.connect(self.addr)
                return self.client.recv(2048).decode()
            except:
                pass
    
        def send(self, data):
            try:
                self.client.send(str.encode(data))
                return self.client.recv(2048).decode()
            except socket.error as e:
                print(e)
    
    
    if __name__ == '__main__':
        n = Network()
        print(n.send("Hello"))
        print(n.send("World"))

