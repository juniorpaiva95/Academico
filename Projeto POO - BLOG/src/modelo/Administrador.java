package modelo;

import java.util.ArrayList;

import modelo.Usuario;
import modelo.Comentario;

public class Administrador extends Usuario implements Comparable<Administrador>{
	private ArrayList<Comentario> pendencias = new ArrayList<Comentario>();
	public Administrador(String nome, String email) {
		super(nome, email);
	}
	
	//Metodos GET
	public ArrayList<Comentario> getPendencias() {
		return pendencias;
	}
	//Metodos de inserção
	public void addComentario(Comentario c){
		pendencias.add(c);
	}

	@Override
	public String toString() {
		return "Administrador [pendencias=" + pendencias + ", toString()=" + super.toString() + "]";
	}

	@Override
    public int compareTo(Administrador adm) {
		return this.getNome().compareTo(adm.getNome());
	}

	
}
