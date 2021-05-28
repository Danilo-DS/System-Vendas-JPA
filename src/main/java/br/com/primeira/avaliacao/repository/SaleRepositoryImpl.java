package br.com.primeira.avaliacao.repository;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.primeira.avaliacao.model.Sale;
import br.com.primeira.avaliacao.util.Constantes;
import br.com.primeira.avaliacao.util.JpaUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DS
 *
 */

@Slf4j
public class SaleRepositoryImpl implements SaleRepository{

	private EntityManager em;
	
	@Override
	public void update(Long id, Sale sale){
		try{
			em = JpaUtils.startConnection();
			Optional<Sale> saletExisting  = findById(id);
			if(saletExisting.isPresent()) {
				sale.setId(id);
				
				em.getTransaction().begin();
				em.merge(sale);
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
	public void save(Sale sale){
		try{
			em = JpaUtils.startConnection();
			em.getTransaction().begin();
			em.persist(sale);
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
	public Collection<Sale> findAll(){
		try {
			em = JpaUtils.startConnection();
			return em.createNamedQuery("Sale.findAll", Sale.class).getResultList();
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
			Optional<Sale> saleExistent  = findById(id);
			if(saleExistent.isPresent()) {
				
				em.getTransaction().begin();
				em.remove(saleExistent.get());
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
	public Optional<Sale> findById(Long id){
		Sale sale = null;
		try{
			em = JpaUtils.startConnection();
			sale = em.find(Sale.class, id);
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
		return Optional.ofNullable(sale);
	}

}
