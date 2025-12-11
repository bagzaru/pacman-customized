package game.entities.ghosts;

import game.Game;
import game.entities.MovingEntity;
import game.ghostStates.*;
import game.ghostStrategies.IGhostStrategy;
import game.utils.ResourceFacade;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Classe abtraite pour décrire les fantômes
public abstract class Ghost extends MovingEntity {
    protected GhostState state;

    protected final GhostState chaseMode;
    protected final GhostState scatterMode;
    protected final GhostState frightenedMode;
    protected final GhostState eatenMode;
    protected final GhostState houseMode;

    protected static BufferedImage frightenedSprite1;
    protected static BufferedImage frightenedSprite2;
    protected static BufferedImage eatenSprite;

    protected IGhostStrategy strategy;

    public Ghost(int xPos, int yPos, String spriteName) {
        super(32, xPos, yPos, 2, spriteName, 2, 0.1f);

        //Création des différents états des fantômes
        chaseMode = new ChaseMode(this);
        scatterMode = new ScatterMode(this);
        frightenedMode = new FrightenedMode(this);
        eatenMode = new EatenMode(this);
        houseMode = new HouseMode(this);

        state = houseMode; //état initial

        try {
            ResourceFacade resourceFacade = ResourceFacade.getInstance();
            frightenedSprite1 = resourceFacade.getImage("img/ghost_frightened.png");
            frightenedSprite2 = resourceFacade.getImage("img/ghost_frightened_2.png");
            eatenSprite = resourceFacade.getImage("img/ghost_eaten.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Méthodes pour les transitions entre les différents états
    public void setState(GhostState state) {
        state.resetTimer();
        this.state = state;
    }

    public GhostState getChaseMode() {
        return chaseMode;
    }

    public GhostState getScatterMode() {
        return scatterMode;
    }

    public GhostState getFrightenedMode() {
        return frightenedMode;
    }

    public GhostState getEatenMode() {
        return eatenMode;
    }

    public GhostState getHouseMode() {
        return houseMode;
    }

    public IGhostStrategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(IGhostStrategy strategy) {
        this.strategy = strategy;
    }

    public GhostState getState() {
        return state;
    }

    public void onPacmanCollision() {
        state.onPacmanCollision();
    }

    @Override
    public void update() {
        if (!Game.getFirstInput()) return; //Les fantômes ne bougent pas tant que le joueur n'a pas bougé

        state.addTimer(1);
        if (state.getTimer() >= state.getTimerThreshold()) {
            state.timerModeOver();
        }

        //Si le fantôme est sur la case juste au dessus de sa maison, l'état est notifié afin d'appliquer la transition adéquate
        if (xPos == 208 && yPos == 168) {
            state.outsideHouse();
        }

        //Si le fantôme est sur la case au milieu sa maison, l'état est notifié afin d'appliquer la transition adéquate
        if (xPos == 208 && yPos == 200) {
            state.insideHouse();
        }

        //Selon l'état, le fantôme calcule sa prochaine direction, et sa position est ensuite mise à jour
        state.computeNextDir();
        updatePosition();
    }

    @Override
    public void render(Graphics2D g) {
        state.render(g, sprite, frightenedSprite1, frightenedSprite2, eatenSprite);
    }

    public void eaten() {
        state.eaten();
    }

    public void superPacGumEaten() {
        state.superPacGumEaten();
    }

    public int[] getScatterTargetPosition(){
        return strategy.getScatterTargetPosition();
    }

    public int[] getChaseTargetPosition(){
        return strategy.getChaseTargetPosition();
    }
}
