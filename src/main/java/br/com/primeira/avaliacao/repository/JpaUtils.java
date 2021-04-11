package br.com.primeira.avaliacao.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.primeira.avaliacao.util.Constantes;

public class JpaUtils {
	
	private static EntityManagerFactory emf;
	
	public static EntityManager startConnection() {
		
		if(emf == null) {
			return Persistence.createEntityManagerFactory(Constantes.persistenceName).createEntityManager();
		}
		
		return emf.createEntityManager();
	}
	
	public static void closeConnection(EntityManager em) {
		if(emf != null) {
			em.close();
			emf.close();
		}
	}
	
	
}
