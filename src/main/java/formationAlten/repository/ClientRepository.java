package formationAlten.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import formationAlten.entity.Civilite;
import formationAlten.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> findByCivilite(Civilite civilite);
	
	List<Client> findByNom(String nom);
	
	List<Client> findByNomContaining(String nom);
	
	Page<Client> findByNomContaining(String nom, Pageable pageable);
	
}
