package daodb4o;

import modelo.*;

import java.util.Date;
import java.util.List;
import com.db4o.query.*;

public class DAOPostagem extends DAO<Postagem>{

	public Postagem localizarPeloId (int id){
		Query q = manager.query();
		q.constrain(Postagem.class);
		q.descend("id").constrain(id);
		List<Postagem> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public int totalPostagens(){
		Query q = manager.query();
		q.constrain(Postagem.class);
		List<Postagem> resultados = q.execute();
		return resultados.size();
	}
	
	public List<Postagem> buscarPostagens(String termo){
		Query q = manager.query();
		q.constrain(Postagem.class);
		Constraint firstConstraint = q.descend("topico").constrain(termo).like();
		Constraint secondConstraint = q.descend("texto").constrain(termo).like(); 
		firstConstraint.or(secondConstraint);
		List<Postagem> resultados = q.execute();
		return resultados;
	}
	
	public List<Postagem> postagemByMonth(Date date){
		Query q = manager.query();
		q.constrain(Postagem.class);
		q.constrain(new FiltroByMonth(date));
		List<Postagem> resultados = q.execute();
		return resultados;
	}
	
	public List<Postagem> postagemSemComentarios(){
		Query q = manager.query();
		q.constrain(Postagem.class);
		q.constrain(new FiltroPostagemSemComentarios());
		List<Postagem> resultados = q.execute();
		return resultados;
	}
	
	public Postagem postagemMaiorAcesso() {
		Query q = manager.query();
		q.descend("acessos").orderDescending();
		List<Postagem> resultados = q.execute();
		if(resultados.size()>0){
			return resultados.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("serial")
	public class FiltroByMonth implements Evaluation{
		private Date data;
		
		//Construtor
		public FiltroByMonth(Date data){
			this.data = data;
		}
		
		@Override
		public void evaluate(Candidate candidate) {
			Postagem p = (Postagem) candidate.getObject();
			candidate.include(p.getDate().getMonth() == this.data.getMonth());
		}
	}
	public class FiltroPostagemSemComentarios implements Evaluation{
		@Override
		public void evaluate(Candidate candidate) {
			Postagem p = (Postagem) candidate.getObject();
			candidate.include(p.getComentarios().size() == 0);
		}
	}
}
