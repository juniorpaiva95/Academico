
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

public class TelaCadastroUsuario extends JFrame {

	private JLabel lblId;
	private JLabel lblNome;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnCadastrar;
	private DefaultListModel<String> model = new DefaultListModel<String>();	
	private JLabel lblMensagemDoUsuario;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroUsuario window = new TelaCadastroUsuario();
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
	public TelaCadastroUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Cadastro de usuario");
		this.setBounds(100, 100, 345, 229);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.lblId = new JLabel("Email:");
		this.lblId.setBounds(19, 56, 102, 14);
		this.getContentPane().add(this.lblId);
		this.lblNome = new JLabel("Nome:");
		this.lblNome.setBounds(19, 28, 102, 14);
		this.getContentPane().add(this.lblNome);
		this.textField = new JTextField();
		this.textField.setBounds(124, 25, 86, 20);
		this.getContentPane().add(this.textField);
		this.textField.setColumns(10);
		this.textField_1 = new JTextField();
		this.textField_1.setBounds(124, 53, 40, 20);
		this.getContentPane().add(this.textField_1);
		this.textField_1.setColumns(10);
		this.btnCadastrar = new JButton("Cadastrar");
		this.btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nome = textField.getText();
					String email = textField_1.getText();
					Fachada.cadastrarUsuario(nome, email);
					lblMensagemDoUsuario.setText("Usuario cadastrado!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					lblMensagemDoUsuario.setText(e.getMessage());
				}
			}
		});
		this.btnCadastrar.setBounds(19, 101, 136, 23);
		this.getContentPane().add(this.btnCadastrar);
		this.lblMensagemDoUsuario = new JLabel("");
		this.lblMensagemDoUsuario.setBounds(19, 164, 294, 14);
		this.getContentPane().add(this.lblMensagemDoUsuario);
		this.btnLimpar = new JButton("Limpar");
		this.btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField.requestFocus();
			}
		});
		this.btnLimpar.setBounds(172, 101, 141, 23);
		this.getContentPane().add(this.btnLimpar);

	}
}
