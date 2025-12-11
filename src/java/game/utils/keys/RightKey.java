package game.utils.keys;

import game.utils.keys.keybehaviors.ArrowKeyBehavior;

import java.awt.event.KeyEvent;

public class RightKey extends Key {
    public RightKey(){
        keyCodes.add(KeyEvent.VK_RIGHT);
        keyCodes.add(KeyEvent.VK_D);

        behavior = new ArrowKeyBehavior(1,0);
    }
}
