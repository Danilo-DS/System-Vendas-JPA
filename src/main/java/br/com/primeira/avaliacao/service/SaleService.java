package br.com.primeira.avaliacao.service;

import java.util.Collection;
import java.util.Optional;

import br.com.primeira.avaliacao.model.Sale;
import br.com.primeira.avaliacao.repository.SaleRepositoryImpl;

/**
 * @author DS
 *
 */
public class SaleService {

	private SaleRepositoryImpl repository;
	
	public SaleService() {
		repository = new SaleRepositoryImpl();
	}
	
	public void update(Long id, Sale sale){
		repository.update(id, sale);
	}

	public void save(Sale sale){
		repository.save(sale);		
	}

	public Collection<Sale> findAll(){
		return repository.findAll();
	}

	public void delete(Long id){
		repository.delete(id);		
	}

	public Optional<Sale> findById(Long id){
		return repository.findById(id);
	}

}
