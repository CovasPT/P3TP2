package torre;

import java.awt.image.BufferedImage;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.Map;
import java.util.function.Supplier;
=======
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f

import prof.jogos2D.util.ImageLoader;

/**
 * Classe que trata da criação das várias torres. Esta classe existe que lidar
 * com a criação de todas as torres e assim impedir que as outras classes tenham
 * de ser alteradas quando se criam novas torres.
 */
public class TorreCreator {
	private ImageLoader loader = ImageLoader.getLoader();
<<<<<<< HEAD
	private Map<String, Supplier<Torre>> torres = new HashMap<>();
=======
	private HashMap<String, Torre> torres = new HashMap<>();
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f



	/**
	 * cria a torre com um dado nome
	 * 
	 * @param nome nome da torre a criar
	 * @return a torre criada, ou null se não existir torre com o nome dado
	 */
	public TorreCreator() {
<<<<<<< HEAD
		torres.put("octo", () -> new TorreOctogonal((BufferedImage) loader.getImage("data/torres/octo/imagem.gif")));
		torres.put("macaco", () -> new TorreMacaco((BufferedImage) loader.getImage("data/torres/macaco/imagem.gif")));
		torres.put("canhao", () -> new TorreCanhao((BufferedImage) loader.getImage("data/torres/canhao/imagem.gif")));
		torres.put("morteiro", () -> new TorreMorteiro((BufferedImage) loader.getImage("data/torres/morteiro/imagem.gif")));
		torres.put("balista", () -> new TorreBalista((BufferedImage) loader.getImage("data/torres/balista/imagem.gif")));
		torres.put("ninja", () -> new TorreNinja((BufferedImage) loader.getImage("data/torres/ninja/imagem.gif")));
		torres.put("sniper", () -> new TorreSniper((BufferedImage) loader.getImage("data/torres/sniper/imagem.gif")));
	}

	public Torre criarTorrePorNome(String nome) {
		Supplier<Torre> supplier = torres.get(nome);
		return supplier != null ? supplier.get() : null;
=======
		torres.put("octo", new TorreOctogonal((BufferedImage) loader.getImage("data/torres/octo/imagem.gif")));
        torres.put("macaco", new TorreMacaco((BufferedImage) loader.getImage("data/torres/macaco/imagem.gif")));
        torres.put("canhao", new TorreCanhao((BufferedImage) loader.getImage("data/torres/canhao/imagem.gif")));
        torres.put("morteiro", new TorreMorteiro((BufferedImage) loader.getImage("data/torres/morteiro/imagem.gif")));
        torres.put("balista", new TorreBalista((BufferedImage) loader.getImage("data/torres/balista/imagem.gif")));
        torres.put("ninja", new TorreNinja((BufferedImage) loader.getImage("data/torres/ninja/imagem.gif")));
        torres.put("sniper", new TorreSniper((BufferedImage) loader.getImage("data/torres/sniper/imagem.gif")));
	}

	public Torre criarTorrePorNome(String nome) {
        return torres.get(nome).clone();
>>>>>>> 8b511d39d8cb55412ea3b078b743fc63d2d7d58f
	}
}
