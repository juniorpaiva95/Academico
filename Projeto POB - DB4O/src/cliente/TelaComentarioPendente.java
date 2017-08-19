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

public class TelaComentarioPendente extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnNewButton;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaComentarioPendente frame = new TelaComentarioPendente();
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
	public TelaComentarioPendente() {
		setTitle("");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(70, 36, 347, 105);
		contentPane.add(textArea);
		
		btnNewButton = new JButton("Listar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//Fachada.obterComentarioPendente(textField_1.getText()).getComentario();
					textArea.setText(Fachada.obterComentarioPendente(textField_1.getText()).getComentario());
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.GREEN);
		btnNewButton.setBounds(168, 148, 108, 23);
		contentPane.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 8, 126, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email do ADM:");
		lblEmail.setBounds(38, 11, 126, 14);
		contentPane.add(lblEmail);
	}
}
