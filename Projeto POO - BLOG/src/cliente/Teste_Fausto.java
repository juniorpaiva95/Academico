
package cliente;

/**
 *
 */

import modelo.Postagem;
import modelo.Usuario;

import java.util.Date;

import fachada.Fachada;
import modelo.Comentario;
import modelo.Forum;



public class Teste_Fausto {

        public Teste_Fausto() {
		 try {
                        // Criaçao de usuarios no forum //
                        Fachada.cadastrarUsuario("Oi", "oi@email.com.br");
                        Fachada.cadastrarUsuario("Tim", "tim@email.com");
                        Fachada.cadastrarUsuario("Claro", "claro@email.com");
                        Fachada.cadastrarUsuario("Vivo", "vivo@email.com");
                        
                        //Meu teste
                        Fachada.cadastrarAdministrador("Junior", "junior@hotmail.com");
                        
                        //Postagem com e sem comentarios no forum //
                        Fachada.postar("OI POST", "Postagem da empresa Oi", "oi@email.com.br");
                        Fachada.postar("TIM ", "Postagem da empresa Tim", "tim@email.com");
                        Fachada.postar("CLARO ", "Postagem da empresa Claro", "claro@email.com");
                        Fachada.postar("VIVO ", "Postagem da empresa Vivo", "vivo@email.com");
                        Fachada.postar("OI", "Post para esclarecimentos", "tim@email.com");
                        Fachada.postar("vivo", "Informaçoes sobre nossos planos", "vivo@email.com");

                        //Comentando em postagens//
                        Fachada.comentar(1, "Prestamos serviço em todo o pais com qualidade", "oi@email.com.br");
                        Fachada.comentar(1, "comentario2", "oi@email.com.br");
                        Fachada.comentar(3, "Internet com pessimo serviço em todos os aspectos", "claro@email.com");
                        Fachada.comentar(3, "Nosso serviço de tv via satelite eh o melhor ja visto", "oi@email.com.br");
//                        System.out.println(Fachada.obterComentarioPendente("junior@hotmail.com"));
//                        System.out.println("Dados cadastrados");
//                        System.out.println(Fachada.listarUsuarios());
                        
//                        System.out.println("Listar usuarios administradores");
//                        System.out.println(Fachada.listarUsuariosAdministradores());
                        
                        System.out.println(Fachada.obterComentarioPendente("junior@hotmail.com"));
//                        System.out.println(Fachada.listarMinhasPostagens("vivo@email.com"));
//                        System.out.println("BUSCAR todas");
//                        System.out.println(Fachada.buscarPostagens(""));
//                        System.out.println("BUSCAR oi");
//                        System.out.println(Fachada.buscarPostagens("OI"));
//                        System.out.println("LER POSTAGEM 1");
//                        System.out.println(Fachada.lerPostagem(1));
//                        System.out.println("LER POSTAGEM 3");
//                        System.out.println(Fachada.lerPostagem(3));
//                        System.out.println("TOP");
//                        System.out.println(Fachada.postagensTop());
//                        System.out.println("SINGLE");
//                        System.out.println(Fachada.postagensSingle());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
                 
//                try{
//                        Fachada.cadastrarUsuario("Oi", "oi@email.com.br");
//                } catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
                
//                try{
//                        Fachada.comentar(10, "Prestamos serviço em todo o pais com qualidade", "oi@email.com.br");
//                }catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
                
//                try{
//                        Fachada.postar("vivo", "Informaçoes sobre nossos planos", "vivo@email.com");
//                        System.out.println(Fachada.listarMinhasPostagens("vivo@email.com"));
//
//                }catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
 
//                try{
//                       System.out.println(Fachada.listarMinhasPostagens("xx@email.com.br"));
//                }catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
 
 
	}

        public static void main (String[] args)
//  ***********************************************
	{
		new Teste_Fausto();
	}

}

