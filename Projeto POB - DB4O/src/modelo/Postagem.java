package modelo;

import java.util.ArrayList;
import java.util.Date;

import daodb4o.IDInterface;

public class Postagem implements Comparable<Postagem>, IDInterface{
	private Integer id;
	private String topico;
	private Usuario autor;
	private String texto;
	private int acessos = 0;
	private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
	private Date data = new Date();
	
	
	//Construtor
	public Postagem(Usuario autor, String topico, String texto){
		this.id = id;
		this.autor = autor;
		this.topico = topico;
		this.texto = texto;
	}

	
	@Override
	public String toString() {
		return "Postagem [id=" + id + ", topico=" + topico + ", autor=" + autor.getNome() + ", texto=" + texto + ", acessos="
				+ acessos + ", comentarios=" + comentarios + ", date_created=" + data + "]";
	}
	

	//Metodos GET
	public int getId(){
		return id;
	}
	public Usuario getAutor(){
		return autor;
	}
	public String getTopico(){
		return topico;
	}
	public String getTexto(){
		return this.texto;
	}
	public int getAcessos() {
		return acessos;
	}
	public Date getDate() {
		return data;
	}
	public ArrayList<Comentario> getComentarios(){
		return comentarios;
	}
	
	//Metodos SET
	public void setId(int id) {
		this.id = id;
	}
	//Funcionalidades
	
	public void incrementarAcesso(){
		this.acessos++;
	}
	public void addComentario(Comentario c){
		comentarios.add(c);
	}
	
	@Override
	public int compareTo(Postagem p) {
		if(acessos < p.getAcessos()){
			return 1;
		}
		if(acessos > p.getAcessos()){
			return -1;
		}
		return 0;
	}
	
}

