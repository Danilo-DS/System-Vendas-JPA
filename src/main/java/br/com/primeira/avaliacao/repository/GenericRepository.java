package br.com.primeira.avaliacao.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * @author DS
 *
 */
public interface GenericRepository<T, ID extends Serializable> {

	public void update(ID id, T entity);
	
	public void save(T entity);
	
	public Collection<T> findAll();
	
	public void delete(ID id);
	
	public Optional<T> findById(ID id); 
}
