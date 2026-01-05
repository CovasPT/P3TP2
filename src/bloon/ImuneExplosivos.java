package bloon;

public class ImuneExplosivos extends DecoratorBloon{
    public ImuneExplosivos(Bloon bloonDecorador){
        super(bloonDecorador);
    }

    @Override
    public void explode(int estrago) {
        if (estrago == 1) { // Dano explosivo
            return; // Imune a dano explosivo   
        }
        super.explode(estrago);
    }
    
}
