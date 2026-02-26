package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	private static class Marche {
		Etal[] etals;

		private Marche(int nombreEtals) {
			this.etals = new Etal[nombreEtals];
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			Etal etal = new Etal();
			etal.occuperEtal(vendeur, produit, nbProduit);
			this.etals[indiceEtal] = etal;

		}

		private int trouverEtalLibre() {
			for (int i = 0; i < this.etals.length; i++) {
				if (etals[i] == null) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nbrEtal = 0;
			for (int i = 0; i < this.etals.length; i++) {
				if (etals[i] != null && etals[i].contientProduit(produit)) {
					nbrEtal++;
				}
			}

			Etal[] sortie = new Etal[nbrEtal];
			int indiceSortie = 0;

			for (int i = 0; i < this.etals.length; i++) {
				if (etals[i] != null && etals[i].contientProduit(produit)) {
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
			StringBuilder sortie = new StringBuilder();
			int nbEtalsVide = 0;
			for (int i = 0; i < this.etals.length; i++) {
				if (this.etals[i] != null && this.etals[i].isEtalOccupe()) {
					sortie.append(this.etals[i].afficherEtal());
				} else {
					nbEtalsVide++;
				}
			}
			if (nbEtalsVide != 0) {
				sortie.append("Il reste " + nbEtalsVide + " étals non utilisés dans le marché.\n");
			}

			return sortie.toString();
		}

	}

	public Village(String nom, int nbVillageoisMaximum, int nombreEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nombreEtals);
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

	public String installerVendeur(Gaulois vendeur, String Produit, int nbProduit) {
		StringBuilder sortie = new StringBuilder();
		sortie.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " fleurs.\n");
		int indiceEtals = this.marche.trouverEtalLibre();
		if (indiceEtals != -1) {
			this.marche.utiliserEtal(indiceEtals, vendeur, Produit, nbProduit);
			sortie.append("Le vendeur " + vendeur.getNom() + " vend des " + Produit + " à l'étal n°" + (indiceEtals + 1)
					+ "\n");
		} else {
			sortie.append("Il n'avait aucune étal de libre!\n");
		}
		return sortie.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder sortie = new StringBuilder();
		Etal[] tabEtal = this.marche.trouverEtals(produit);

		if (tabEtal.length == 0) {
			sortie.append("Il n'y a pas de vendeur qui proposent des " + produit + " au marché\n");
		} else if (tabEtal.length == 1) {
			sortie.append("Seul le vendeur " + tabEtal[0].getVendeur().getNom() + " propose des " + produit
					+ " au marché.\n");
		} else {
			sortie.append("Les vendeurs qui proposent des fleurs sont :\n");
			for (int i = 0; i < tabEtal.length; i++) {
				sortie.append("  - " + tabEtal[i].getVendeur().getNom() + "\n");
			}
		}

		return sortie.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return this.marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		StringBuilder sortie = new StringBuilder();
		Etal etal = this.marche.trouverVendeur(vendeur);
		if (etal == null) {
			sortie.append(vendeur.getNom());
			sortie.append(" n'à pas d'étal dans le village.");
		} else {
			sortie.append(etal.libererEtal());
		}
		return sortie.toString();
	}

	public String afficherMarche() {
		StringBuilder sortie = new StringBuilder();
		sortie.append("Le marché du village '");
		sortie.append(this.getNom());
		sortie.append("'possède plusieurs étals:\n");
		sortie.append(this.marche.afficherMarche());
		return sortie.toString();
	}
}