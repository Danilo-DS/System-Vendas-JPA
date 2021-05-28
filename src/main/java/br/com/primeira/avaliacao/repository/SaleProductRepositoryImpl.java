package br.com.primeira.avaliacao.repository;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.primeira.avaliacao.model.SaleProduct;
import br.com.primeira.avaliacao.util.Constantes;
import br.com.primeira.avaliacao.util.JpaUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DS
 *
 */

@Slf4j
public class SaleProductRepositoryImpl implements SaleProductRepository {
	
	private EntityManager em;
	
	@Override
	public void update(Long id, SaleProduct saleProduct){
		try{
			em = JpaUtils.startConnection();
			Optional<SaleProduct> saleProductExisting  = findById(id);
			if(saleProductExisting.isPresent()) {
				saleProduct.setId(id);
				
				em.getTransaction().begin();
				em.merge(saleProduct);
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
	public void save(SaleProduct saleProduct){
		try{
			em = JpaUtils.startConnection();
			em.getTransaction().begin();
			em.persist(saleProduct);
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
	public Collection<SaleProduct> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id){
		try{
			em = JpaUtils.startConnection();
			Optional<SaleProduct> saleProductExistent  = findById(id);
			if(saleProductExistent.isPresent()) {
				
				em.getTransaction().begin();
				em.remove(saleProductExistent.get());
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
	public Optional<SaleProduct> findById(Long id){
		SaleProduct saleProduct = null;
		try{
			em = JpaUtils.startConnection();
			saleProduct = em.find(SaleProduct.class, id);
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
		return Optional.ofNullable(saleProduct);
	}
}
