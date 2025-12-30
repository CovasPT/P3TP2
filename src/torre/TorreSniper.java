package torre;

import game.manipulator.ManipuladorTorre;

public class TorreSniper {

    @Override
    public ManipuladorTorre criarManipulador() {
        return new ManipuladorSniper(this);
    }
    
}
