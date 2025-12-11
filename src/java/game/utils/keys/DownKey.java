package game.utils.keys;

import game.utils.keys.keybehaviors.ArrowKeyBehavior;

import java.awt.event.KeyEvent;

public class DownKey extends Key {

    public DownKey(){
        keyCodes.add(KeyEvent.VK_DOWN);
        keyCodes.add(KeyEvent.VK_S);

        behavior = new ArrowKeyBehavior(0,1);
    }
}
