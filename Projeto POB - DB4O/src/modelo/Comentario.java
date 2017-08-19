package modelo;

import java.util.Date;

import daodb4o.IDInterface;
import utils.Helpers;

import java.text.SimpleDateFormat;

public class Comentario implements IDInterface{
	//Atributos	
	private int id;
	private Usuario autor;
	private String comentario;
	private Postagem postagem;
	private Date data = new Date();
	
	public Comentario(Usuario autor, String comentario, Postagem postagem){
		this.id = id;
		this.autor = autor;
		this.comentario = comentario;
		this.postagem = postagem;
	}
	
	//GETS
	
	

	public int getId() {
		return id;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", autor=" + autor.getNome() + ", comentario=" + comentario + ", data=" + Helpers.formatarData(data) + "]";
	}

	public Usuario getAutor() {
		return autor;
	}

	public String getComentario() {
		return comentario;
	}

	public Date getData() {
		return data;
	}

	@Override
	public void setId(int id) {
		this.id = id;
		
	}


}

