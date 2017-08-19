package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Filme;
import modelo.Funcionario;

public class DAOFilme extends DAO<Filme>{
	public Filme localizarPeloTitulo (String t){
		try{
			Query query = manager.createNamedQuery("Filme.consultarPorTitulo");
			query.setParameter("titulo", t);
			return (Filme) query.getSingleResult();
		}catch(NoResultException e){			
			return null;
		}
	}
	
	public List<Filme> localizarFilmesAdultos() {
		try {
			Query query = manager.createNamedQuery("Filme.filmeAdulto");
			return (List<Filme>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
