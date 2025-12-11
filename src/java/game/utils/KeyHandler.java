package game.utils;

import game.Game;
import game.GameplayPanel;
import game.entities.Pacman;
import game.utils.keys.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;

//Classe pour gérer les inputs
public class KeyHandler implements KeyListener {

    public static List<Key> keys = new ArrayList<>();

    public KeyHandler(GameplayPanel game) {
        game.addKeyListener(this);

        keys.add(new LeftKey());
        keys.add(new RightKey());
        keys.add(new UpKey());
        keys.add(new DownKey());
    }

    public void toggle(KeyEvent e, boolean pressed) {
        for(Key k : keys){
            if(k.validateKeyCode(e)){
                k.toggle(pressed);
            }
        }
    }

    public void doPressedKeyAction(){
        for(Key k : keys){
            if(k.isPressed){
                k.performKeyBehavior();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
