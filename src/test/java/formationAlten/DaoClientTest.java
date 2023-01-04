package formationAlten;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import config.JpaConfig;
import formationAlten.dao.DaoClient;
import formationAlten.entity.Civilite;
import formationAlten.entity.Client;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class DaoClientTest {

	@Autowired
	DaoClient daoClient;

	@Test
	//@Commit
	void insertTest() {
		Client client = new Client("nomTest", "email@gmail.com", null, "prenomTest", LocalDate.now(), Civilite.M);
		assertNull(client.getId());
		daoClient.insert(client);
		assertNotNull(client.getId());
		assertNotNull(daoClient.findByKey(4L));
	}
	
	@Test
	//@Commit
	void updateTest() {
		Client client = daoClient.findByKey(4L);
		client.setNom("UpdateNom");
		client.setPrenom("UpdatePrenom");
		daoClient.update(client);
		assertEquals("UpdateNom", client.getNom());
		assertEquals("UpdatePrenom", client.getPrenom());
	}
	
	@Test
	//@Commit
	void deleteTest() {
		Client client = daoClient.findByKey(4L);
		daoClient.delete(client);
		assertNull(daoClient.findByKey(4L));
	}

}
