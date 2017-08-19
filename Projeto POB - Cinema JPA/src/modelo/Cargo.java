package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name = "Cargo.consultarTodos",
		    query= "SELECT c FROM Cargo c"),
	@NamedQuery(name = "Cargo.consultarPorNome",
    query= "SELECT c FROM Cargo c WHERE c.nome = :nome")
	})
@Entity
public class Cargo {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false,unique=true)
	private String nome;
	private Double salario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at = new Date();
	
	@OneToMany(mappedBy="cargo")
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	public Cargo(){}
	public Cargo(String nome, Double salario){
		this.nome = nome;
		this.salario = salario;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return "ID: " + id + "\nCargo :" + nome + "\nSalario: R$ " + salario + "\nData de criação:" + f.format(created_at)
				+ "\nUltima Atualização:" + f.format(updated_at);
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}
