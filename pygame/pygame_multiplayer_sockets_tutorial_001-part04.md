# Pygame multiplayer with sockets tutorial 001 part 02

Reference:

* Part 1 - https://www.youtube.com/watch?v=_fx7FQ3SP0U - Python Online Game Tutorial #1 - Creating a Client Using Sockets
* Part 2 - https://www.youtube.com/watch?v=_whymdfq-R4 - Python Online Game Tutorial #2 - Creating a Server
* Part 3 - https://www.youtube.com/watch?v=qbL4hPWcnFM - Python Online Game Tutorial #3 - Sending & Receiving Information From Server
* Part 4 - https://www.youtube.com/watch?v=F257x_E6H4k - Python Online Game Tutorial #4 - Connecting Multiple Clients
* Part 5 - https://www.youtube.com/watch?v=UIlO7TbwrBE - Python Online Game Tutorial #5 - Sending Objects With Pickle!
* Part 6 -
* Part 7 -
* Part 8 -
* Part 9

Re-write for Rock-Paper-Scissors game:

* `client.py`
* `network.py`
* `server.py`

----

`client.py`

    import pygame
    import sys
    from orpsnetwork import Network
    
    WIDTH = 500
    HEIGHT = 500
    FPS = 30
    
    
    class Button:
        def __init__(self, text, x, y, color, font):
            self.text = text
            self.color = color
            self.rect = pygame.Rect(x, y, 128, 64)
            self.font = font
            self.fontsurface = font.render(self.text, True, pygame.Color("white"))
            self.fontx = self.rect.x + ((self.rect.width - self.fontsurface.get_width())/2)
            self.fonty = self.rect.y + ((self.rect.height - self.fontsurface.get_height())/2)
    
        def draw(self, surface):
            pygame.draw.rect(surface, self.color, self.rect)
            surface.blit(self.fontsurface, (self.fontx, self.fonty))
    
        def clicked(self, pos):
            return self.rect.x <= pos[0] <= self.rect.x+self.rect.width and self.rect.y <= pos[1] <= self.rect.y + self.rect.height
    
    
    def redraw():
        screen.fill((128, 128, 128))
    
        if not (game.connected()):
            screen.blit(waitingforplayer, (waitingforplayer_x, 200))
        else:
            screen.blit(yourmove, (yourmove_x, 120))
            screen.blit(opponent, (opponent_x, 120))
    
            move1 = game.get_player_move(0)
            move2 = game.get_player_move(1)
    
            if game.both_went():
                print(f"render:both_went({move1}, {move2})")
                playerstatus1 = playerstatusfont.render(move1, True, (0, 0, 0))
                playerstatus2 = playerstatusfont.render(move2, True, (0, 0, 0))
            else:
                if game.p1Went and playerid == 0:
                    playerstatus1 = playerstatusfont.render(move1, True, (0, 0, 0))
                elif game.p1Went:
                    playerstatus1 = playerstatusfont.render("Locked In", True, (0, 0, 0))
                else:
                    playerstatus1 = playerstatusfont.render("Waiting...", True, (0, 0, 0))
    
                if game.p2Went and playerid == 1:
                    playerstatus2 = playerstatusfont.render(move2, True, (0, 0, 0))
                elif game.p2Went:
                    playerstatus2 = playerstatusfont.render("Locked In", True, (0, 0, 0))
                else:
                    playerstatus2 = playerstatusfont.render("Waiting...", True, (0, 0, 0))
    
            playerstatus1x = 20 + ((((WIDTH - (20 * 3)) // 2) - playerstatus1.get_width()) // 2)
            playerstatus2x = 20 + ((WIDTH - (20 * 3)) // 2) + 20 + ((((WIDTH - (20 * 3)) // 2) - playerstatus2.get_width()) // 2)
    
            if playerid == 0:
                screen.blit(playerstatus1, (playerstatus1x, 220))
                screen.blit(playerstatus2, (playerstatus2x, 220))
            else:
                screen.blit(playerstatus2, (playerstatus1x, 220))
                screen.blit(playerstatus1, (playerstatus2x, 220))
    
            for button in buttons:
                button.draw(screen)
    
        #screen.blit(waitingforplayer, (waitingforplayer_x, 200))
    
        # pygame.draw.rect(screen, (255,0,0), (0, 200, 20, 20))
        # pygame.draw.rect(screen, (128,0,0), (20, 200, ((WIDTH - (20*3))//2), 20))
        # pygame.draw.rect(screen, (0,255,0), ( ((WIDTH - (20*3))//2) + 20, 200, 20, 20) )
        # pygame.draw.rect(screen, (0,128,0), ( ((WIDTH - (20*3))//2) + 40, 200, (WIDTH - (20*3))//2, 20) )
    
        #screen.blit(yourmove, (yourmove_x, 120))
        #screen.blit(opponent, (opponent_x, 120))
    
        #screen.blit(waiting, (waiting1x, 220))
        #screen.blit(waiting, (waiting2x, 220))
    
        #screen.blit(youwon, (
        #    ((screen.get_width() - youwon.get_width()) / 2),
        #    ((screen.get_height() - youwon.get_height()) / 2)
        #))
    
        # pygame.draw.rect(screen, (128,0,0), (20, 200, (WIDTH - (20 * 3) // 2), 20))
    
    
        screen.blit(fps_text, (10, 10))
        pygame.display.update()
    
    
    if __name__ == '__main__':
    
        pygame.init()
        pygame.font.init()
        screen = pygame.display.set_mode((WIDTH, HEIGHT), 0, 32)
        pygame.display.set_caption("PyGame")
    
        clock = pygame.time.Clock()
        n = Network()
        playerid = int(n.get_playerid())
    
        fpsfont = pygame.font.SysFont("Arial", 18)
        bfont = pygame.font.SysFont(pygame.font.get_default_font(), 40)
    
        winningtextfont = pygame.font.SysFont(pygame.font.get_default_font(), 80)
    
        mfont = pygame.font.SysFont(pygame.font.get_default_font(), 60)
        waitingforplayer = mfont.render("WAITING FOR PLAYER", True, pygame.Color("red"))
        waitingforplayer_x = (WIDTH - waitingforplayer.get_width())/2
    
        nfont = pygame.font.SysFont(pygame.font.get_default_font(), 32)
        yourmove = mfont.render("Your Move", True, pygame.Color("cyan"))
        opponent = mfont.render("Opponents", True, pygame.Color("cyan"))
        yourmove_x = 20 + ((((WIDTH - (20 * 3)) // 2) - yourmove.get_width()) // 2)
        opponent_x = 20 + ((WIDTH - (20 * 3)) // 2) + 20 + ((((WIDTH - (20 * 3)) // 2) - opponent.get_width()) // 2)
    
        waitingfont = pygame.font.SysFont(pygame.font.get_default_font(), 42)
        waiting = waitingfont.render("Waiting...", True, pygame.Color("black"))
        waiting1x = 20 + ((((WIDTH - (20 * 3)) // 2) - waiting.get_width()) // 2)
        waiting2x = 20 + ((WIDTH - (20 * 3)) // 2) + 20 + ((((WIDTH - (20 * 3)) // 2) - waiting.get_width()) // 2)
    
        playerstatusfont = pygame.font.SysFont(pygame.font.get_default_font(), 42)
    
        xoffset = (WIDTH - (20 * 2)) // 3
        buttons = [
            Button("Rock",      20, 340, (0, 0, 0), bfont),
            Button("Scissors",  20+xoffset, 340, (255, 0, 0), bfont),
            Button("Paper",     20+(2*xoffset), 340, (0, 255, 0), bfont)
        ]
    
        run = True
        while run:
            fps = str(int(clock.get_fps()))
            fps_text = fpsfont.render(fps, True, pygame.Color("coral"))
    
            try:
                game = n.send("get")
            except:
                run = False
                print("Couldn't get game")
                break
    
            if game.both_went():
                redraw()
                pygame.time.delay(500)
                try:
                    game = n.send("reset")
                except:
                    run = False
                    print("Couldn't send reset")
                    break
    
                if (game.winner() == 1 and playerid == 1) or (game.winner() == 0 and playerid == 0):
                    winningtext = winningtextfont.render("You Won!", True, pygame.Color("yellow"))
                    print("You won!")
                elif game.winner() == -1:
                    winningtext = winningtextfont.render("Its a tie!", True, pygame.Color("yellow"))
                    print("Its a tie")
                else:
                    winningtext = winningtextfont.render("You lost...", True, pygame.Color("yellow"))
                    print("You lost...")
    
                screen.blit(winningtext, (
                        ((screen.get_width() - winningtext.get_width())/2),
                        ((screen.get_height() - winningtext.get_height()) / 2)
                ))
                pygame.display.update()
                pygame.time.delay(2000)
    
            redraw()
    
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
    
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        pygame.quit()
                        sys.exit()
                if event.type == pygame.MOUSEBUTTONDOWN:
                    pos = pygame.mouse.get_pos()
    
                    for button in buttons:
                        if button.clicked(pos) and game.connected():
                            if playerid == 0:
                                if not game.p1Went:
                                    n.send(button.text)
                            else:
                                if not game.p2Went:
                                    n.send(button.text)
            clock.tick(FPS)
    
`orpsgame.py`

    class Game:
        def __init__(self, id):
            self.p1Went = False
            self.p2Went = False
            self.ready = False
            self.id = id
            self.moves = [None, None]
            self.wins = [0, 0]
            self.ties = 0
    
        def get_player_move(self, p):
            """
            :param p:  [0, 1]
            :return: Move
            """
            return self.moves[p]
    
        def play(self, player, move):
            self.moves[player] = move
            if player == 0:
                self.p1Went = True
            else:
                self.p2Went = True
    
        def connected(self):
            return self.ready
    
        def both_went(self):
            return self.p1Went and self.p2Went
    
        def winner(self):
            #if not (self.moves[0] and self.moves[1]):
            #    return -1
    
            p1 = self.moves[0].upper()[0]
            p2 = self.moves[1].upper()[0]
    
            if ((p1 == "R" and p2 == "S") or
                (p1 == "P" and p2 == "R") or
                (p1 == "S" and p2 == "P")):
                    winner = 0
            elif((p1 == "R" and p2 == "P") or
                (p1 == "P" and p2 == "S") or
                (p1 == "S" and p2 == "R")):
                    winner = 1
            elif((p1 == "R" and p2 == "R") or
                 (p1 == "P" and p2 == "P") or
                 (p1 == "S" and p2 == "S")):
                    winner = -1
            else:
                raise Exception(f"Unknown RPS: {p1}, {p2}")
            return winner
    
        def reset_moves(self):
            self.p1Went = False
            self.p2Went = False
    
`orpsnetwork.py`

    import socket
    import pickle
    
    
    class Network:
        def __init__(self):
            self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.server = "192.168.1.5"
            self.port = 5150
            self.addr = (self.server, self.port)
            self.playerid = self.connect()
    
        def connect(self):
            try:
                self.client.connect(self.addr)
                return self.client.recv(2048).decode()
            except:
                pass
    
        def get_playerid(self):
            return self.playerid
    
        def send(self, data):
            try:
                self.client.send(str.encode(data))
                return pickle.loads(self.client.recv(2048))
            except socket.error as e:
                print(e)
    
    
    if __name__ == '__main__':
        n1 = Network()
        print("NETWORK::n1:get_playerid(): ", n1.get_playerid())
        print("NETWORK::n1:send: ", n1.send("get"))
        print("NETWORK::n1:send: ", n1.send("reset"))
        n2 = Network()
        print("NETWORK::n2:get_playerid(): ", n2.get_playerid())
        print("NETWORK::n2:send: ", n2.send("get"))
        print("NETWORK::n2:send: ", n2.send("reset"))
    
`orpsserver.py`

    import socket
    from _thread import *
    from orpsgame import Game
    import pickle
    
    server = "192.168.1.5"
    port = 5150
    listener = None
    
    connected = set()
    games = {}
    idCount = 0
    
    
    def threaded_client(conn, playerid, gameid):
        global idCount
        conn.send(str.encode(str(playerid)))
        reply = ""
    
        while True:
            try:
                data = conn.recv(4096).decode()
    
                if not data:
                    print("CLIENT::Disconnected")
                    break
                elif gameid in games:
                    game = games[gameid]
    
                    if data == "reset":
                        print("CLIENT::reset", gameid)
                        game.reset_moves()
                    elif data != "get":
                        print("CLIENT::play", gameid, playerid, data)
                        game.play(playerid, data)
                    reply = game
                    conn.sendall(pickle.dumps(reply))
                else:
                    raise Exception(f"gameid not found: {gameid}")
    
            except Exception as e:
                print(str(e))
                break
    
        print("CLIENT::Lost connection")
        print("CLIENT::Closing Game", gameid)
        try:
            if gameid in games:
                del games[gameid]
        except Exception as e:
            print(str(e))
        idCount -= 1
        conn.close()
    
    
    def init():
        global listener
        listener = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
        try:
            listener.bind((server, port))
    
        except socket.error as e:
            print(str(e))
    
        listener.listen()
        print("SERVER::Started - Waiting for a connection")
    
    
    def run():
        global idCount
        while True:
            conn, addr = listener.accept()
            print("SERVER::Connected to:", addr)
    
            idCount += 1
            p = 0
            gameid = (idCount -1) // 2
            if (idCount % 2) == 1:
                games[gameid] = Game(gameid)
                print("SERVER::Creating a new game...")
            else:
                games[gameid].ready = True
                p = 1
            start_new_thread(threaded_client, (conn, p, gameid))
    
    
    if __name__ == '__main__':
        init()
        run()
    
