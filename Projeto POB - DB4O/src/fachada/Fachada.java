package fachada;

import java.text.SimpleDateFormat;
//Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import daodb4o.*;
import modelo.*;
import utils.Helpers;
import utils.ShowMe;

public class Fachada {
	private static Forum forum = new Forum("Blog IFPB - POO");
	
	private static DAOPostagem daopostagem = new DAOPostagem();
	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOAdministrador daoadministrador = new DAOAdministrador();
	private static DAOComentario daocomentario = new DAOComentario();
	
	private static int idpostagem = 0;
	private static int idcomentario = 0;
	
	public static void inicializar(){
		DAO.abrir();
	}

	public static void finalizar(){
		DAO.fechar();
	}
	
	//Metodos GET	
	public static String getNomeForum(){
		return forum.getNome();
	}
	//Metodos SET
	
	
	//Metodos Estáticos e Funcionalidades
	public static Usuario cadastrarAdministrador(String nome, String email) throws Exception{
		Usuario u = daousuario.localizarPeloEmail(email);
		if(u != null){
			throw new Exception("Opss!! Já existe um usuário cadastrado com esse e-mail: " + email);
		}
		if(!Helpers.validaEmail(email)){
			throw new Exception("Email informado inválido!!");
		}
		DAO.iniciar();
		Administrador novo = new Administrador(nome,email);
		daoadministrador.persistir(novo);
		DAO.efetivar();
		System.out.println("Administrador " + novo.getNome() +" cadastrado com sucesso");
		return novo;
	}
	public static Usuario cadastrarUsuario(String nome,String email) throws Exception{
		Usuario u = daousuario.localizarPeloEmail(email);
		if(u != null){
			throw new Exception("Opss!! Já existe um usuário cadastrado com esse e-mail: " + email);
		}
		if(!Helpers.validaEmail(email)){
			throw new Exception("Email informado inválido!!");
		}
		
		DAO.iniciar();
		Usuario novo = new Usuario(nome,email);
		daousuario.persistir(novo);
		DAO.efetivar();
		System.out.println("Usuário " + novo.getNome() +" cadastrado com sucesso");
		return novo;
	}
	public static String listarUsuarios(){
		List<Usuario> aux = daousuario.listar();
		//Concatenando lista de usuarios com lista de administradores
		
		String texto= "\n--LISTAGEM DE USUÁRIOS CADASTRADOS--\n";
		if(aux.isEmpty()){
			texto += "\nAinda não temos usuários cadastrados.";
		}
		for(Usuario u: aux){
			texto += u + "\n";
		}
		return texto;
	}
	public static String listarUsuariosAdministradores(){
		List<Administrador> aux = daoadministrador.listar();
		String texto= "\n--LISTAGEM DE USUÁRIOS ADMINISTRADORES--\n";
		if(aux.isEmpty()){
			texto += "\nAinda não temos administradores cadastrados.";
		}
		for(Administrador a: aux){
			texto += a.toString() +"\n";
		}
		return texto;
	}
	
	public static Postagem postar(String email,String topico, String texto) throws Exception{
		DAO.iniciar();		
		Usuario autor = daousuario.localizarPeloEmail(email);
		if(autor == null){
			DAO.cancelar();
			throw new Exception("Autor não cadastrado!!");
		}
		//Criando uma nova instancia em Postagem
		Postagem p = new Postagem(autor, topico, texto);
		daopostagem.persistir(p);
		//Ajustando o relacionamento
		autor.addPostagem(p);
		daousuario.atualizar(autor);
		DAO.efetivar();
		System.out.println("Postagem ("+topico+") realizada com sucesso!!");
		return p;
	}
	
