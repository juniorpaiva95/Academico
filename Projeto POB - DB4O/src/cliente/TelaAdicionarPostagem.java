
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
package cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fachada.Fachada;

public class TelaAdicionarPostagem extends JFrame {

	private JLabel lblId;
	private JLabel lblNome;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnCadastrar;
	private DefaultListModel<String> model = new DefaultListModel<String>();	
	private JLabel lblMensagemDoUsuario;
	private JButton btnLimpar;
	private JLabel lblEmail;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdicionarPostagem window = new TelaAdicionarPostagem();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaAdicionarPostagem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Adicionar uma nova postagem");
		this.setBounds(100, 100, 345, 229);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.lblId = new JLabel("Texto:");
		this.lblId.setBounds(19, 56, 102, 14);
		this.getContentPane().add(this.lblId);
		this.lblNome = new JLabel("Titulo:");
		this.lblNome.setBounds(19, 28, 102, 14);
		this.getContentPane().add(this.lblNome);
		this.textField = new JTextField();
		this.textField.setBounds(124, 25, 119, 20);
		this.getContentPane().add(this.textField);
		this.textField.setColumns(10);
		this.textField_1 = new JTextField();
		this.textField_1.setBounds(124, 53, 119, 52);
		this.getContentPane().add(this.textField_1);
		this.textField_1.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(19, 109, 46, 14);
		getContentPane().add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setBounds(124, 106, 147, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		this.btnCadastrar = new JButton("Adicionar");
		this.btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String topico = textField.getText();
					String texto = textField_1.getText();
					String email = textField_2.getText();
					Fachada.postar(topico, texto, email);
					
					lblMensagemDoUsuario.setText("Postagem realizada!");
				} catch (Exception e) {
					lblMensagemDoUsuario.setText(e.getMessage());
				}
			}
		});
		this.btnCadastrar.setBounds(19, 155, 136, 23);
		this.getContentPane().add(this.btnCadastrar);
		this.lblMensagemDoUsuario = new JLabel("");
		this.lblMensagemDoUsuario.setBounds(19, 134, 294, 14);
		this.getContentPane().add(this.lblMensagemDoUsuario);
		this.btnLimpar = new JButton("Limpar");
		this.btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField.requestFocus();
			}
		});
		this.btnLimpar.setBounds(165, 155, 141, 23);
		this.getContentPane().add(this.btnLimpar);
		
		
	}
}
