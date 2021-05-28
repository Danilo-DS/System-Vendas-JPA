package br.com.primeira.avaliacao.service;

import java.util.Collection;
import java.util.Optional;

import br.com.primeira.avaliacao.model.Product;
import br.com.primeira.avaliacao.repository.ProductRespositoryImpl;

/**
 * @author DS
 *
 */
public class ProductService {
	
	private ProductRespositoryImpl repository;
	
	public ProductService() {
		repository = new ProductRespositoryImpl();
	}
	
	public void update(Long id, Product product){
		repository.update(id, product);		
	}

	public void save(Product product){
		repository.save(product);
	}


	public Collection<Product> findAll(){
		return repository.findAll();
	}

	public void delete(Long id){
		repository.delete(id);
	}

	public Optional<Product> findById(Long id){
		return repository.findById(id);
	}

}
