package formationAlten.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationAlten.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