	public static String listarMinhasPostagens(String email) throws Exception{
		Usuario u = daousuario.localizarPeloEmail(email);
		if(u == null){
			throw new Exception("Autor não cadastrado!!");
		}
		String postagens = "--LISTAGEM DE POSTS ("+u.getNome()+")--\n\n";
		if(u.getPostagens().isEmpty()){
			postagens += "Este autor ainda não escreveu seus Posts";
		}
		for(Postagem p: u.getPostagens()){
			postagens += p.toString()+"\n";
		}
		return postagens;
		
	}
	public static String lerPostagem(int idpostagem) throws Exception{
		Postagem p = daopostagem.localizarPeloId(idpostagem);
		if(p == null){
			throw new Exception("Postagem não encontrada com esse parametro");
		}
		DAO.iniciar();
		//Incrementando o acesso da postagem
		p.incrementarAcesso();
		daopostagem.atualizar(p);
		DAO.efetivar();
		//Preparando o retorno
		return ShowMe.showPost(p);
	}
	public static Comentario comentar(int idpost, String comentario, String email_user) throws Exception{
		Postagem p = daopostagem.localizarPeloId(idpost);
		if(p == null){
			throw new Exception("Postagem não encontrada com esse parametro");
		}
		Usuario autor = daousuario.localizarPeloEmail(email_user);
		if(autor == null){
			throw new Exception("Usuário não encontrado com esse email");
		}
		if(daopostagem.totalPostagens() == 0){
			throw new Exception("Comentário não poderá ser aprovado, pois não temos administradores cadastrados.");
		}
		//Capturar o Administrador com menos comentários pendentes
		Administrador adm = daoadministrador.listar().get(0);
		for(Administrador a: daoadministrador.listar()){
			//Se o administrador corrente tiver um numero menor de pendencias guardaremos sua referência
			if(a.getPendencias().size() < a.getPendencias().size()){
				adm = a;
			}
		}
		DAO.iniciar();
		//Salvar o comentario
		Comentario c = new Comentario(autor, comentario, p);
		daocomentario.persistir(c);
		//Atualizar o admin com a nova pendencia
		adm.addComentario(c);
		daoadministrador.atualizar(adm);
		DAO.efetivar();
		return c;
	}
	public static Comentario obterComentarioPendente(String email) throws Exception{
		Usuario adm = daoadministrador.localizarPorEmail(email);
		if(adm == null){
			throw new Exception("Nenhum administrador encontrado com o email " + email);
		}
		
		if (((Administrador)adm).getPendencias().isEmpty()) {
			throw new Exception("Nenhum comentario pendente");
		}
		
		Comentario aux = ((Administrador)adm).getPendencias().get(0);
		for(Comentario c: ((Administrador)adm).getPendencias()){
			if(c.getData().before(aux.getData())){
				aux = c;
			}
		}
		return aux;
	}
	public static String postagensTop() throws Exception{
		String retorno = "\n--POSTAGENS TOP--\n";
		List<Postagem> postagens = daopostagem.listar();
		if(postagens.isEmpty()){
			throw new Exception("Ainda não existem postagens nesse Forúm");
		}
		return retorno + ShowMe.showPosts(Helpers.postagemMaisAcessada(postagens));
	}
	
	public static String postagensSingle() throws Exception{
		String retorno = "--> POSTAGENS SEM COMENTÁRIOS <--";
		List<Postagem> postagens = daopostagem.listar();
		ArrayList<Postagem> aux = new ArrayList<Postagem>();
		
		if(postagens.isEmpty()){
			throw new Exception("Ainda não existem postagens nesse Forúm");
		}
		
		for(Postagem p: postagens){
			if(p.getComentarios().isEmpty()){
				aux.add(p);
			}
		}
		
		if(aux.isEmpty()){
			throw new Exception("Não há nenhuma postagem sem comentários");
		}
		
		return retorno + ShowMe.showPosts(aux);
	}
	
	public static String buscarPostagens(String termo) throws Exception{
		String retorno = "";
		List<Postagem> postagens = daopostagem.buscarPostagens(termo);
		if(postagens.isEmpty()){
			throw new Exception("Ainda não existem postagens nesse Forúm");
		}
		retorno = "--> POSTAGENS ENCONTRADAS ("+ postagens.size() +") <--";
		return retorno + ShowMe.showPosts(postagens);
	}
	
