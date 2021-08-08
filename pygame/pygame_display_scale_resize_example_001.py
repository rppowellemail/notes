# GitHub:
# https://github.com/Rabbid76/PyGameExamplesAndAnswers/blob/master/documentation/pygame/pygame_display_resize_and_scroll.md
#
# Stack Overflow:
# https://stackoverflow.com/questions/64341589/scale-everything-on-pygame-display-surface/64341784#64341784

import pygame

pygame.init()
display_win = pygame.display.set_mode((500, 500))
win = pygame.Surface((125, 125))

font = pygame.font.SysFont(None, 40)
clock = pygame.time.Clock()

text = font.render("Text", True, (255, 255, 0))

run = True
while run:
    clock.tick(60)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            run = False
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_ESCAPE:
                run = False

    window_center = win.get_rect().center

    win.fill(0)
    pygame.draw.circle(win, (255, 0, 0), window_center, 32)
    win.blit(text, text.get_rect(center = window_center))

    scaled_win = pygame.transform.scale(win, display_win.get_size())
    #scaled_win = pygame.transform.smoothscale(win, display_win.get_size())
    display_win.blit(scaled_win, (0, 0))
    pygame.display.flip()

pygame.quit()
exit()
