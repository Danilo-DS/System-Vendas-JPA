package br.com.primeira.avaliacao.service;

import java.util.Collection;
import java.util.Optional;

import br.com.primeira.avaliacao.model.Client;
import br.com.primeira.avaliacao.repository.ClientRepositoryImpl;

/**
 * @author DS
 *
 */
public class ClientService {
	
	private ClientRepositoryImpl repository;
	
	public ClientService() {
		repository = new ClientRepositoryImpl();
	}
	
	public void update(Long id, Client client){	
		repository.update(id, client);
	}

	public void save(Client client){
		repository.save(client);		
	}

	public Collection<Client> findAll(){
		return repository.findAll();
	}

	public void delete(Long id){
		repository.delete(id);
	}

	public Optional<Client> findById(Long id){
		return repository.findById(id);
	}

}
