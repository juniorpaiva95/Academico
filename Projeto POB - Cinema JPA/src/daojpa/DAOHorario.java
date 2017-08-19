package daojpa;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.*;

public class DAOHorario extends DAO<Horario>{
	
	public Horario localizarHorario(Date hora){
		try{
			Query query = manager.createNamedQuery("Horario.consultarHora");
			query.setParameter("hora", hora);
			return (Horario) query.getSingleResult();
		}catch(NoResultException e){			
			return null;
		}
	}
}
