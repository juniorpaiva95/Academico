package cliente;

import java.util.Scanner;
import fachada.Fachada;
import utils.Helpers;

public class Teclado {
	private Scanner teclado = new Scanner(System.in);
	
	public Teclado() {
		processarMenu();
	}
	public void processarMenu() {
		String entrada;
		int opcao;
		System.out.println("\n\n <-- Bem vindo ao Forum" + Fachada.getNomeForum() + " --> ");
		do {
			exibirMenu();
			try{
				entrada = teclado.nextLine();
				opcao = Integer.parseInt(entrada);
				switch (opcao) {
				case 0:	break;
				case 1:
					this.tarefa_Cadastrar_Usuario();
					break;
				case 2:
					this.tarefa_listarUsuariosCadastrados();
					break;
				case 3:
					this.tarefa_buscarPostagens();
					break;
				case 4:
					this.tarefa_listarMinhasPostagens();
					break;
				case 5:
					this.tarefa_lerPostagem();
					break;
				case 6: 	
					this.tarefa_postar();
					break;
				case 7:
					this.tarefa_comentarPostagem();
					break;
				case 8:
					this.tarefa_listarPostagensTop();
					break;
				case 9:
					this.tarefa_listarPostagemSemComentarios();
					break;
				default: 
					System.out.println("Opção Invalida !! \n");
				}
			}
			catch(NumberFormatException e)	{
				System.out.println("Digite somente número! \n");
				opcao=-1;
			}
			catch(Exception e)	{
				System.out.println("erro:" + e.getMessage());
				opcao=-1;
			}		
		}while (opcao != 0);
		System.out.println("\n <-- Finalizando o programa. -->");
	}
	
	
	public void exibirMenu() {
		System.out.println("\n\n| - - - - - - - - -  Menu  - - - - - - - - - - - |");
		System.out.println("|  [0]- Sair                                     |");
		System.out.println("|  [1]- Cadastrar novo usuário                   |");
		System.out.println("|  [2]- Listar Usuários Cadastrados              |");
		System.out.println("|  [3]- Buscar Postagens                         |");
		System.out.println("|  [4]- Listar minhas Postagens                  |");
		System.out.println("|  [5]- Ler Postagem                             |");
		System.out.println("|  [6]- Postar                                   |");
		System.out.println("|  [7]- Comentar                                 |");
		System.out.println("|  [8]- Postagens mais visualizadas              |");
		System.out.println("|  [9]- Postagens sem comentários                |");
		System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - -|");
		System.out.print("  Opção :");
	}
	
	//Executando o Programa CLIENTE	
	public static void main(String[] args){
		new Teclado();
	}
	
	//Tarefas executadas pelo cliente
	
	
	/*
	 * MODEL USUARIO
	 * */
	public void tarefa_Cadastrar_Usuario(){
		String nome;
		String email;
		System.out.println("\n--CADASTRO DE NOVO USUÁRIO--");
		System.out.println("\n\nInsira o nome do novo usuário(ou ENTER para voltar)");
		nome = teclado.nextLine();
		System.out.println("\nInsira o email do novo usuário(ou ENTER para voltar)");
		email = teclado.nextLine();
		try{
			Fachada.cadastrarUsuario(nome, email);
		}catch(Exception e){
			System.out.println("-->"+ e.getMessage());
		}
	}
	
	public void tarefa_listarUsuariosCadastrados(){		
		System.out.println(Fachada.listarUsuarios());
	}
	
	/*
	 * MODEL POSTAGEM
	 * */
	public void tarefa_postar(){
		String topico;
		String email;
		String texto;
		
//		String confirmar;
		
		System.out.println("\n--NOVA POSTAGEM--\n");
		System.out.println("\nInforme o email do autor:");
		email = teclado.nextLine();
		
		System.out.println("\nInforme o topico:");
		topico = teclado.nextLine();
		
		System.out.println("\nInforme o texto:");
		texto = teclado.nextLine();
		
//		System.out.println("Deseja confirmar esta postagem ? (S ou N)");
//		confirmar = teclado.nextLine().toUpperCase();
//		System.out.println(confirmar == "S");
//		if(confirmar == "S"){
			try{
				Fachada.postar(email, topico, texto);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
//		}
	}
	
	public void tarefa_listarMinhasPostagens(){
		String email;
		System.out.println("Informe o email do autor:");
		email = teclado.nextLine();
		try{
			System.out.println(Fachada.listarMinhasPostagens(email));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	public void tarefa_lerPostagem(){
		int idpostagem;
		try{
			System.out.println("Informe o id da postagem:");
			idpostagem = Integer.parseInt(teclado.nextLine());
			
			System.out.println(Fachada.lerPostagem(idpostagem));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public void tarefa_comentarPostagem(){
		int idpost;
		String comentario;
		String email_user;
		System.out.println("\n\n---> NOVO COMENTÁRIO <---");
		System.out.println("\nInforme o id da postagem:");
		idpost = Integer.parseInt(teclado.nextLine());
		System.out.println("\nDigite o comentário:");
		comentario = teclado.nextLine();
		System.out.println("\nInforme seu email:");
		email_user = teclado.nextLine();
		
		try{	
			Fachada.comentar(idpost, comentario, email_user);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void tarefa_buscarPostagens(){
		String termo_pesq;
		System.out.println("Informe um termo de busca:");
		termo_pesq = teclado.nextLine();
		
		try{
			System.out.println(Fachada.buscarPostagens(termo_pesq));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void tarefa_listarPostagensTop(){
		try{
			System.out.println(Fachada.postagensTop());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void tarefa_listarPostagemSemComentarios(){
		try{
			System.out.println(Fachada.postagensSingle());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
