package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.*;
import modelo.*;
public class Helpers {
	//Método estático que retorna true caso o e-mail seja válido e falso caso contrário
	public static boolean validaEmail(String email){
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(email.trim()); 
        if (!m.find()){
        	return false;
        }
        return true;
	}
	
	//Método estático que retorna um array com as postagens mais acessadas
	public static ArrayList<Postagem> postagemMaisAcessada(List<Postagem> postagens){
		int maior = 0;
		ArrayList<Postagem> aux = new ArrayList<Postagem>();
		//Obtendo o maior numero de acessos no array de postagem
		for(Postagem p: postagens){
			if(p.getAcessos() > maior){
				maior = p.getAcessos();
			}
		}
		//Verificando quais postagens tem acessos maior ou igual ao número obtido
		for(Postagem p: postagens){
			if(p.getAcessos() >= maior){
				aux.add(p);
			}
		}
		return aux;
	}
	
	//Formatar data
	public static String formatarData(Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
		return sdf.format(data);
	}
}
