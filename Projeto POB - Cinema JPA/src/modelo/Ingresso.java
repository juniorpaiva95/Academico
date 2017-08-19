package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;

@Entity
public class Ingresso {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double valor_meia;
	private double valor_inteira;
	private int quantidade;
	@OneToOne
	private Filme filme;
	@OneToOne
	private Sessao sessao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at = new Date();
	
	public Ingresso() {}

	public Ingresso(double valor_meia, double valor_inteira, int quantidade, Filme filme, Sessao sessao) {
		super();
		this.valor_meia = valor_meia;
		this.valor_inteira = valor_inteira;
		this.quantidade = quantidade;
		this.filme = filme;
		this.sessao = sessao;
	}
}
