package bloon;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import mundo.Caminho;
import mundo.Mundo;
import prof.jogos2D.image.ComponenteVisual;

public abstract class DecoratorBloon implements Bloon{
    private Bloon bloonDecorador;

    public DecoratorBloon(Bloon bloonDecorador) {
        this.bloonDecorador = bloonDecorador;
    }
	// --- MÉTODOS DELEGADOS (Apenas repassam a chamada) ---
    @Override public void desenhar(Graphics2D g) { bloonDecorador.desenhar(g); }
    @Override public void mover() { bloonDecorador.mover(); }
	@Override public ComponenteVisual getComponente() { return bloonDecorador.getComponente(); }
	@Override public ComponenteVisual getPopComponente() { return bloonDecorador.getPopComponente(); }
	@Override public void setCaminho(Caminho rua) { bloonDecorador.setCaminho(rua); }
	@Override public Caminho getCaminho() { return bloonDecorador.getCaminho(); }
	@Override public int getPosicaoNoCaminho() { return bloonDecorador.getPosicaoNoCaminho(); }
	@Override public void setPosicaoNoCaminho(int pos) { bloonDecorador.setPosicaoNoCaminho(pos); }
	@Override public void setVelocidade(float veloc) { bloonDecorador.setVelocidade(veloc); }
	@Override public float getVelocidade() { return bloonDecorador.getVelocidade(); }
	@Override public void setMundo(Mundo w) { bloonDecorador.setMundo(w); }
	@Override public Mundo getMundo() { return bloonDecorador.getMundo(); }
	@Override public void setPosicao(Point p) { bloonDecorador.setPosicao(p); }
	@Override public Rectangle getBounds() { return bloonDecorador.getBounds(); }
	@Override public int getResistencia() { return bloonDecorador.getResistencia(); }
	@Override public int getValor() { return bloonDecorador.getValor(); }
	@Override public void setValor(int val) { bloonDecorador.setValor(val); }
	@Override public void addBloonObserver(BloonObserver bo) { bloonDecorador.addBloonObserver(bo); }
	@Override public void removeBloonObserver(BloonObserver bo) { bloonDecorador.removeBloonObserver(bo); }
   


    // --- MÉTODOS QUE MUDAM O COMPORTAMENTO (Override nas filhas) ---
	@Override public int pop(int estrago) { return bloonDecorador.pop(estrago); }
	@Override public void explode(int estrago) { bloonDecorador.explode(estrago); }

    @Override
    public Bloon clone() {
        try {
            DecoratorBloon copia = (DecoratorBloon) super.clone();
            // Clona o balão que está dentro da armadura
            copia.bloonDecorador = this.bloonDecorador.clone();
            return copia;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    
}
