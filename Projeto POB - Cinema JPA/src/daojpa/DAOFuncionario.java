/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;


import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Cargo;
import modelo.Funcionario;

public class DAOFuncionario  extends DAO<Funcionario>{

	public Funcionario localizarPeloNome (String n){
		try{
			Query query = manager.createNamedQuery("Funcionario.consultarPorNome");
			query.setParameter("nome", n);
			return (Funcionario) query.getSingleResult();
		}catch(NoResultException e){			
			return null;
		}
	}
	//consultas
	
	public List<Funcionario> consultarFuncionariosGerente(String cargo){
		try {
			Query query = manager.createNamedQuery("Cargo.consultarPorNome");
			query.setParameter("nome", cargo);
			Cargo c = (Cargo) query.getSingleResult();
			
			query = manager.createNamedQuery("Funcionario.consultarGerentes");
			query.setParameter("cargo", c.getNome());
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Funcionario> consultarAniversariantesDoMes(){
		try {
			Date month = new Date();
			Query query = manager.createQuery("SELECT f FROM Funcionario f WHERE EXTRACT(MONTH FROM f.data_nasc) = :data");
			query.setParameter("data", month.getMonth() + 1);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	

}