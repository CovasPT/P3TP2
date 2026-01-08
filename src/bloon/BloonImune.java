package bloon;

public class BloonImune extends DecoratorBloon{
    private boolean imuneExplosao;
    private boolean imunePerfurantes;

    public BloonImune(Bloon bloonDecorador, boolean imuneExplosao, boolean imunePerfurantes){
        super(bloonDecorador);
        this.imuneExplosao = imuneExplosao;
        this.imunePerfurantes = imunePerfurantes;
    }

    @Override
    public void explode(int estrago) {
           if (imuneExplosao)
            return; // Ignora explosão
        super.explode(estrago);
    }
    @Override
    public int pop(int estrago) {
        if (imunePerfurantes)
            return estrago; // Ignora dano perfurante
        return super.pop(estrago);
    }
    
}
