package cliente;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;

public class TelaPrincipal {

	private JFrame frmPrincipal;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmListar;
	private JMenu mnProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				System.out.println("Inicializado!");
				Fachada.inicializar();
				frmPrincipal.setTitle(Fachada.getNomeForum());
//				Teste.main(null);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "At� a proxima");
				Fachada.finalizar();
				System.out.println("Finalizado!");
			}
		});
		frmPrincipal.setBounds(100, 100, 450, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		mnProduto = new JMenu("Usuarios");
		menuBar.add(mnProduto);
		
		mntmCadastrar = new JMenuItem("Cadastrar usu�rio");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroUsuario j = new TelaCadastroUsuario();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmCadastrar);
		
		mntmCadastrar = new JMenuItem("Cadastrar administrador");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroAdministrador j = new TelaCadastroAdministrador();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmCadastrar);
		
		mntmCadastrar = new JMenuItem("Listar apenas administradores");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemAdministrador j = new TelaListagemAdministrador();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmCadastrar);
		
		mntmListar = new JMenuItem("Listar usuarios cadastrados");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemUsuario j = new TelaListagemUsuario();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmListar);
		
		JMenu mnPrateleira = new JMenu("Postagem");
		menuBar.add(mnPrateleira);
		
		JMenuItem mntmCriar = new JMenuItem("Listar Postagens");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemPostagem j = new TelaListagemPostagem();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar);
		
		JMenuItem mntmCriar1 = new JMenuItem("Adicionar Postagem");
		mntmCriar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAdicionarPostagem j = new TelaAdicionarPostagem();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar1);
		
		JMenuItem mntmCriar2 = new JMenuItem("Buscar Postagem");
		mntmCriar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaBuscarPostagem j = new TelaBuscarPostagem();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar2);
		
		JMenuItem mntmCriar3 = new JMenuItem("Buscar Minha Postagem");
		mntmCriar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaMinhaPostagem j = new TelaMinhaPostagem();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar3);
		
		JMenuItem mntmCriar8 = new JMenuItem("Buscar Minha Postagem");
		mntmCriar8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaMinhaPostagem j = new TelaMinhaPostagem();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar8);
		
		JMenuItem mntmCriar4 = new JMenuItem("Top Postagem(s)");
		mntmCriar4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaTopPostagens j = new TelaTopPostagens();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar4);
		
		JMenuItem mntmCriar9 = new JMenuItem("Postagens sem comentarios");
		mntmCriar9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaPostagemSingle j = new TelaPostagemSingle();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar9);
		
		JMenu mnPrateleira2 = new JMenu("Coment�rios");
		menuBar.add(mnPrateleira2);
		
		JMenuItem mntmCriar5 = new JMenuItem("Comentar Postagem");
		mntmCriar5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaComentarPostagem j = new TelaComentarPostagem();
				j.setVisible(true);
			}
		});
		mnPrateleira2.add(mntmCriar5);
		
		JMenuItem mntmCriar6 = new JMenuItem("Excluir Comentario");
		mntmCriar6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaExcluirComentario j = new TelaExcluirComentario();
				j.setVisible(true);
			}
		});
		mnPrateleira2.add(mntmCriar6);
		
		JMenuItem mntmCriar7 = new JMenuItem("Processar Comentario Pendente");
		mntmCriar7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaProcessarComentarioPendente j = new TelaProcessarComentarioPendente();
				j.setVisible(true);
			}
		});
		mnPrateleira2.add(mntmCriar7);
		
		JMenuItem mntmCriar10 = new JMenuItem("Comentario Pendente");
		mntmCriar10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaComentarioPendente j = new TelaComentarioPendente();
				j.setVisible(true);
			}
		});
		mnPrateleira2.add(mntmCriar10);
		
		
		
		
		
		
		
		
		
		
	}
}
