package bloon;

import java.awt.Graphics2D;

import prof.jogos2D.image.ComponenteVisual;

public class BloonEscudo extends DecoratorBloon{
       private int durabilidade;
    private ComponenteVisual imagemEscudo; 
    
    public BloonEscudo(Bloon bloonDecorado, int durabilidade, ComponenteVisual imagemEscudo) {
        super(bloonDecorado);
        this.durabilidade=durabilidade;
        this.imagemEscudo=imagemEscudo;
    }



    @Override
    public void mover() {
        super.mover();
        if (durabilidade > 0) {
            imagemEscudo.setPosicaoCentro(getComponente().getPosicaoCentro());
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        super.desenhar(g);
        if (durabilidade > 0) {
            imagemEscudo.desenhar(g);
        }
    }

    @Override
    public void explode(int estrago) {
        if (durabilidade > 0) {
            durabilidade--;
            // Absorve a explosão e retorna (não chama o super)
            return; 
        }
        super.explode(estrago);
    }


    @Override
    public Bloon clone() {
        BloonEscudo copia = (BloonEscudo) super.clone();
        copia.imagemEscudo = imagemEscudo.clone(); 
        return copia;
    }
}
