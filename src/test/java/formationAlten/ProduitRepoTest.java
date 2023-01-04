package formationAlten;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import config.JpaConfig;
import service.ProduitService;

@SpringJUnitConfig(JpaConfig.class)
class ProduitRepoTest {
	
	@Autowired
	private ProduitService produitService;

	@Test
	void test() {
		assertNotNull(produitService);
	}

}
