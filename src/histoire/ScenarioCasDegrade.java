package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois gaulois = new Gaulois("gaulois", 5);
		etal.acheterProduit(1, null);
		System.out.println("Fin du test");
	}

}
