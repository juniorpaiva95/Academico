package modelo;

import java.util.Date;

import utils.Helpers;

import java.text.SimpleDateFormat;

public class Comentario{
	//Atributos	
	private int id;
	private Usuario autor;
	private String comentario;
	private Date data = new Date();
	
	public Comentario(int id, Usuario autor, String comentario){
		this.id = id;
		this.autor = autor;
		this.comentario = comentario;
	}
	
	//GETS
	
	

	public int getId() {
		return id;
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


}

