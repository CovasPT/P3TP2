package game.manipulator;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

=======
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f
import torre.Torre;
    
    /**
    * Classe responsável por criar todos os manipuladores. Esta classe existe para
    * que qualquer alteração nos manipuladores seja feita aqui, de modo a
    * permitir que apenas esta classe seja alterada em todo o sistema
    */  
public class ManipuladorCreator { 
<<<<<<< HEAD
    public ManipuladorTorre criarManipulador(Torre t) {
=======
    public static ManipuladorTorre criarManipulador(Torre t) {
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f
        ManipuladorVisitor visitar = new ManipuladorVisitor();
        t.aceitar(visitar);
        return visitar.criarManipulador(t);          
    }
<<<<<<< HEAD

=======
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f
}
