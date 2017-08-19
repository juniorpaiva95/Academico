package daojpa;import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Cargo;

public class DAOCargo extends DAO<Cargo>{
	
	public Cargo localizarPeloNome (String nome){
		try{
			Query query = manager.createNamedQuery("Cargo.consultarPorNome");
			query.setParameter("nome", nome);
			return (Cargo) query.getSingleResult();
			
		}catch(NoResultException e){		
			return null;
		}
	}
	
}
