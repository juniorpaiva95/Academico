package modelo;

import java.util.*;

public class Forum{
	private String nome;
	TreeMap<String, Usuario> usuarios = new TreeMap<String, Usuario>();
	private ArrayList<Postagem> postagens = new ArrayList<Postagem>();
	private ArrayList<Administrador> administradores = new ArrayList<Administrador>();
	//Construtor
	public Forum(String nome) {
		this.nome = nome;
	}
	// Metodos GET	
	public String getNome(){
		return this.nome;
	}
	public TreeMap<String, Usuario> getUsuarios(){
		return usuarios;
	}
	public ArrayList<Administrador> getAdministradores(){
		return administradores;
	}
	public ArrayList<Postagem> getPostagens(){
		return postagens;
	}
	// Metodos SET
	
	//Funcionalidades - Busca de conteúdo
	public Usuario localizarUsuarioPorNome(String nome){
		if(usuarios.isEmpty()){
			return null;
		}
		if(usuarios.containsKey(nome)){
			return usuarios.get(nome);
		}
		return null;
	}
	public Usuario localizarUsuarioPorEmail(String email){
		if(usuarios.isEmpty()){
			return null;
		}
		if(usuarios.containsKey(email)){
			return usuarios.get(email);
		}
		return null;
	}
	public Postagem localizarPostagemPorId(int id){
		for(Postagem p: postagens){
			if(p.getId() == id){
				return p;
			}
		}
		return null;
	}
	//Funcionalidades de inserção	
	public void addUsuario(Usuario u){
		usuarios.put(u.getEmail(), u);
	}
	public void addAdministrador(Administrador a){
		administradores.add(a);
	}
	public void addPostagem(Postagem p){
		postagens.add(p);
	}

}
