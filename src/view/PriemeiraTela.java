package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.dao.TestUsuarioDAO;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PriemeiraTela {

	private JFrame frmAcesso;
	private JTextField textLogin;
	private JPasswordField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PriemeiraTela window = new PriemeiraTela();
					window.frmAcesso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PriemeiraTela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAcesso = new JFrame();
		frmAcesso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAcesso.setResizable(false);
		frmAcesso.setTitle("Acesso");
		frmAcesso.setBounds(100, 100, 450, 374);
		frmAcesso.setLocationRelativeTo(null);
		frmAcesso.getContentPane().setLayout(null);
		
		textLogin = new JTextField();
		textLogin.setBounds(115, 108, 211, 20);
		frmAcesso.getContentPane().add(textLogin);
		textLogin.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(115, 57, 211, 44);
		frmAcesso.getContentPane().add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(115, 139, 211, 38);
		frmAcesso.getContentPane().add(lblSenha);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(115, 180, 211, 20);
		frmAcesso.getContentPane().add(textPassword);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestUsuarioDAO dao = new TestUsuarioDAO();
				
				if(dao.checkLogin(textLogin.getText(), textPassword.getText())) {
					new TabelaProdutos().setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Acesso Negado!", "Erro", JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		btnNewButton.setBounds(115, 231, 211, 57);
		frmAcesso.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PriemeiraTela.class.getResource("/imagens/preto.jpg")));
		lblNewLabel_2.setBounds(-29, 0, 487, 352);
		frmAcesso.getContentPane().add(lblNewLabel_2);
	}
}

