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

public class TelaProcessarComentarioPendente extends JFrame {

	private JPanel contentPane;
	private JButton btnCriar;
	private JLabel lblAprovar;
	private JTextField textField;
	private JLabel lblsimnao;
	private JTextArea textArea;
	private JLabel lblEmail;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProcessarComentarioPendente frame = new TelaProcessarComentarioPendente();
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
	public TelaProcessarComentarioPendente() {
		setTitle("Processar comentario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCriar = new JButton("Confirmar");
		btnCriar.setBackground(Color.GREEN);
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Fachada.processarComentarioPendente(textField_1.getText(), textField.getText());
					textArea.setText("Operação realizada!!");
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(381, 142, 115, 23);
		contentPane.add(btnCriar);
		
		lblAprovar = new JLabel("Aprovar?");
		lblAprovar.setBounds(63, 116, 71, 14);
		contentPane.add(lblAprovar);
		
		textField = new JTextField();
		textField.setBounds(118, 113, 83, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblsimnao = new JLabel("(sim/nao)");
		lblsimnao.setBounds(207, 116, 62, 14);
		contentPane.add(lblsimnao);
		
		textArea = new JTextArea();
		textArea.setBounds(63, 49, 331, 56);
		contentPane.add(textArea);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(63, 24, 46, 14);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 21, 115, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnObterComentario = new JButton("Obter Comentario");
		btnObterComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String email = textField_1.getText();
					textArea.setText(Fachada.obterComentarioPendente(email).getComentario());		
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnObterComentario.setBounds(243, 20, 138, 23);
		contentPane.add(btnObterComentario);
	}
}
