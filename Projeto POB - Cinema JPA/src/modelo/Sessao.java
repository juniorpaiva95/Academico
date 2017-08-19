package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.Utilitaries;

@NamedQueries({
	@NamedQuery(name = "Sessao.consultarTodos",
		    query= "SELECT s FROM Sessao s"),
	@NamedQuery(name = "Sessao.consultarSessao",
    query= "SELECT s FROM Sessao s WHERE s.id = :id"),
	@NamedQuery(name = "Sessao.consultarSessaoEmSala",
    query= "SELECT s.filme FROM Sessao s JOIN s.sala sal WHERE sal.id = :idsala and s.id = :idsessao")
	})
@Entity
public class Sessao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data;
	@ManyToOne
	private Sala sala;
	@ManyToOne
	private Horario horario;
	@ManyToOne
	private Filme filme;
	
	public Sessao() {}
	public Sessao(Sala sala, Filme filme, Horario horario, Date data) {
		super();
		this.sala = sala;
		this.filme = filme;
		this.horario = horario;
		this.data = data;
	}
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at = new Date();

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	@Override
	public String toString() {
		return "Sessao Id:" + id + "\n" + (data != null ? "Data: " + Utilitaries.formatarData(data)+ "\n" : "")
				+ (sala != null ? "Sala: " + sala.getNome() + "\n" : "") + (horario != null ? "Horario: " + Utilitaries.formatarHora(horario.getHora()) + "\n" : "")
				+ (filme != null ? "Filme: " + filme.getTitulo() : "") + "\n";
	}
}
