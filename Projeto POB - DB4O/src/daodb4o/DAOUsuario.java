package daodb4o;

import modelo.*;

import java.util.Date;
import java.util.List;
import com.db4o.query.*;

public class DAOUsuario extends DAO<Usuario>{

	public Usuario localizarPeloEmail (String email){
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("email").constrain(email);
		List<Usuario> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);			
		else
			return null;
	}
	
	public List<Usuario> usuariosSemComentarios(){
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.constrain(new FiltroSemComentario());
		List<Usuario> resultados = q.execute();
		if (resultados.size()>0)
			return resultados;		
		else
			return null;
	}
	@SuppressWarnings("serial")
	public class FiltroSemComentario implements Evaluation{
		
		@Override
		public void evaluate(Candidate candidate) {
			Usuario u = (Usuario) candidate.getObject();
			candidate.include(u.getComentarios().size() == 0);
		}
	}
}