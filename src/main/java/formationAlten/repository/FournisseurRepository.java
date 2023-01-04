package formationAlten.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formationAlten.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
	
	@Modifying
	@Transactional
	@Query("update Fournisseur f set f.listeProduits=null where f.listeProduits=:fournisseur")
	void updateByProduitSetSupplierToNull(@Param("fournisseur") Fournisseur fournisseur) ;
	
	@Modifying
	@Transactional
	@Query("delete Fournisseur f where f.listeProduits =: fournisseur")
	void deleteBySupplier(@Param("fournisseur")Fournisseur fournisseur);

}
