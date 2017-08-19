package utils;

import java.util.ArrayList;
import java.util.List;

import modelo.Comentario;
import modelo.Postagem;

public class ShowMe {
	//Metodo estático que exibe um arrayList de postagens
	public static String showPosts(List<Postagem> postagens){
		String retorno = "";
		
		for(Postagem p: postagens){
			retorno +=  "\nAutor: "+p.getAutor().getNome().toUpperCase()+"\nTitulo:"+p.getTopico();
			retorno += "\nTexto:"+p.getTexto();
			//Comentarios 
			retorno += "\n\nCOMENTÁRIOS ("+p.getComentarios().size()+")\n";
//			retorno += "\n-----------------------------------------------------------------\n";
			if(p.getComentarios().isEmpty()){
				retorno += "Esta postagem não possui comentários";
			}else{
				int i=0;
				for(Comentario c: p.getComentarios()){
					retorno += ++i  +" - "+ c.getAutor().getNome() + ": " + c.getComentario()+ " Data:"+ c.getData() +"\n";
				}
			}
			retorno += "\nNº Acessos: "+p.getAcessos();
			retorno += "\nPostado em: "+p.getDate();
			retorno += "\n-----------------------------------------------------------------\n\n";
		}
		
		return retorno;
	}
	//Metodo estático que exibe uma postagem única
	public static String showPost(Postagem p){
		String retorno = "\n--POSTAGEM ["+p.getId()+"]--\n";
		retorno +=  "\nAutor: "+p.getAutor().getNome().toUpperCase()+"\nTitulo:"+p.getTopico();
		retorno += "\nTexto:"+p.getTexto();
		//Comentarios 
		retorno += "\n\nCOMENTÁRIOS ("+p.getComentarios().size()+")\n";
//		retorno += "\n-----------------------------------------------------------------\n";
		if(p.getComentarios().isEmpty()){
			retorno += "Esta postagem não possui comentários";
		}else{
			int i=0;
			for(Comentario c: p.getComentarios()){
				retorno += ++i  +" - "+ c.getAutor().getNome() + ": " + c.getComentario()+ " Data:"+ c.getData() +"\n";
			}
		}
		retorno += "\nNº Acessos: "+p.getAcessos();
		retorno += "\nPostado em: "+p.getDate();
		retorno += "\n-----------------------------------------------------------------\n\n";
		return retorno;
	}
}
