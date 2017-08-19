package daodb4o;

import modelo.*;

import java.util.Date;
import java.util.List;
import com.db4o.query.*;

public class DAOAdministrador extends DAO<Administrador> {
	
	public Administrador localizarPorEmail(String email){
		Query q = manager.query();
		q.constrain(Administrador.class);
		q.descend("email").constrain(email);
		List<Administrador> resultados = q.execute();
		if( resultados.size() > 0){
			return resultados.get(0);
		}else{
			return null;
		}
	}
	
//	public Administrador adminComMenosPendencias(){
//		Query q = manager.query();
//		q.constrain(Administrador.class);
//		List<Administrador> resultados = q.execute();
//		return resultados;
//	}
	
	// Não funcionou
	public Comentario pendenciaMaisAntiga(String email){
		Query q = manager.query();
		q.constrain(Administrador.class);
		q.descend("email").constrain(email);
		q.descend("pendencias");
		List<Administrador> resultados = q.execute();
		System.out.println(resultados);
		if( resultados.size() > 0){
			return resultados.get(0).getPendencias().get(0);
		}else{
			return null;
		}
	}
	public List<Administrador> administradorComMaisDe1Postagem(){
		Query q = manager.query();
		q.constrain(Administrador.class);
		q.constrain(new Filtro());
		List<Administrador> resultados = q.execute();
		return resultados;
	}
	
	@SuppressWarnings("serial")
	public class Filtro implements Evaluation{
		@Override
		public void evaluate(Candidate candidate) {
			Administrador admin = (Administrador) candidate.getObject();
			candidate.include(admin.getPostagens().size() > 1);
		}
	}
	
}

