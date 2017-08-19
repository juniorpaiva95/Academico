package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name = "Horario.consultarTodos",
		    query= "SELECT h FROM Horario h"),
	@NamedQuery(name = "Horario.consultarHora",
    query= "SELECT h FROM Horario h WHERE h.hora = :hora"),
	})

@Entity
public class Horario {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.TIME)
	private Date hora;
	@OneToMany(mappedBy="horario")
	private List<Filme> filmes = new ArrayList<Filme>();
	@OneToMany(mappedBy="horario")
	private List<Sessao> sessoes = new ArrayList<Sessao>();
	
	public Horario() {}

	public Horario(Date hora) {
		super();
		this.hora = hora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	public void adicionarFilme(Filme f){
		this.filmes.add(f);
	}
	public void adicionarSessao(Sessao s){
		this.sessoes.add(s);
	}
}
