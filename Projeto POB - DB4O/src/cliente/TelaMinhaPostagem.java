package cliente;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
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

public class TelaMinhaPostagem extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnCriar;
	private JLabel lblTermo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMinhaPostagem frame = new TelaMinhaPostagem();
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
	public TelaMinhaPostagem() {
		setTitle("Buscar postagem");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCriar = new JButton("Buscar!");
		btnCriar.setBackground(Color.GREEN);
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String email = textField.getText();
					
					textArea.setText(Fachada.listarMinhasPostagens(email));
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(208, 149, 115, 23);
		contentPane.add(btnCriar);
		
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 29, 510, 109);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		lblTermo = new JLabel("Email:");
		lblTermo.setBackground(Color.GREEN);
		lblTermo.setBounds(24, 153, 46, 14);
		contentPane.add(lblTermo);
		
		textField = new JTextField();
		textField.setBounds(61, 150, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
