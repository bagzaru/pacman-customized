package game.utils.keys;

import game.utils.keys.keybehaviors.ArrowKeyBehavior;

import java.awt.event.KeyEvent;

public class UpKey extends Key {
    public UpKey(){
        keyCodes.add(KeyEvent.VK_UP);
        keyCodes.add(KeyEvent.VK_W);

        behavior = new ArrowKeyBehavior(0,-1);
    }
}
