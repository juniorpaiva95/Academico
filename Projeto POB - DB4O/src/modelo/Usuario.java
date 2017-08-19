package modelo;

import java.util.ArrayList;

public class Usuario {
	private String nome;
	private String email;
	private ArrayList<Postagem> postagens = new ArrayList<Postagem>();
	private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
	
	//Construtor		
	public Usuario(String nome, String email){
		this.nome = nome;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", postagens=" + postagens
				+ ", comentarios=" + comentarios + "]";
	}



	//Metodos GET
	public String getNome() {
		return nome;
	}
	public String getEmail(){
		return email;
	}
	public ArrayList<Comentario> getComentarios(){
		return this.comentarios;
	}
	//Metodos SET		
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Postagem> getPostagens(){
		return postagens;
	}
	
	//Metodos funcionais
	public void addPostagem(Postagem p){
		postagens.add(p);
	}
	public void addComentario(Comentario c){
		comentarios.add(c);
	}
}
