package bloon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import prof.jogos2D.image.ComponenteVisual;

    public class ArmaduraBloon extends DecoratorBloon{
     private int vidaArm;
     private ComponenteVisual imagemArmadura;


     public ArmaduraBloon(Bloon bloonDecorador, int vidaArm) {
        super(bloonDecorador);
        this.vidaArm = vidaArm;
     }

      @Override
    public void desenhar(Graphics2D g) {
        super.desenhar(g);
        System.out.println("Durabilidade da armadura: " + vidaArm);
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
}


