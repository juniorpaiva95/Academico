package aplicacao;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import daojpa.*;
import fachada.Fachada;
import modelo.*;

public class TesteAplicacao {
	protected static EntityManager manager;
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat fDataEHora = new SimpleDateFormat("dd/MM/yyyy H:m:s");
		
		Fachada.inicializar();
		try { 
//			Fachada.cadastrarCargo("Gerente", 2000);
//			Fachada.cadastrarCargo("Vendedor", 1000);
//			Fachada.cadastrarCargo("Limpeza", 950);
			
//			Fachada.cadastrarFuncionario("Junior", new Date(format.parse("15/04/1995").getTime()), "Gerente");
//			Fachada.cadastrarFuncionario("Julia", new Date(format.parse("19/08/1985").getTime()), "Limpeza");
//			Fachada.cadastrarFuncionario("Roberta", new Date(format.parse("25/06/1990").getTime()), "Vendedor");
			
//			Fachada.cadastrarFilme(50, "James aragão", 16, "Um filme legal", "Filme legal pakas",new Date(format.parse("25/06/1990").getTime()));
//			Fachada.cadastrarFilme(50, "Pedro Alcantara", 18, "Um filme adulto", "Cinquenta Tons de cinza",new Date(format.parse("25/06/2016").getTime()));
//			Fachada.cadastrarFilme(80, "Francisco", 0, "Filme de criança", "Procurando Nemo",new Date(format.parse("25/06/2010").getTime()));
			
//			Fachada.cadastrarSala("Sala 1", 50);
//			Fachada.cadastrarSala("Sala 2", 30);
//			Fachada.cadastrarSala("Sala 3", 25);
			
//			Date hora = new Date();
//			hora.setHours(17);
//			hora.setMinutes(15);
//			hora.setSeconds(0);
//			Fachada.cadastrarHorario(hora);
			
//			Fachada.cadastrarSessao(new Date(), "Sala 1", hora, "Procurando Nemo");
			
//			System.out.println(Fachada.atualizarCargo("Gerente", "Gerente", 6000).toString());
			for (Funcionario f : Fachada.listarTodosFuncionarios()) {
				System.out.println(f.toString());
			}
			
//			System.out.println(Fachada.removerCargo("Limpeza"));
			// Consultas
			
			//Listar Sessões
			for (Sessao sessao : Fachada.listarSessoes()) {
				System.out.println(sessao.toString());
			}
			for (Filme f : Fachada.listarFilmes()) {
				System.out.println(f.toString());
			}
//			List<Filme> filmes = Fachada.consultarFilmesAdulto();
//				System.out.println(filmes.get(i).getTitulo());
//			}
//			
//			List<Funcionario> funcionarios = Fachada.consultarGerentes("Gerente");
//			for (int i = 0; i < funcionarios.size(); i++) {
//				System.out.println(funcionarios.get(i).getNome());
//			}
			System.out.println("Funcionarios do mês");
			for (Funcionario f : Fachada.consultarAniversariantesDoMes()) {
				System.out.println(f);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
