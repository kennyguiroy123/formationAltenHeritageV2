package formationAlten.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationAlten.entity.Achat;
import formationAlten.entity.AchatKey;

public interface AchatRepository extends JpaRepository<Achat, AchatKey> {

}
