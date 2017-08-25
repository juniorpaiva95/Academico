package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Filme;
import util.Utilitaries;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

//import fachada.Fachada;

public class TelaCadastroFilme extends JFrame {

	private JPanel contentPane;
	private JTextField titulo;
	private JLabel lblNome;
	private JButton btnCriar;
	private JTextField data_lanc;
	private JLabel lblNewLabel;
	private JTextField censura;
	private JTextField duracao;
	private JTextField autor;
	
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroFilme frame = new TelaCadastroFilme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroFilme() {
		setTitle("Cadastrar Horário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		titulo = new JTextField();
		titulo.setBounds(141, 12, 270, 20);
		contentPane.add(titulo);
		titulo.setColumns(10);

		lblNome = new JLabel("Titulo");
		lblNome.setBounds(10, 14, 94, 14);
		contentPane.add(lblNome);
		
		JLabel lblMinutos = new JLabel("Data Lançamento");
		lblMinutos.setBounds(10, 67, 70, 15);
		contentPane.add(lblMinutos);
		
		data_lanc = new JTextField();
		data_lanc.setBounds(141, 65, 270, 19);
		contentPane.add(data_lanc);
		data_lanc.setColumns(10);
		
		lblNewLabel = new JLabel("Sinopse");
		lblNewLabel.setBounds(10, 119, 70, 15);
		contentPane.add(lblNewLabel);
		
		JTextPane sinopse = new JTextPane();
		sinopse.setBounds(141, 119, 270, 92);
		contentPane.add(sinopse);
		
		JLabel lblCensura = new JLabel("Censura");
		lblCensura.setBounds(10, 260, 70, 15);
		contentPane.add(lblCensura);
		
		censura = new JTextField();
		censura.setBounds(141, 258, 270, 19);
		contentPane.add(censura);
		censura.setColumns(10);
		
		JLabel lblDurao = new JLabel("Duração");
		lblDurao.setBounds(10, 324, 70, 15);
		contentPane.add(lblDurao);
		
		duracao = new JTextField();
		duracao.setBounds(141, 322, 270, 19);
		contentPane.add(duracao);
		duracao.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(10, 382, 70, 15);
		contentPane.add(lblAutor);
		
		autor = new JTextField();
		autor.setBounds(150, 379, 261, 20);
		contentPane.add(autor);
		autor.setColumns(10);
		

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String aut = autor.getText();
					String sinop = sinopse.getText();
					Integer duration = Integer.parseInt(duracao.getText());
					String tit = titulo.getText();
					String data = data_lanc.getText();
					
					Integer cens = Integer.parseInt(censura.getText());
					
					Filme f = Fachada.cadastrarFilme(duration, aut, cens, sinop, tit, new Date(format.parse(data).getTime()));
					System.out.println(f.toString());
					JOptionPane.showMessageDialog(null,"Filme cadastrado: " + f);
				}
				catch(Exception erro){
					System.out.println(erro.getMessage());
					JOptionPane.showMessageDialog(null,"Erro: " + erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(531, 402, 115, 23);
		contentPane.add(btnCriar);

	}
}
