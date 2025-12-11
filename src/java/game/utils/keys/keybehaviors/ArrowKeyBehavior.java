package game.utils.keys.keybehaviors;

import game.Game;
import game.entities.Pacman;

public class ArrowKeyBehavior implements KeyBehavior {
    int dx;
    int dy;
    public ArrowKeyBehavior(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void Action() {
        Pacman pacman = Game.getPacman();
        pacman.changeSpeed(dx, dy);
    }
}
