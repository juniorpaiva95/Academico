package cliente;

import fachada.Fachada;
import modelo.*;
public class Teste {
	public Teste(){
		try{
			Usuario u1, u2, u3;
			Postagem p1, p2, p3, p4, p5, p6;
			//TESTE 1 - Criando os usuários de Teste
			u1 = Fachada.cadastrarUsuario("Junior", "junior@hotmail.com");
			u2 = Fachada.cadastrarUsuario("Renan", "renan@hotmail.com");
			u3 = Fachada.cadastrarUsuario("Lucas", "lucas@hotmail.com");
			
			//TESTE 6 - Criando as postagens de Teste
			p1 = Fachada.postar("junior@hotmail.com", "Topico 1", "Topico 1");
			p2 = Fachada.postar("renan@hotmail.com", "Topico 2", "Topico 2");
			p3 = Fachada.postar("lucas@hotmail.com", "Topico 3", "Topico 3");
			p4 = Fachada.postar("junior@hotmail.com", "Topico 4", "Topico 4");
			p5 = Fachada.postar("renan@hotmail.com", "Topico 5", "Topico 5");
			p6 = Fachada.postar("lucas@hotmail.com", "Topico 6", "Topico 6");
			
			//Teste de Funcionalidades
			
			//TESTE 2 - Listagem de Usuários			
			System.out.println(Fachada.listarUsuarios());
			//TESTE 3 - Busca de Postagens 			
			System.out.println(Fachada.buscarPostagens("Topico 6"));
			//TESTE 4 - Listar Minhas postagens 
			System.out.println(Fachada.listarMinhasPostagens(u1.getEmail()));
			//TESTE 5 - Minhas postagens
			System.out.println(Fachada.lerPostagem(1));
			//TESTE 7 - Comentar
			Fachada.comentar(1, "Comentário 1", u1.getEmail());
			Fachada.comentar(2, "Comentário 2", u2.getEmail());
			Fachada.comentar(2, "Comentário 2", u2.getEmail());
			//TESTE 8 - Postagens TOP
			System.out.println(Fachada.postagensTop());
			//TESTE 9 - Postagens sem comentários
			System.out.println(Fachada.postagensSingle());
			
			System.out.println("Teste concluído com sucesso!!");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void main(String[] args){
		new Teste();
	}
}
