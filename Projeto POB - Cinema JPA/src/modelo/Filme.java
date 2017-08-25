package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.Utilitaries;

@NamedQueries({
	@NamedQuery(name = "Filme.consultarTodos",
		    query= "SELECT f FROM Filme f"),
	@NamedQuery(name = "Filme.consultarPorTitulo",
    query= "SELECT f FROM Filme f WHERE f.titulo = :titulo"),
	@NamedQuery(name = "Filme.filmeAdulto",
    query= "SELECT f FROM Filme f WHERE f.censura >= 18")
	})
@Entity
public class Filme {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private int duracao;
	@Column(nullable=false)
	private String autor;
	@Column(nullable=false)
	private int censura;
	@Column(nullable=false)
	private String sinopse;
	@Column(nullable=false)
	private String titulo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_lanc = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at = new Date();
	
	@OneToMany(mappedBy="filme")
	private List<Sessao> sessoes = new ArrayList<Sessao>();
	@ManyToOne
	private Horario horario;
	
	public Filme(){}

	public Filme(int duracao, String autor, int censura, String sinopse, String titulo, Date data_lanc) {
		super();
		this.duracao = duracao;
		this.autor = autor;
		this.censura = censura;
		this.sinopse = sinopse;
		this.titulo = titulo;
		this.data_lanc = data_lanc;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getCensura() {
		return censura;
	}

	public void setCensura(int censura) {
		this.censura = censura;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getData_lanc() {
		return data_lanc;
	}

	public void setData_lanc(Date data_lanc) {
		this.data_lanc = data_lanc;
	}

	@Override
	public String toString() {
		String texto_sessoes = "";
		for (Sessao sessao : sessoes) {
			texto_sessoes += "Horário : " + Utilitaries.formatarHora(sessao.getHorario() .getHora())+ "\n\tSala: " + sessao.getSala().getNome();
		}
		return "Filme ID:" + id + "\nDuracao:" + duracao + "min \n" + (autor != null ? "Autor: " + autor + "\n" : "")
				+ "Censura: " + censura + "\n" + (sinopse != null ? "Sinopse: " + sinopse + "\n" : "")
				+ (titulo != null ? "Titulo: " + titulo + "\n" : "")
				+ (data_lanc != null ? "Data de Lançamento: " + data_lanc + "\n" : "")
				+ (sessoes != null ? "Sessoes: \n\t" + texto_sessoes+ " \n" : "") + "\n";
	}
}
