package formationAlten;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import config.JpaConfig;
import formationAlten.entity.Civilite;
import formationAlten.entity.Client;
import formationAlten.exception.ClientException;
import formationAlten.repository.ClientRepository;

@SpringJUnitConfig(JpaConfig.class)
class ClientRepoTest {
	
	@Autowired
	private ClientRepository clientRepo;

	/*@Test
	void testRepo() {
		clientRepo.findAll();
	}*/
	
	@Test
	void testRepoForEach() {
//		clientRepo.findAll().forEach(f-> {
//			System.out.println(f.getId());
//		});;
		assertTrue(clientRepo.findById(4L).orElseThrow(ClientException::new) instanceof Client);
		
//		Client c = clientRepo.findById(4L).orElseThrow(ClientException::new); // Version plus détaillé de la ligne ci-dessus
//		assertTrue(c instanceof Client);
//		if(opt.isPresent()) {
//			opt.get(); // Permet de récupérer la class client et pas que l'id, vu que findById return que l'id du client
//			System.out.println(opt.get().getNom());
//			System.out.println(opt.get().getPrenom());
//		}
		
	}
	
	@Test
	void findByIdException() {
		assertThrows(ClientException.class, () -> {
			clientRepo.findById(500L).orElseThrow(ClientException::new);
		});
	}
	
	@Test
	void PageTest() {
		Pageable pageable = PageRequest.of(0, 1); // Permet de choisir le nombre d'élements par page
		
		Page<Client> page = clientRepo.findAll(pageable);
		System.out.println(page);
		page.forEach(f -> {
			System.out.println(f.getId());
			System.out.println(f.getNom());
		});
		
		page = clientRepo.findAll(page.nextOrLastPageable()); // Next page
		System.out.println(page);
		page.forEach(f -> {
			System.out.println(f.getId());
			System.out.println(f.getNom());
		});
		
		page = clientRepo.findAll(page.previousOrFirstPageable()); // Previous page
		System.out.println(page);
		page.forEach(f -> {
			System.out.println(f.getId());
			System.out.println(f.getNom());
		});
		
	}
	
	@Test
	void requetePerso() {		
		clientRepo.findByCivilite(Civilite.M);
		System.out.println(clientRepo.findByCivilite(Civilite.M));
		clientRepo.findByNomContaining("a");
		System.out.println(clientRepo.findByNomContaining("a"));
	}

}
