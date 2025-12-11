package game.utils.keys;

import game.utils.keys.keybehaviors.KeyBehavior;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Key {
    public boolean isPressed;
    public List<Integer> keyCodes =  new ArrayList<Integer>();

    public KeyBehavior behavior;

    public Key() {
    }

    public void toggle(boolean pressed) {
        if (pressed != isPressed) {
            isPressed = pressed;
        }
    }

    public boolean validateKeyCode(KeyEvent keyEvent){
        int inputKeyCode = keyEvent.getKeyCode();
        for(int myKeyCode :keyCodes){
            if(inputKeyCode == myKeyCode){
                return true;
            }
        }
        return false;
    }

    public void performKeyBehavior(){
        behavior.Action();
    }
}