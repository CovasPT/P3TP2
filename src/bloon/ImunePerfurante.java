package bloon;

public class ImunePerfurante extends DecoratorBloon{
    public ImunePerfurante(Bloon bloonDecorador){
        super(bloonDecorador);
    }

    @Override
    public int pop(int estrago) {
        if (estrago == 1) { // Dano perfurante
            return 0; // Imune a dano perfurante
        }
        return super.pop(estrago);
    }

   


    
}
