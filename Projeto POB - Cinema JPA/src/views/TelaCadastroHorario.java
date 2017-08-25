package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

//import fachada.Fachada;

public class TelaCadastroHorario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNome;
	private JButton btnCriar;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroHorario frame = new TelaCadastroHorario();
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
	public TelaCadastroHorario() {
		setTitle("Cadastrar Horário");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(85, 12, 123, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNome = new JLabel("Hora");
		lblNome.setBounds(10, 14, 94, 14);
		contentPane.add(lblNome);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Integer hora = Integer.parseInt(textField.getText());
					Integer minuto = Integer.parseInt(textField_1.getText());
					Date horario = new Date();
					horario.setHours(hora);
					horario.setMinutes(minuto);
					horario.setSeconds(0);
					Fachada.cadastrarHorario(horario);
					JOptionPane.showMessageDialog(null,"Horario cadastrado: " + horario.getHours() +":"+horario.getMinutes()+":"+horario.getSeconds());
				}
				catch(Exception erro){
					System.out.println(erro.getMessage());
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(12, 112, 115, 23);
		contentPane.add(btnCriar);
		
		JLabel lblMinutos = new JLabel("Minutos");
		lblMinutos.setBounds(10, 67, 70, 15);
		contentPane.add(lblMinutos);
		
		textField_1 = new JTextField();
		textField_1.setBounds(85, 65, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}