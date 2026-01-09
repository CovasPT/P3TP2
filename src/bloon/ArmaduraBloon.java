package bloon;
<<<<<<< HEAD
import java.awt.Graphics2D;
=======
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f

import prof.jogos2D.image.ComponenteVisual;

    public class ArmaduraBloon extends DecoratorBloon{
     private int vidaArm;
     private ComponenteVisual imagemArmadura;
<<<<<<< HEAD

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
=======


     public ArmaduraBloon(Bloon bloonDecorador, int vidaArm) {
        super(bloonDecorador);
        this.vidaArm = vidaArm;
     }
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f

      @Override
    public void desenhar(Graphics2D g) {
        super.desenhar(g);
<<<<<<< HEAD
=======
        System.out.println("Durabilidade da armadura: " + vidaArm);
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f
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
<<<<<<< HEAD

    @Override
    public Bloon clone() {
        ArmaduraBloon copia = (ArmaduraBloon) super.clone();
        copia.imagemArmadura = imagemArmadura.clone();
        return copia;
    }
=======
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f
}


