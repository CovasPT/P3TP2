package bloon;

import java.awt.Graphics2D;

import prof.jogos2D.image.ComponenteVisual;

public class BloonEscudo extends BloonFabricante{
       private int durabilidade;
    private ComponenteVisual imagemArmadura; 
    
   @Override
    public void desenhar(Graphics2D g) {
        super.desenhar(g);
        if (durabilidade > 0) {
            imagemArmadura.desenhar(g);
        }
    }
}
