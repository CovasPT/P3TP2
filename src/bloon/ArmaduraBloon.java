package bloon;

public class ArmaduraBloon extends DecoratorBloon{
    private int vidaArmadura;

    public ArmaduraBloon(Bloon bloonDecorado, int vidaArmadura) {
        super(bloonDecorado);
        this.vidaArmadura = vidaArmadura;
    }

    @Override
    public int getVida() {
        // A vida total é a do bloon + a da armadura
        return bloonDecorado.getVida() + vidaArmadura;
    }

    @Override
    public void levouDano(int dano) {
        if (vidaArmadura > 0) {
            vidaArmadura -= dano;
            if (vidaArmadura < 0) {
                // Se o dano foi maior que a armadura, o resto vai para o bloon
                int danoRestante = -vidaArmadura;
                vidaArmadura = 0;
                bloonDecorado.levouDano(danoRestante);
            }
        } else {
            // Se já não tem armadura, ataca o bloon diretamente
            bloonDecorado.levouDano(dano);
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        super.desenhar(g); // Desenha o bloon normal
        if (vidaArmadura > 0) {
            // Desenha uma imagem de armadura por cima (se tiveres)
            // Ex: g.drawImage(imagemArmadura, ...);
        }
    }
}
