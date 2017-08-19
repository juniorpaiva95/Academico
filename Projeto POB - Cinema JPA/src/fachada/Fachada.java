package fachada;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.persistence.NoResultException;

import daojpa.DAO;
import daojpa.*;
import modelo.*;
import util.Utilitaries;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

public class Fachada {
	
	private static DAOFuncionario daofuncionario = new DAOFuncionario();
	private static DAOCargo daocargo = new DAOCargo();
	private static DAOFilme daofilme = new DAOFilme();
	private static DAOSala daosala = new DAOSala();
	private static DAOSessao daosessao = new DAOSessao();
	private static DAOHorario daohorario = new DAOHorario();
	
	public static void inicializar(){
		DAO.abrir();
	}
	
	public static void finalizar(){
		DAO.fechar();
	}
	
	public static Cargo cadastrarCargo(String nome, double salario) 
			throws  Exception{
		DAO.iniciar();
		
		Cargo c = daocargo.localizarPeloNome(nome);
		if(c != null) {
			DAO.cancelar();
			throw new Exception("Cargo ja cadastrado: " + nome);
		}
		if (salario <= 0){
			DAO.cancelar();
			throw new Exception("salario inválido:" + salario);
		}
		
		c = new Cargo(nome,salario);
		daocargo.persistir(c);
		
		DAO.efetivar();
		return c;
	}
	
//	Funcionario
	public static List<Funcionario> listarTodosFuncionarios() throws Exception{
		try {
			return (List<Funcionario>) daofuncionario.listar();
		} catch (NoResultException e) {
			return null;
		}
	}
	public static Funcionario cadastrarFuncionario(String nome, Date data_nasc, String cargo) throws Exception {
		DAO.iniciar();
		Funcionario f = daofuncionario.localizarPeloNome(nome);
		Cargo c = daocargo.localizarPeloNome(cargo);
		if (f != null) {
			throw new Exception("Funcionario " + nome + "já cadastrado!");
		}
		if (c == null) {
			throw new Exception("Cargo " + cargo + " não cadastrado!");
		}
		daofuncionario.persistir(new Funcionario(nome, data_nasc, c));
		DAO.efetivar();
		return f;
	}
	public static void apagarFuncionario(String nome) throws Exception{
		DAO.iniciar();
		
		Funcionario f = daofuncionario.localizarPeloNome(nome);
		if (f == null) {
			throw new Exception("Funcionario " + nome + "não cadastrado!");
		}
		daofuncionario.apagar(f);
		DAO.efetivar();
	}
	public static Cargo atualizarCargo(String nome, String novo_nome, double novo_salario) throws Exception{
		DAO.iniciar();
		
		Cargo c = daocargo.localizarPeloNome(nome);
		if(c == null){
			throw new Exception("Cargo não encontrado: " + nome);
		}
		
		c.setNome(novo_nome);
		c.setSalario(novo_salario);
		c.setUpdated_at(new Date());
		daocargo.atualizar(c);
		DAO.efetivar();
		return c;
	}
	public static Cargo removerCargo(String nome) throws Exception{
		DAO.iniciar();
		Cargo c = daocargo.localizarPeloNome(nome);
		if(c == null){
			throw new Exception("Cargo não encontrado: " + nome);
		}
		for (Funcionario f : c.getFuncionarios()) {
			f.setCargo(null);
			daofuncionario.atualizar(f);
		}
		daocargo.apagar(c);
		
		DAO.efetivar();
		return c;
	}
	
//	Filmes
	public static List<Filme> listarFilmes() {
		return daofilme.listar();
	}
	public static Filme cadastrarFilme(int duracao, String autor, int censura, String sinopse, String titulo, Date data ) throws Exception {
		DAO.iniciar();
		
		Filme f = daofilme.localizarPeloTitulo(titulo);
		if(f != null ){
			throw new Exception("Filme " + f.getTitulo() + "já cadastrado!");
		}
		if (censura < 0) {
			throw new Exception("Censura inválida, forneça uma censura válida.");
		}
		
		daofilme.persistir(new Filme(duracao,autor, censura, sinopse, titulo, data));
		DAO.efetivar();
		return f;
	}
	
//	Sala
	public static Sala cadastrarSala(String nome, int capacidade) throws Exception{
		DAO.iniciar();
		
		Sala s = daosala.localizarPeloNome(nome);
		
		if(s != null) {
			throw new Exception("Sala " + s.getNome()+ "já cadastrada!");
		}
		if(capacidade <= 20) {
			throw new Exception("Tamanho da sala deve ter capacidade superior a 20 pessoas");
		}
		daosala.persistir(new Sala(nome,capacidade));
		DAO.efetivar();
		return s;
	}
	
//	Sessao
	public static List<Sessao> listarSessoes() {
		return daosessao.listar();
	}
	public static Sessao cadastrarSessao(Date data, String nome_sala, Date hora, String nome_filme) throws Exception{
		DAO.iniciar();
		
		
		Sala sala = daosala.localizarPeloNome(nome_sala);
		Filme filme = daofilme.localizarPeloTitulo(nome_filme);
		Horario horario = daohorario.localizarHorario(hora);
		Sessao sessao = daosessao.localizarSessao(hora, nome_sala, nome_filme, data);
		if(sala == null) {
			throw new Exception("Sala não localizada");
		}
		if(filme == null){
			throw new Exception("Filme não localizado");
		}
		// Se não existir o horário no banco ele cadastra
		if(horario == null) {
			horario = new Horario(hora);
			daohorario.persistir(horario);
		}
		if(sessao != null) {
			throw new Exception(
				"Já existe uma sessão cadastrada com os parametros abaixo!" +
				"\nSala: " + sala.getNome() + "\nFilme: " + filme.getTitulo() + "\nHorario: " + horario.getHora()
			);
		}
		sessao = new Sessao(sala,filme,horario, data);
		daosessao.persistir(sessao);
		DAO.efetivar();
		return sessao;
	}
	
	//Horario
	public static Horario cadastrarHorario(Date hora) throws Exception{
		DAO.iniciar();
		
		Horario h = daohorario.localizarHorario(hora);
		if(h != null){
			throw new Exception("Horario já cadastrado: " + Utilitaries.formatarHora(h.getHora()));
		}
		h = new Horario(hora);
		daohorario.persistir(h);
		DAO.efetivar();
		return h;
	}
	
	//Consultas
	
	public static List<Filme> consultarFilmesAdulto() throws Exception{
			List<Filme> filmes = daofilme.localizarFilmesAdultos();
			return filmes;
	}
	
	public static List<Funcionario> consultarGerentes(String cargo) throws Exception {
		List<Funcionario> funcionarios = daofuncionario.consultarFuncionariosGerente(cargo);
		return funcionarios;
	}
}
