package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois vendeur = new Gaulois("vendeur", 5);
		etal.occuperEtal(vendeur, "test", 10);
		Gaulois gaulois = new Gaulois("gaulois", 5);
		try {
			String retour = etal.acheterProduit(1, gaulois);
			System.out.println(retour);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
	}

}
