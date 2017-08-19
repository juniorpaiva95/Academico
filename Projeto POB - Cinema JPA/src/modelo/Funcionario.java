

/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Júnior Paiva
 */

package modelo;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedQueries({
	@NamedQuery(name = "Funcionario.consultarTodos",
		    query= "SELECT f FROM Funcionario f"),
	@NamedQuery(name = "Funcionario.consultarPorNome",
    query= "SELECT f FROM Funcionario f WHERE f.nome = :nome"),
	@NamedQuery(name = "Funcionario.consultarGerentes",
    query= "SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.nome = :cargo")
	})

@Entity 
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

public class Funcionario extends Pessoa{
	@Column(nullable=false, columnDefinition="DATE")
	private Date data_nasc;
	@ManyToOne
	private Cargo cargo;
	@ManyToOne
	private Sala sala;

	public Funcionario(){}
	
	public Funcionario (String nome, Date data_nasc, Cargo cargo){
		super(nome);
		this.data_nasc = data_nasc;
		this.cargo = cargo;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");
		return "Funcionario: " + getNome() + "\nData de Nascimento: " + f2.format(data_nasc)
				+ (cargo != null ? "\nCargo: " + cargo.getNome() + "" : "") + "\n";
	}
	
}