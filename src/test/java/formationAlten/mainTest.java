package formationAlten;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import formationAlten.dao.DaoAchat;
import formationAlten.dao.DaoClient;
import formationAlten.dao.DaoCommande;
import formationAlten.dao.DaoFournisseur;

import formationAlten.dao.DaoProduit;
import formationAlten.entity.Achat;
import formationAlten.entity.AchatKey;
import formationAlten.entity.Adresse;
import formationAlten.entity.Civilite;
import formationAlten.entity.Client;
import formationAlten.entity.Commande;
import formationAlten.entity.Fournisseur;
import formationAlten.entity.Produit;
import formationAlten.util.JpaContext;

public class mainTest {

	public static void main(String[] args) {
		JpaContext.getEntityManagerFactory();
		
		DaoClient daoClient = JpaContext.getDaoClient();
		DaoFournisseur daoFournisseur = JpaContext.getDaoFournisseur();
		DaoProduit daoProduit = JpaContext.getDaoProduit();
		DaoCommande daoCommande = JpaContext.getDaoCommande();
		DaoAchat daoAchat = JpaContext.getDaoAchat();
		
		
		//daoClient.insert(new Client("Guiroy","kenny.guiroy@gmail.com", new Adresse("28", "Avenue du chut", "33700", "Bordeaux"),"Kenny", LocalDate.now(), Civilite.M));
		//daoFournisseur.insert(new Fournisseur("Mybro", "mybro@gmail.com",new Adresse("28", "Avenue du chut", "33700", "Bordeaux"),"Contactmybro.supply"));
		
		//daoProduit.insert(new Produit("Casquette Gucci", "Casquette gucci fait à la main 90% coton et 10% laine", 30.24,fourniCasquette));
		//daoProduit.insert(new Produit("Casquette Louis Vuitton", "Casquette L&V fait à la main 90% coton et 10% laine", 50.55,fourniCasquette));
		
		Client client1 = new Client("Guiroy","kenny.guiroy@gmail.com", new Adresse("28", "Avenue du chut", "33700", "Bordeaux"),"Kenny", LocalDate.now(), Civilite.M);
		daoClient.insert(client1);
		Client client2 = new Client("Hamed","Hamed22@gmail.com", new Adresse("12", "Avenue du maréchal", "33000", "Bordeaux"),"Jarid", LocalDate.now(), Civilite.M);
		daoClient.insert(client2);
		Fournisseur fournisseur1 = new Fournisseur("Mybro", "mybro@gmail.com",new Adresse("28", "Avenue du chut", "33700", "Bordeaux"),"Contactmybro.supply");
		daoFournisseur.insert(fournisseur1);
		Fournisseur fournisseur2 = new Fournisseur("Sheesh", "sheesh@gmail.com",new Adresse("33", "Rue du jambon", "75000", "Paris"),"Contactsheesh.supply");
		daoFournisseur.insert(fournisseur2);
		Fournisseur fourniCasquette = daoFournisseur.findByKey(69L);
		Produit produit1 = new Produit("Casquette Gucci", "Casquette gucci fait à la main 90% coton et 10% laine", 30.24,fourniCasquette);
		Produit produit2 = new Produit("Casquette Louis Vuitton", "Casquette L&V fait à la main 90% coton et 10% laine", 50.55,fourniCasquette);
		Produit produit3 = new Produit("Sacoche Gucci", "Sacoche gucci brodée à la main 100% cuir ", 99.99 ,fournisseur2);
		produit1.setFournisseur(fournisseur1);
		daoProduit.insert(produit1);
		daoProduit.insert(produit2);
		daoProduit.insert(produit3);
		
		Commande commande1 = new Commande(LocalDate.now(), client1);
		Commande commande2 = new Commande(LocalDate.now(), client2);
		
		daoCommande.insert(commande1);
		daoCommande.insert(commande2);
		
		Achat achat1 = new Achat(new AchatKey(commande1, produit2), 2);

		Achat achat3 = new Achat(new AchatKey(commande2, produit1), 5);
		daoAchat.insert(achat1);
		daoAchat.insert(new Achat(new AchatKey(commande1, produit3), 1));
		daoAchat.insert(achat3);
		/*Set<Produit> achats = new HashSet<Produit>();
		achats.add(produit2);
		achats.add(produit3);
		commande1.setAchats(achats);
		
		daoCommande.insert(commande1);
		
		* Version sans la table Achat et AchatKey
		*/
		
		
		
		List<Produit> produits = daoProduit.findAll();
		for(Produit p:produits) {
			System.out.println(p.getLibelle()+ " || " + p.getDescription());
		}
		
		System.out.println(daoProduit.findAll());
		
		
		
		//System.out.println(daoProduit.findByKey(100L).getLibelle() + " || " + daoProduit.findByKey(100L).getDescription());
		
		/*
		 * 
		 * Encapsulation des variables de création de client, fournisseur et produit pour que cela soit plus propre
		 * 
		 * 
		 * Client client1 = new Client("Guiroy","kenny.guiroy@gmail.com", new Adresse("28", "Avenue du chut", "33700", "Bordeaux"),"Kenny", LocalDate.now(), Civilite.M);
		 * daoClient.insert(client1);
		 * Fournisseur fournisseur1 = new Fournisseur("Mybro", "mybro@gmail.com",new Adresse("28", "Avenue du chut", "33700", "Bordeaux"),"Contactmybro.supply");
		 * daoFournisseur.insert(fournisseur1);
		 * Produit produit1 = new Produit("Casquette Gucci", "Casquette gucci fait à la main 90% coton et 10% laine", 30.24,fourniCasquette);
		 * produit1.setFournisseur(fournisseur1);
		 * daoProduit.insert(produit1);
		 * 
		 * 
		 */
		
		JpaContext.DestroyJpaContext();
		
	}

}
