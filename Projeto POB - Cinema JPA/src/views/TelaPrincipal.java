package views;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
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

import aplicacao.TesteAplicacao;
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
//					Fachada.inicializar();
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
				TesteAplicacao.main(null);

			}
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "até breve !");
			}
		});
		frmPrincipal.setBounds(100, 100, 450, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		mnProduto = new JMenu("Filmes");
		menuBar.add(mnProduto);
		
		JMenuItem mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mntmCadastrar_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFilme j = new TelaCadastroFilme();
				j.setVisible(true);
				
			}
		});
		mnProduto.add(mntmCadastrar_1);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mnProduto.add(mntmExcluir);
		
		
		JMenu mnPrateleira = new JMenu("Horário");
		menuBar.add(mnPrateleira);
		
		JMenuItem mntmCriar = new JMenuItem("Cadastrar");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroHorario j = new TelaCadastroHorario();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar);
		
		JMenuItem mntmExcluir_1 = new JMenuItem("Excluir");
		mntmExcluir_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		mnPrateleira.add(mntmExcluir_1);
		
		JMenu mnSalas = new JMenu("Salas");
		menuBar.add(mnSalas);
		
		JMenuItem mntmCadastrar_2 = new JMenuItem("Cadastrar");
		mnSalas.add(mntmCadastrar_2);
		
		JMenuItem mntmExcluir_2 = new JMenuItem("Excluir");
		mnSalas.add(mntmExcluir_2);
		
		JMenu mnSesso = new JMenu("Sessão");
		menuBar.add(mnSesso);
		
		JMenuItem mntmCadastrar_3 = new JMenuItem("Cadastrar");
		mnSesso.add(mntmCadastrar_3);
		
		JMenuItem mntmExcluir_3 = new JMenuItem("Excluir");
		mnSesso.add(mntmExcluir_3);
		
	}
}