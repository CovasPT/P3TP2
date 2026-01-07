package torre;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import java.util.Objects;

import bloon.Bloon;
import mundo.Mundo;
import prof.jogos2D.image.ComponenteMultiAnimado;
import prof.jogos2D.util.DetectorColisoes;
import torre.projetil.Projetil;
import torre.Estrategia.*;


/**
 * Classe que implementa os comportamentos e variáveis comuns a todos as torres.
 * Tambám possui alguns métodos auxiliares para as várias torres
 */
public abstract class TorreDefault implements Torre {

	private Mundo mundo; // mundo onde está a torre
	private ComponenteMultiAnimado imagem; // desenho da torre

	private int modoAtaque = ATACA_PRIMEIRO; // modo de ataque da torre
	private EstrategiaAtaque estrategia; // estrategia de ataque
	private int raioAtaque; // raio de ataque, isto é, área circular onde consegue detetar bloons
	private Point pontoDisparo; // ponto de onde sai o disparo

	protected static final int PAUSA_ANIM = 0; // identifica a animação quando não está a disparar
	protected static final int ATAQUE_ANIM = 1; // identifica a animação de disparar
	private int ritmoDisparo; // velocidade de disparo
	private int proxDisparo; // quando volta a disparar
	private int frameDisparoDelay; // delay desde que a animação de disparo começa até que "realmente" dispara

	/**
	 * Construtor da torre. Cria uma torre dando-lhe uma imagem, um ponto de disparo
	 * e um raio de ataque. O ponto de disparo é a coordenada de onde sai o projétil
	 * e é dado relativamente ao ponto central da torre. O raio de ataque é o raio,
	 * a partir do centro da torre em que esta deteta bloons
	 * 
	 * @param cv           A imagem a usar para a torre
	 * @param ritmoDisparo quantos ciclos demora entre disparos
	 * @param delayDisparo delay da animação em que realmente ocorre o disparo
	 * @param pontoDisparo o ponto de disparo da torre, isto é, de onde sai o
	 *                     projétil. Coordenada relativa ao centro da torre
	 * @param raioAtaque   distãncia dentro da qual deteta bloons
	 */
	public TorreDefault(ComponenteMultiAnimado cv, int ritmoDisparo, int delayDisparo, Point pontoDisparo,
			int raioAtaque) {
		imagem = Objects.requireNonNull(cv);
		this.ritmoDisparo = ritmoDisparo;
		this.proxDisparo = 0;
		this.frameDisparoDelay = delayDisparo;
		this.pontoDisparo = Objects.requireNonNull(pontoDisparo);
		this.raioAtaque = raioAtaque;
	}

	protected void atualizarCicloDisparo() {
		proxDisparo++;
	}

	protected boolean podeDisparar() {
		return proxDisparo >= ritmoDisparo;
	}

	protected int resetTempoDisparar() {
		return proxDisparo = 0;
	}

	protected void sincronizarFrameDisparo(ComponenteMultiAnimado anim) {
		if (proxDisparo + frameDisparoDelay >= ritmoDisparo) {
			if (anim.getAnim() != ATAQUE_ANIM) {
				anim.setAnim(ATAQUE_ANIM);
				anim.reset();
			}
		}
	}

	protected void setComponente(ComponenteMultiAnimado v) {
		imagem = v;
	}

	@Override
	public void setMundo(Mundo w) {
		mundo = w;
	}

	@Override
	public Mundo getMundo() {
		return mundo;
	}

	@Override
	public ComponenteMultiAnimado getComponente() {
		return imagem;
	}

	@Override
	public void setPosicao(Point p) {
		imagem.setPosicaoCentro(p);
	}

	@Override
	public Point getPontoDisparo() {
		return pontoDisparo;
	}

	@Override
	public void setRaioAcao(int raio) {
		raioAtaque = raio;
	}

	@Override
	public int getRaioAcao() {
		return raioAtaque;
	}

	@Override
	public void desenhar(Graphics2D g) {
		imagem.desenhar(g);
	}

