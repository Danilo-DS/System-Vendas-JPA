package br.com.primeira.avaliacao.service;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.primeira.avaliacao.model.Product;
import br.com.primeira.avaliacao.repository.JpaUtils;
import br.com.primeira.avaliacao.repository.ProductRepository;
import br.com.primeira.avaliacao.util.Constantes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductService implements ProductRepository{
	
	private EntityManager em;
	
	@Override
	public void update(Long id, Product product){
		try{
			em = JpaUtils.startConnection();
			Optional<Product> productExisting  = findById(id);
			if(productExisting.isPresent()) {
				product.setId(id);
				
				em.getTransaction().begin();
				em.merge(product);
				em.getTransaction().commit();
			}
			else {
				throw new Exception(Constantes.updateProductFailed);
			}
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			log.error(Constantes.updateProductError + e.getMessage());
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
	public void save(Product product){
		try{
			em = JpaUtils.startConnection();
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			log.error(Constantes.saveProductError + e.getMessage());
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
	public Collection<Product> findAll(){
		try {
			em = JpaUtils.startConnection();
			return em.createNamedQuery("Product.findAll", Product.class).getResultList();
		}
		catch (Exception e) {
			log.error(Constantes.listProductError + e.getMessage());
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
			Optional<Product> productExisting  = findById(id);
			if(productExisting.isPresent()) {
				
				em.getTransaction().begin();
				em.remove(productExisting.get());
				em.getTransaction().commit();
			}
			else {
				throw new Exception(Constantes.deleteProductFailed);
			}
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			log.error(Constantes.deleteProductError + e.getMessage());
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
	public Optional<Product> findById(Long id){
		Product product = null;
		try{
			em = JpaUtils.startConnection();
			product = em.find(Product.class, id);
		}
		catch (Exception e) {
			log.error(Constantes.findProductError + e.getMessage());
		}
		finally {
			try {
				JpaUtils.closeConnection(em);
			}
			catch(Exception e){
				log.error(Constantes.closeConnectionError + e.getMessage());
			}
		}
		return Optional.ofNullable(product);
	}

}
