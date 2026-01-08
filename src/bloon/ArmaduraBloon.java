package bloon;
import java.awt.Graphics2D;

import prof.jogos2D.image.ComponenteVisual;

    public class ArmaduraBloon extends DecoratorBloon{
     private int vidaArm;
     private ComponenteVisual imagemArmadura;

     public ArmaduraBloon(Bloon bloonDecorador, int vidaArm, ComponenteVisual imagemArmadura) {
        super(bloonDecorador);
        this.vidaArm = vidaArm;
        this.imagemArmadura = imagemArmadura;
     }

    @Override
    public void mover() {
        super.mover();
        if (vidaArm > 0) {
            imagemArmadura.setPosicaoCentro(getComponente().getPosicaoCentro());
        }
    }

      @Override
    public void desenhar(Graphics2D g) {
        super.desenhar(g);
        if (vidaArm > 0) {
            imagemArmadura.desenhar(g);
        }
    }
     @Override
     public int pop(int estrago) {
         if (vidaArm > 0) {
            vidaArm--; 
            // Absorve o dano do perfurante e devolve 0
            return 0; 
        }
        // Se a armadura já partiu, o bloon sofre dano normal
        return super.pop(estrago);
    }

    @Override
    public Bloon clone() {
        ArmaduraBloon copia = (ArmaduraBloon) super.clone();
        copia.imagemArmadura = imagemArmadura.clone();
        return copia;
    }
}
