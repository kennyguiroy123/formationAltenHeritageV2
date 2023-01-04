package formationAlten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import formationAlten.entity.Adresse;
import formationAlten.entity.Client;
import formationAlten.entity.Commande;
import formationAlten.exception.ClientException;
import formationAlten.exception.IdException;
import formationAlten.repository.AchatRepository;
import formationAlten.repository.ClientRepository;
import formationAlten.repository.CommandeRepository;

@Service
public class ClientService {
	
		@Autowired
		private ClientRepository clientRepository;
		@Autowired
		private CommandeRepository commandeRepository;
		@Autowired
		private AchatRepository achatRepository;
		
		
		public void create(Client client) {
			checkClientIsNotNull(client);	
			clientRepository.save(client);
		}
		private void checkClientIsNotNull(Client client) {
			if (client == null) {
				throw new ClientException("client not found");
			}
		}
		public Client getById(Long id) {
			if(id==null) {
				throw new IdException();
			}
			return clientRepository.findByIdFetchCommandes(id).orElseThrow(()->{throw new ClientException("client not found");});
		}
		public void deleteById(Long id) {
			
			
			
			Client client= getById(id);
			
			achatRepository.updateByAchatKeySetAchatKeyToNull((Commande) client.getCommandes());
			commandeRepository.updateByClient(client);
		
			
			clientRepository.delete(client);
		}
		public void delete(Long id) {
			deleteById(id);
		}
		public void delete(Client client) {
			checkClientIsNotNull(client);
			deleteById(client.getId());
		}
		
		public List<Client> getAll() {
			return clientRepository.findAll();
		}
		
		public Page<Client> getAll(Pageable pageable) {
			if (pageable == null) {
				throw new ClientException();
			}
			return clientRepository.findAll(pageable);
		}
		public Page<Client> getNextPage(Page<Client> page) {
			if (page == null) {
				throw new ClientException();
			}
			return clientRepository.findAll(page.nextOrLastPageable());
		}

		public Page<Client> getPrevious(Page<Client> page) {
			if (page == null) {
				throw new ClientException();
			}
			return clientRepository.findAll(page.previousOrFirstPageable());
		}
		public Client update(Client client) {
			Client clientEnBase = getById(client.getId());
			clientEnBase.setCivilite(client.getCivilite());
			clientEnBase.setDateInscription(client.getDateInscription());
			clientEnBase.setEmail(client.getEmail());
			clientEnBase.setNom(client.getNom());
			clientEnBase.setPrenom(client.getNom());
			if(client.getAdresse()!=null) {
				client.setAdresse(new Adresse(
						client.getAdresse().getNumero(), 
						client.getAdresse().getRue(),
						client.getAdresse().getCodePostal(),
						client.getAdresse().getVille()));
			}else {
				clientEnBase.setAdresse(null);
			}
			return clientRepository.save(clientEnBase);
		}
}