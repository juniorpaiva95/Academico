package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Cargo;
import modelo.Sala;

public class DAOSala extends DAO<Sala>{
	
	public Sala localizarPeloNome (String nome){
		try{
			Query query = manager.createNamedQuery("Sala.consultarPorNome");
			query.setParameter("nome", nome);
			return (Sala) query.getSingleResult();
			
		}catch(NoResultException e){		
			return null;
		}
	}
	public Sala localizarPeloId (int id) {
		try {
			Query query = manager.createNamedQuery("Sala.consultarPorId");
			query.setParameter("id", id);
			return (Sala) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
