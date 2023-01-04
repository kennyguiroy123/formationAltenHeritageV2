package formationAlten.repository;

import java.util.List;
import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import formationAlten.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	List<Produit> findByLibelle(String libelle);

	List<Produit> findByLibelleContaining(String libelle);

	Page<Produit> findByLibelleContaining(String libelle, Pageable pageable);
	
	@Query("select p from Produit p left join fetch p.fournisseur where p.id=:id")
	Optional<Produit> findByIdFetchfournisseur(@Param("id") Long id);
}
