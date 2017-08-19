package fachada;

import java.text.SimpleDateFormat;
//Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TreeMap;

import modelo.*;
import utils.Helpers;
import utils.ShowMe;

public class Fachada {
	private static Forum forum = new Forum("Blog IFPB - POO");
	private static int idpostagem = 0;
	private static int idcomentario = 0;
	
	//Metodos GET	
	public static String getNomeForum(){
		return forum.getNome();
	}
	//Metodos SET
	
	
	//Metodos Estáticos e Funcionalidades
	public static Usuario cadastrarAdministrador(String nome, String email) throws Exception{
		Usuario u = forum.localizarUsuarioPorEmail(email);
		if(u != null){
			throw new Exception("Opss!! Já existe um usuário cadastrado com esse e-mail: " + email);
		}
		if(!Helpers.validaEmail(email)){
			throw new Exception("Email informado inválido!!");
		}
		Administrador novo = new Administrador(nome,email);
		forum.addAdministrador(novo);
		forum.addUsuario(novo);
		return novo;
	}
	public static Usuario cadastrarUsuario(String nome,String email) throws Exception{
		Usuario u = forum.localizarUsuarioPorEmail(email);
		if(u != null){
			throw new Exception("Opss!! Já existe um usuário cadastrado com esse e-mail: " + email);
		}
		if(!Helpers.validaEmail(email)){
			throw new Exception("Email informado inválido!!");
		}
		Usuario novo = new Usuario(nome,email);
		forum.addUsuario(novo);
		System.out.println("Usuário " + novo.getNome() +" cadastrado com sucesso");
		return novo;
	}
	public static String listarUsuarios(){
		TreeMap<String,Usuario> aux = forum.getUsuarios();
		//Concatenando lista de usuarios com lista de administradores
		
		String texto= "\n--LISTAGEM DE USUÁRIOS CADASTRADOS--\n";
		if(aux.isEmpty()){
			texto += "\nAinda não temos usuários cadastrados.";
		}
		for(String email: aux.keySet()){
			texto += aux.get(email);
		}
		return texto;
	}
	public static String listarUsuariosAdministradores(){
		ArrayList<Administrador> aux = forum.getAdministradores();
		String texto= "\n--LISTAGEM DE USUÁRIOS ADMINISTRADORES--\n";
		if(aux.isEmpty()){
			texto += "\nAinda não temos administradores cadastrados.";
		}
		for(Administrador a: aux){
			texto += a.toString() +"\n";
		}
		return texto;
	}
	
	public static Postagem postar(String topico, String texto,String email) throws Exception{
		Usuario autor = forum.localizarUsuarioPorEmail(email);
		if(autor == null){
			throw new Exception("Autor não cadastrado!!");
		}
		//Criando uma nova instancia em Postagem
		Postagem p = new Postagem(++idpostagem, autor, topico, texto);
		//Relacionando a postagem com o forum		
		forum.addPostagem(p);
		//Relacionando a postagem com o usuário		
		autor.addPostagem(p);
		System.out.println("Postagem ("+topico+") realizada com sucesso!!");
		return p;
	}
	
	public static String listarMinhasPostagens(String email) throws Exception{
		Usuario u = forum.localizarUsuarioPorEmail(email);
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
		Postagem p = forum.localizarPostagemPorId(idpostagem);
		if(p == null){
			throw new Exception("Postagem não encontrada com esse parametro");
		}
		//Incrementando o acesso da postagem
		p.incrementarAcesso();
		//Preparando o retorno
		return ShowMe.showPost(p);
	}
	public static Comentario comentar(int idpost, String comentario, String email_user) throws Exception{
		Postagem p = forum.localizarPostagemPorId(idpost);
		if(p == null){
			throw new Exception("Postagem não encontrada com esse parametro");
		}
		Usuario autor = forum.localizarUsuarioPorEmail(email_user);
		if(autor == null){
			throw new Exception("Usuário não encontrado com esse email");
		}
		if(forum.getAdministradores().size() == 0){
			throw new Exception("Comentário não poderá ser aprovado, pois não temos administradores cadastrados.");
		}
		//Capturar o Administrador com menos comentários pendentes
		Administrador adm = forum.getAdministradores().get(0);
		for(Administrador a: forum.getAdministradores()){
			//Se o administrador corrente tiver um numero menor de pendencias guardaremos sua referência
			if(a.getPendencias().size() < adm.getPendencias().size()){
				adm = a;
			}
		}
		Comentario c = new Comentario(++idcomentario, autor, comentario);
		p.addComentario(c);
		adm.addComentario(c);
		return c;
	}
	public static Comentario obterComentarioPendente(String email) throws Exception{
		Usuario adm = forum.localizarUsuarioPorEmail(email);
		if(adm == null){
			throw new Exception("Nenhum usuário encontrado com o email: "+email);
		}
		if(!(adm instanceof Administrador)){
			throw new Exception("Operação não permitida para um usuário não Administrador");
		}
		if(((Administrador)adm).getPendencias().size() == 0){
			throw new Exception("O administrador "+adm.getNome()+ " ainda não possui comentários pendentes");
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
		ArrayList<Postagem> postagens = forum.getPostagens();
		if(postagens.isEmpty()){
			throw new Exception("Ainda não existem postagens nesse Forúm");
		}	
		return retorno + ShowMe.showPosts(Helpers.postagemMaisAcessada(postagens));
	}
	
	public static String postagensSingle() throws Exception{
		String retorno = "--> POSTAGENS SEM COMENTÁRIOS <--";
		ArrayList<Postagem> postagens = forum.getPostagens();
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
		ArrayList<Postagem> postagens = forum.getPostagens();
		
		if(postagens.isEmpty()){
			throw new Exception("Ainda não existem postagens nesse Forúm");
		}
		//Se o parametro for vazio retorna todas as postagens do forum		
		if(termo.equals("")){
			retorno = "--> POSTAGENS ENCONTRADAS ("+ postagens.size() +") <--";
			return retorno + ShowMe.showPosts(postagens);
		}
		//Array auxiliar que irá conter os posts com o parametro buscado
		ArrayList<Postagem> aux = new ArrayList<Postagem>();
		for(Postagem p: postagens){
			if(p.getTopico().toUpperCase().contains(termo.toUpperCase()) || p.getTexto().toUpperCase().contains(termo.toUpperCase()) ){
				aux.add(p);
			}
		}
		retorno = "--> POSTAGENS ENCONTRADAS ("+ aux.size() +") <--";
		return retorno + ShowMe.showPosts(aux);
	}
}
