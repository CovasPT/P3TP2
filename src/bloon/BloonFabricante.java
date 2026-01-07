package bloon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import prof.jogos2D.image.ComponenteVisual;

/**
 * Bloon que cria outros bloons. A criação de bloons é aleatória, dentro de uma
 * lista de bloons prováveis.
 */
public class BloonFabricante extends BloonSimples {
    // a lista de bloons prováveis de serem criados
    private Map<String, Bloon> prototipos = new HashMap<>();

    /**
     * Cria um bloon que fabrica outros bloons
     * 
     * @param imagem       imagem do bloon
     * @param imagemPop    imagem de quando o bloon rebenta
     * @param veloc        velocidade de deslocamento
     * @param resist       resistência do bloon
     * @param valor        valor
     * @param ritmoCriacao de quantos em quantos ciclos cria um novo bloon
     */
    public BloonFabricante(ComponenteVisual imagem, ComponenteVisual imagemPop, float speed, int resistence, int value,
            int ritmoCriacao) {
        // Inicializa os moldes base
        // Nota: Ajusta os construtores conforme o teu código real
        prototipos.put("basico", new BloonSimples( ));
        prototipos.put("rapido", new BloonSimples(/* imagemRapida, vida, velocidadeAlta... */));
        
        // Podes até criar protótipos já com armadura!
        Bloon baseForte = new BloonSimples(/* imagemForte... */);
        prototipos.put("blindado", new ArmaduraBloon(baseForte, 50)); 
    }

    /**
     * Adiciona um bloon à lista dos bloons prováveis
     * 
     * @param b o bloon a poder ser criado
     */
    public void addBloonProvavel(Bloon b) {
        provaveis.add(b);
    }

    @Override
    public void mover() {
        super.mover();
        // se por acaso já saiu não faz nada
        if (getResistencia() <= 0)
            return;
        proximaCriacao--;
        if (proximaCriacao <= 0) {
            // decidir aleatoriamente qual o bloon a "disparar"
            int idx = ThreadLocalRandom.current().nextInt(provaveis.size());
            // colocar o bloon um pouco à frente deste
            int pathOffset = 3;
            int pos = getPosicaoNoCaminho();
            if (getCaminho().getPoint(pos + pathOffset) == null)
                pathOffset = 0;
            // esta parte tem de ser revista pois está a usar repetidamente os mesmos bloons
            Bloon escolhido = provaveis.get(idx).clone();
            escolhido.setCaminho(getCaminho());
            getMundo().addBloonPendente(escolhido);
            escolhido.setPosicaoNoCaminho(pos + pathOffset);
            getObservers().forEach(o -> escolhido.addBloonObserver(o));
            proximaCriacao = ritmoCriacao;
        }
    }

    public Bloon criarBloon(String tipo) {
        Bloon molde = prototipos.get(tipo);
        if (molde != null) {
            return molde.clone(); // A MAGIA ESTÁ AQUI: Devolve uma cópia nova
        }
        return null;
    }
    @Override
    public Bloon clone() {
        BloonFabricante copia = (BloonFabricante) super.clone();
        copia.provaveis = new ArrayList<>();
        for (Bloon b : provaveis) {
            copia.provaveis.add(b.clone());
        }
        return copia;
    }
}
