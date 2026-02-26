package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	private static class marche {
		Etal[] etals;

		private marche(int nombreEtals) {
			this.etals = new Etal[nombreEtals];
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);

		}

		private int trouverEtalLibre() {
			for (int i = 0; i < this.etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nbrEtal = 0;
			for (int i = 0; i < this.etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nbrEtal++;
				}
			}

			Etal[] sortie = new Etal[nbrEtal];
			int indiceSortie = 0;

			for (int i = 0; i < this.etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					sortie[indiceSortie] = etals[i];
					indiceSortie++;
				}
			}
			return sortie;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < this.etals.length; i++) {
				if (this.etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}

		private String afficherMarche() {
			String sortie = "";
			int nbEtalsVide = 0;
			for (int i = 0; i < this.etals.length; i++) {
				if (this.etals[i].isEtalOccupe()) {

					sortie += this.etals[i].afficherEtal();
				} else {
					nbEtalsVide++;
				}
			}
			if (nbEtalsVide) {

			}
		}

	}

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}