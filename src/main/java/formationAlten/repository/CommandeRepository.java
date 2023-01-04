package formationAlten.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import formationAlten.entity.Commande;


public interface CommandeRepository extends JpaRepository<Commande, Long> {
	
	@Query("select c from Commande c left join fetch c.achats where c.id=:id") // pas de condition d'�galit� avec requ�te JPQL il automatise gr�ce � la relation One to Many et Many to One avec le mapped By
	Optional<Commande> findByCommandeFetchAchats(@Param("id")Long id);

	
	
	
}
