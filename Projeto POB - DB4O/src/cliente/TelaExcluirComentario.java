package cliente;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class TelaExcluirComentario extends JFrame {

	private JPanel contentPane;
	private JButton btnCriar;
	private JLabel lblTermo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExcluirComentario frame = new TelaExcluirComentario();
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
	public TelaExcluirComentario() {
		setTitle("Excluir comentario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCriar = new JButton("Excluir!");
		btnCriar.setBackground(Color.GREEN);
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String idPostagem = textField.getText();
					int idP = Integer.parseInt(idPostagem);
					String comentario = textField_2.getText();
					int idC = Integer.parseInt(comentario);
					String email = textField_1.getText();
					Fachada.comentar(idP, comentario, email);
					Fachada.excluirComentario(idP, idC, email);
					textArea_1.setText("Comentario excluido!");
					
					
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(208, 149, 115, 23);
		contentPane.add(btnCriar);
		
		lblTermo = new JLabel("ID postagem:");
		lblTermo.setBackground(Color.GREEN);
		lblTermo.setBounds(48, 106, 65, 14);
		contentPane.add(lblTermo);
		
		textField = new JTextField();
		textField.setBounds(123, 103, 28, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblComentrio = new JLabel("ID comentario:");
		lblComentrio.setBounds(48, 74, 80, 14);
		contentPane.add(lblComentrio);
		
		textField_1 = new JTextField();
		textField_1.setBounds(123, 128, 82, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(82, 131, 46, 14);
		contentPane.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setBounds(123, 67, 28, 28);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(111, 31, 371, 39);
		contentPane.add(textArea_1);
	}
}
