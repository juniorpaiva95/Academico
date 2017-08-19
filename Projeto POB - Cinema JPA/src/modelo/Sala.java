package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import modelo.*;


@NamedQueries({
	@NamedQuery(name = "Sala.consultarTodos",
		    query= "SELECT s FROM Sala s"),
	@NamedQuery(name = "Sala.consultarPorNome",
    query= "SELECT s FROM Sala s WHERE s.nome = :nome"),
	@NamedQuery(name = "Sala.consultarPorId",
    query= "SELECT s FROM Sala s WHERE s.id = :id")
	})
@Entity
public class Sala {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String nome;
	private int capacidade;
	@OneToMany(mappedBy="sala")
	private List<Funcionario> funcionarios;
	
	@OneToMany(mappedBy="sala")
	private List<Sessao> sessoes;	
	
	public Sala() {}
	public Sala(String nome, int capacidade) {
		super();
		this.nome = nome;
		this.capacidade = capacidade;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	//
	public void adicionarSessao(Sessao s){
		this.sessoes.add(s);
	}
}