	@Override
	public void desenhaRaioAcao(Graphics2D g) {
		Point p = getComponente().getPosicaoCentro();
		Composite oldComp = g.getComposite();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g.setColor(Color.WHITE);
		g.fillOval(p.x - raioAtaque, p.y - raioAtaque, 2 * raioAtaque, 2 * raioAtaque);
		g.setColor(Color.BLUE);
		g.drawOval(p.x - raioAtaque, p.y - raioAtaque, 2 * raioAtaque, 2 * raioAtaque);
		g.setComposite(oldComp);
	}

	@Override
	public void setModoAtaque(int modo) {
		modoAtaque = modo;
	}

	@Override
	public int getModoAtaque() {
		return modoAtaque;
	}

	/**
	 * Retorna uma lista com os bloons que estejam dentro de um raio de
	 * ação circular
	 * 
	 * @param bloons lista de bloons a verificar
	 * @param center centro do raio de ação
	 * @param radius raio de ação
	 * @return lista de bloons que estão dentro desse raio de ação
	 */
	protected List<Bloon> getBloonsInRadius(List<Bloon> bloons, Point center, int radius) {
		return bloons.stream().filter(b -> DetectorColisoes.intersectam(b.getBounds(), center, radius)).toList();
	}

	/**
	 * Retorna uma lista com os bloons que intersetam um segmento de reta
	 * 
	 * @param bloons lista de bloons a verificar
	 * @param p1     ponto de início do segemento de reta
	 * @param p2     ponto de fim do segment de reta
	 * @return lista de bloons que tocam nesse segmento de reta
	 */
	protected List<Bloon> getBloonsInLine(List<Bloon> bloons, Point p1, Point p2) {
		return bloons.stream().filter(b -> b.getBounds().intersectsLine(p1.x, p1.y, p2.x, p2.y)).toList();
	}

	@Override
	public void setEstrategia(EstrategiaAtaque estrategia) {
		this.estrategia = estrategia;
	}

	@Override
	public EstrategiaAtaque getEstrategia() {
		return estrategia;
	}

	
	@Override
	public Projetil[] atacar(List<Bloon> bloons) {
		atualizarCicloDisparo();
		ComponenteMultiAnimado anim = getComponente();

		// 1. Gestão da Animação
		if (anim.getAnim() == ATAQUE_ANIM && anim.numCiclosFeitos() >= 1) {
			anim.setAnim(PAUSA_ANIM);
		}

		// 2. Escolher o Alvo (Usando STRATEGY)
		List<Bloon> alvosPossiveis = getBloonsInRadius(bloons, getComponente().getPosicaoCentro(), getRaioAcao());
		if (alvosPossiveis.isEmpty()) return new Projetil[0];
		
		Bloon alvo = getEstrategia().escolherAlvo(this,alvosPossiveis );
		
		if (alvo == null) return new Projetil[0];
		Point posAlvo = alvo.getComponente().getPosicaoCentro();

		// 3. Rodar a Torre
		double angle = DetectorColisoes.getAngulo(posAlvo, anim.getPosicaoCentro());
		anim.setAngulo(angle);

		// 4. Verificar Cooldown
		sincronizarFrameDisparo(anim);
		if (!podeDisparar()) return new Projetil[0];

		// 5. Calcular Posição do Disparo
		resetTempoDisparar();
		Point centro = getComponente().getPosicaoCentro();
		Point disparo = getPontoDisparo();
		double cosA = Math.cos(angle);
		double senA = Math.sin(angle);
		int px = (int) (disparo.x * cosA - disparo.y * senA);
		int py = (int) (disparo.y * cosA + disparo.x * senA);
		Point pontoFinalDisparo = new Point(centro.x + px, centro.y + py);

		// 6. Criar o Projétil Específico (FACTORY METHOD)
		// Aqui a classe pai delega na filha a criação do objeto concreto
		return criarProjetil(pontoFinalDisparo, angle, alvo);
	}

	protected Projetil[] criarProjetil(Point posicao, double angulo, Bloon alvo) {
		return new Projetil[0];
	}

	@Override
	public Torre clone() {
		try {
			TorreDefault copia = (TorreDefault) super.clone();
			copia.imagem = imagem.clone();
			copia.mundo = null;
			copia.pontoDisparo = new Point(pontoDisparo);
			return copia;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public void aceitar(VisitorTorre v) {
		v.visita(this);
	}


}
