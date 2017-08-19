package daojpa;

import java.util.Date;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Sala;
import modelo.Sessao;

public class DAOSessao extends DAO<Sessao>{
	public Sessao localizarSessaoPorId (int id){
		try{
			Query query = manager.createNamedQuery("Sessao.consultarSessao");
			query.setParameter("id", id);
			return (Sessao) query.getSingleResult();
			
		}catch(NoResultException e){		
			return null;
		}
	}
	public Sessao localizarSessaoEmSala(int idsessao, int idsala){
		try {
			Query query = manager.createNamedQuery("Sessao.consultarSessaoEmSala");
			query.setParameter("idsala", idsala);
			query.setParameter("idsessao", idsessao);
			return (Sessao) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	public Sessao localizarSessao(Date horario, String nome_sala, String nome_filme, Date data){
		try {
			Query query = manager.createQuery("SELECT s FROM Sessao s WHERE s.horario.hora = :horario and s.filme.titulo = :titulo and s.sala.nome = :sala and s.data = :data");
			query.setParameter("horario", horario);
			query.setParameter("sala", nome_sala);
			query.setParameter("titulo", nome_filme);
			query.setParameter("data", data);
			return (Sessao) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
