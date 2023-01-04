package formationAlten;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import config.JpaConfig;
import formationAlten.dao.DaoProduit;
import formationAlten.entity.Produit;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class DaoProduitTest {
	
	@Autowired
	DaoProduit daoProduit;
	
	@Test
	//@Commit
	void insertProduit() {
		Produit produit = new Produit("MT-07", "Moto yamaha 2014 neuve", 4500.33);
		assertNull(produit.getId());
		daoProduit.insert(produit);
		assertNotNull(produit.getId());
		assertNotNull(daoProduit.findByKey(101L));
	}

}
