package game.manipulator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import torre.Torre;
    
    /**
    * Classe responsável por criar todos os manipuladores. Esta classe existe para
    * que qualquer alteração nos manipuladores seja feita aqui, de modo a
    * permitir que apenas esta classe seja alterada em todo o sistema
    */  
public class ManipuladorCreator { 
    public ManipuladorTorre criarManipulador(Torre t) {
        ManipuladorVisitor visitar = new ManipuladorVisitor();
        t.aceitar(visitar);
        return visitar.criarManipulador(t);          
    }

}
