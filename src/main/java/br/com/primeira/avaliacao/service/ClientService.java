package br.com.primeira.avaliacao.service;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.primeira.avaliacao.model.Client;
import br.com.primeira.avaliacao.repository.ClientRepository;
import br.com.primeira.avaliacao.repository.JpaUtils;
import br.com.primeira.avaliacao.util.Constantes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientService implements ClientRepository {
	
	private EntityManager em;
	
	@Override
	public void update(Long id, Client client){
		try{
			em = JpaUtils.startConnection();
			Optional<Client> clientExisting  = findById(id);
			if(clientExisting.isPresent()) {
				client.setId(id);
				
				em.getTransaction().begin();
				em.merge(client);
				em.getTransaction().commit();
			}
			else {
				throw new Exception(Constantes.updateClientFailed);
			}
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			log.error(Constantes.updateClientError + e.getMessage());
		}
		finally {
			try {
				JpaUtils.closeConnection(em);
			}
			catch(Exception e){
				log.error(Constantes.closeConnectionError + e.getMessage());
			}
		}
		
	}

	@Override
	public void save(Client client){
		try{
			em = JpaUtils.startConnection();
			em.getTransaction().begin();
			em.persist(client);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			log.error(Constantes.saveClientError + e.getMessage());
		}
		finally {
			try {
				JpaUtils.closeConnection(em);
			}
			catch(Exception e){
				log.error(Constantes.closeConnectionError + e.getMessage());
			}
		}
		
	}

	@Override
	public Collection<Client> findAll(){
		try {
			em = JpaUtils.startConnection();
			return em.createNamedQuery("Client.findAll", Client.class).getResultList();
		}
		catch (Exception e) {
			log.error(Constantes.listClientsError + e.getMessage());
		}
		finally {
			try {
				JpaUtils.closeConnection(em);
			}
			catch(Exception e){
				log.error(Constantes.closeConnectionError + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public void delete(Long id){
		try{
			em = JpaUtils.startConnection();
			Optional<Client> clientExistent  = findById(id);
			if(clientExistent.isPresent()) {
				
				em.getTransaction().begin();
				em.remove(clientExistent.get());
				em.getTransaction().commit();
			}
			else {
				throw new Exception(Constantes.deleteClientFailed);
			}
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			log.error(Constantes.deleteClientError + e.getMessage());
		}
		finally {
			try {
				JpaUtils.closeConnection(em);
			}
			catch(Exception e){
				log.error(Constantes.closeConnectionError + e.getMessage());
			}
		}
		
	}

	@Override
	public Optional<Client> findById(Long id){
		Client client = null;
		try{
			em = JpaUtils.startConnection();
			client = em.find(Client.class, id);
		}
		catch (Exception e) {
			log.error(Constantes.findClientError + e.getMessage());
		}
		finally {
			try {
				JpaUtils.closeConnection(em);
			}
			catch(Exception e){
				log.error(Constantes.closeConnectionError + e.getMessage());
			}
		}
		return Optional.ofNullable(client);
	}

}