	public static void processarComentarioPendente(String email, String aprovacao)
			throws Exception{
		Administrador adm = daoadministrador.localizarPorEmail(email);
		
		if(adm == null){
			throw new Exception("Usuário não encontrado com este email: "+ email);
		}
		
		Comentario c = obterComentarioPendente(email);
		
		if(!(adm instanceof Administrador)){
			throw new Exception("O usuário não é um administrador");
		}
		
		if(aprovacao.equals("sim")){
			Postagem p = c.getPostagem();
			p.addComentario(c);
			daopostagem.atualizar(p);
			
			Usuario u = c.getAutor();
			u.addComentario(c);
			daousuario.atualizar(u);
			
			adm.getPendencias().remove(c);
			daoadministrador.atualizar(adm);
			System.out.println("Comentário aprovado");
			}else{
				adm.getPendencias().remove(c);
				daoadministrador.atualizar(adm);
				System.out.println("Comentário reprovado");
			}

	}
	
	public static void excluirComentario(int idpostagem,int idcomentario,String email) throws Exception{
		Postagem p = daopostagem.localizarPeloId(idpostagem);
		Usuario u = daousuario.localizarPeloEmail(email);
		if(p == null){
			throw new Exception("Postagem não encontrada com o id: "+idpostagem);
		}
		Comentario c = daocomentario.localizarPeloId(idcomentario);
		if(c == null){
			throw new Exception("Comentário não encontrado na postagem: "+ p.getTopico());
		}
		if(u == null){
			throw new Exception("Usuário não encontrado com o email: "+ email);
		}
		if(!c.getAutor().getEmail().equals(email)){
			throw new Exception("Não é possível remover o comentário de outro autor");
		}else{
			DAO.iniciar();
			//Removendo o comentário do usuário
			u.getComentarios().remove(c);
			daousuario.apagar(u);
			//Removendo o comentário da postagem
			p.getComentarios().remove(c);
			daopostagem.atualizar(p);
			DAO.efetivar();
		}
	}
	
//	Consultas
	public static String usuariosSemComentarios() throws Exception{
		List<Usuario> users = daousuario.usuariosSemComentarios();
		if(users == null || users.isEmpty()){
			throw new Exception("Nenhum usuário encontrado!");
		}
		String retorno = "Usuários sem Comentários ( " + users.size() + " )";
		for(Usuario u: users){
			retorno += "\n" + u;
		}
		return retorno;
	}
	// N funcionou
	public static String comentarioMaisAntigo(String email) throws Exception{
		Comentario comentario = daoadministrador.pendenciaMaisAntiga(email);
		String retorno = "Comentários\n";
		if(comentario == null){
			throw new Exception("Nenhum comentario encontrado!");
		}
		retorno += comentario;
		return retorno;
	}
	
	public static String administradorComMaisDe1Postagem() throws Exception{
		List<Administrador> adms = daoadministrador.administradorComMaisDe1Postagem();
		String retorno = "Comentários\n";
		if(adms.isEmpty()){
			throw new Exception("Nenhum administrador com mais de 1 postagem encontrado!");
		}
		for(Administrador a: adms){
			retorno += "\n" + a;
		}
		return retorno;
	}
	
	public static String postagensByMonth(Date data) throws Exception{
		String retorno = "Postagens\n";
		List<Postagem> postagens = daopostagem.postagemByMonth(data);
		if(postagens.isEmpty()){
			throw new Exception("Nenhuma postagem encontrada no mês: " + data.getMonth());
		}
		for(Postagem p: postagens){
			retorno += "\n" + p;
		}
		return retorno;
	}
	
	public static String postagensSemComentarios() throws Exception{
		List<Postagem> postagens = daopostagem.postagemSemComentarios();
		if(postagens == null || postagens.isEmpty()){
			throw new Exception("Nenhuma postagem encontrada!");
		}
		String retorno = "Postagens sem Comentários ( " + postagens.size() + " )";
		for(Postagem p: postagens){
			retorno += "\n" + p;
		}
		return retorno;
	}
	
	public static String postagemMaiorAcesso() throws Exception{
		String retorno = "Postagem mais acessada";
		Postagem postagem = daopostagem.postagemMaiorAcesso();
		
		if(postagem == null){
			throw new Exception("Ainda não temos postagens");
		}
		retorno += "\n" + postagem;
		
		return retorno;
	}
	
}
