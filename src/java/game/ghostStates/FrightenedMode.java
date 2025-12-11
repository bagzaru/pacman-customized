package game.ghostStates;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.entities.ghosts.Ghost;
import game.utils.Utils;

//Classe pour décrire l'état concret d'un fantôme effrayé (après que Pacman ait mangé une SuperPacGum)
public class FrightenedMode extends GhostState{
    public FrightenedMode(Ghost ghost) {
        super(ghost);
    }

    //Transition lorsque le fantôme est mangé
    @Override
    public void eaten() {
        ghost.setState(ghost.getEatenMode());
    }

    //Transition lorsque le timer d'état effrayé est terminé
    @Override
    public void timerModeOver() {
        ghost.setState(isChasing ? ghost.getChaseMode() : ghost.getScatterMode());
    }

    //Dans cet état, la position ciblée est une case aléatoire autour du fantôme
    @Override
    public int[] getTargetPosition(){
        int[] position = new int[2];

        boolean randomAxis = Utils.randomBool();
        position[0] = ghost.getxPos() + (randomAxis ? Utils.randomInt(-1,1) * 32 : 0);
        position[1] = ghost.getyPos() + (!randomAxis ? Utils.randomInt(-1,1) * 32 : 0);
        return position;
    }

    @Override
    public int getTimerThreshold() {
        return 60 * 7;
    }

    @Override
    public void onPacmanCollision() {
        eaten();
    }

    @Override
    public int getScore() {
        return 500;
    }

    @Override
    public void render(Graphics2D g, BufferedImage sprite, BufferedImage frightenedSprite1, BufferedImage frightenedSprite2, BufferedImage eatenSprite) {
        int size = ghost.getSize();
        int xPos = ghost.getxPos();
        int yPos = ghost.getyPos();
        int subimage = (int)ghost.getSubimage();
        if (timer <= (60 * 5) || timer%20 > 10) {
            g.drawImage(frightenedSprite1.getSubimage((int)subimage * size, 0, size, size), xPos, yPos,null);
        }else{
            g.drawImage(frightenedSprite2.getSubimage((int)subimage * size, 0, size, size), xPos, yPos,null);
        }
    }
}
