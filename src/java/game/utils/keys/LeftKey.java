package game.utils.keys;

import game.entities.Pacman;
import game.utils.keys.keybehaviors.ArrowKeyBehavior;

import java.awt.event.KeyEvent;

public class LeftKey extends Key {
    public LeftKey(){
        keyCodes.add(KeyEvent.VK_LEFT);
        keyCodes.add(KeyEvent.VK_A);

        behavior = new ArrowKeyBehavior(-1,0);
    }
}
