package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formationAlten.entity.Produit;
import formationAlten.exception.IdException;
import formationAlten.exception.ProduitException;
import formationAlten.repository.FournisseurRepository;
import formationAlten.repository.ProduitRepository;

@Service
public class ProduitService {
	@Autowired
	private FournisseurRepository fournisseurRepo;
	@Autowired
	private ProduitRepository produitRepo;
	
	/*
	 * 
	 * Ne pas gérer les update des mots de passe avec le compte il faut qu'il soit gérer indépandamment pour pouvoir assurer une meilleur sécurité et évité de réécrire le mot de passe encodé
	 * 
	 */
	public void create (Produit produit) {
		checkProduitIsNotNull(produit);
		if(produit.getLibelle()==null || produit.getLibelle().isEmpty()) {
			throw new ProduitException("Libelle vide");
		}
		if(produit.getDescription()==null || produit.getDescription().isEmpty()) {
			throw new ProduitException("Description vide");
		}
		produitRepo.save(produit);
	}
	
	public void checkProduitIsNotNull(Produit produit) {
		if(produit==null) {
			throw new ProduitException("Produit null");
		}
	}
	
	public Produit getById(Long id) {
		if(id == null) {
			throw new IdException();
		}
		return produitRepo.findById(id).orElseThrow(() -> {			
			throw new ProduitException("Produit inconnu");	
		});
	}
	
	public void delete(Produit produit) {
		checkProduitIsNotNull(produit);
		//produit = getById(produit.getId());
		fournisseurRepo.updateByProduitSetSupplierToNull(null);		
		produitRepo.delete(produit);
	}
}
