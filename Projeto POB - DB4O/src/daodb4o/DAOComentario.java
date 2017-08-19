package daodb4o;

import modelo.*;
import java.util.List;
import com.db4o.query.*;

public class DAOComentario extends DAO<Comentario>{

	public Comentario localizarPeloId (int id){
		Query q = manager.query();
		q.constrain(Comentario.class);
		q.descend("id").constrain(id);
		List<Comentario> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
}

