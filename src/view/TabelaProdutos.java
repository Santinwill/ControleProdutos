package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.bean.Produto;
import model.dao.ProdutoDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class TabelaProdutos extends JFrame {

	private JPanel contentPane;
	private JTextField descricao;
	private JTextField quantidade;
	private JTextField preco;
	private JTable tableProdutos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaProdutos frame = new TabelaProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TabelaProdutos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		descricao = new JTextField();
		descricao.setBounds(10, 30, 191, 20);
		contentPane.add(descricao);
		descricao.setColumns(10);
		
		quantidade = new JTextField();
		quantidade.setColumns(10);
		quantidade.setBounds(211, 30, 172, 20);
		contentPane.add(quantidade);
		
		preco = new JTextField();
		preco.setColumns(10);
		preco.setBounds(393, 30, 181, 20);
		contentPane.add(preco);
		
		JLabel lblNewLabel = new JLabel("DESCRIÇÃO:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(80, 11, 191, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantidade = new JLabel("QUANTIDADE:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuantidade.setForeground(new Color(255, 255, 255));
		lblQuantidade.setBounds(263, 11, 172, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblPreo = new JLabel("PREÇO:");
		lblPreo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPreo.setForeground(new Color(255, 255, 255));
		lblPreo.setBounds(463, 11, 181, 14);
		contentPane.add(lblPreo);

		JButton cadastrarP = new JButton("CADASTRAR");
		cadastrarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto p = new Produto();
				ProdutoDAO dao = new ProdutoDAO();
				p.setDescricao(descricao.getText());
				p.setQuantidade(Integer.parseInt(quantidade.getText()));
				p.setPreco(Float.parseFloat(preco.getText()));
				dao.create(p);
				descricao.setText("");
				quantidade.setText("");
				preco.setText("");
				readTabela();
			}
		});
		cadastrarP.setBounds(10, 71, 191, 31);
		contentPane.add(cadastrarP);
		
		tableProdutos = new JTable();
		tableProdutos.setSelectionBackground(new Color(255, 128, 128));
		tableProdutos.setSelectionForeground(new Color(0, 0, 0));
		tableProdutos.setFont(new Font("Tahoma", Font.BOLD, 14));
		tableProdutos.setGridColor(new Color(0, 0, 0));
		tableProdutos.setToolTipText("");
		tableProdutos.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableProdutos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(tableProdutos.getSelectedRow() != -1) {
					descricao.setText(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 1).toString());
					quantidade.setText(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 2).toString());
					preco.setText(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 3).toString());
				}else {
						JOptionPane.showMessageDialog(null, "Selecione produto para Atualizar!");
				}
			}
		});
		tableProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tableProdutos.getSelectedRow() != -1) {
					descricao.setText(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 1).toString());
					quantidade.setText(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 2).toString());
					preco.setText(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 3).toString());
				}else {
						JOptionPane.showMessageDialog(null, "Selecione produto para Atualizar!");
				}
			}
		});
		tableProdutos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Descricao", "Quantidade", "Preco"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableProdutos.setBounds(10, 172, 564, 378);
		contentPane.add(tableProdutos);
		readTabela();
		JLabel lblNewLabel_1 = new JLabel("DESCRIÇÃO:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(153, 147, 111, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblQuantidade_1 = new JLabel("QUANTIDADE:");
		lblQuantidade_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuantidade_1.setForeground(new Color(255, 255, 255));
		lblQuantidade_1.setBounds(294, 147, 102, 14);
		contentPane.add(lblQuantidade_1);
		
		JLabel lblPreo_1 = new JLabel("PREÇO:");
		lblPreo_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPreo_1.setForeground(new Color(255, 255, 255));
		lblPreo_1.setBounds(431, 147, 181, 14);
		contentPane.add(lblPreo_1);
		
		JButton ExcluirP = new JButton("EXCLUIR");
		ExcluirP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableProdutos.getSelectedRow() != -1) {
					Produto p = new Produto();
					ProdutoDAO dao = new ProdutoDAO();
					p.setId((int) tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 0));
					dao.delete(p);
					descricao.setText("");
					quantidade.setText("");
					preco.setText("");
					readTabela();
				}else {
					JOptionPane.showMessageDialog(null, "Selecione produto para Exclusao!");
				}
			}
		});
		ExcluirP.setBounds(393, 71, 181, 31);
		contentPane.add(ExcluirP);
		
		JButton AtualizarP = new JButton("ATUALIZAR");
		AtualizarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableProdutos.getSelectedRow() != -1) {
					Produto p = new Produto();
					ProdutoDAO dao = new ProdutoDAO();
					p.setDescricao(descricao.getText());
					p.setQuantidade(Integer.parseInt(quantidade.getText()));
					p.setPreco(Float.parseFloat(preco.getText()));
					p.setId((int) tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 0));
					dao.update(p);
					descricao.setText("");
					quantidade.setText("");
					preco.setText("");
					readTabela();
				}else {
						JOptionPane.showMessageDialog(null, "Selecione produto para Atualizar!");
				}
			}
		});
		AtualizarP.setBounds(211, 71, 172, 31);
		contentPane.add(AtualizarP);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(10, 147, 71, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TabelaProdutos.class.getResource("/imagens/preto.jpg")));
		lblNewLabel_2.setBounds(0, 0, 2940, 1960);
		contentPane.add(lblNewLabel_2);
	}
	
	public void readTabela(){
		DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
		modelo.setNumRows(0);
		ProdutoDAO pdao = new ProdutoDAO();
		for(Produto p: pdao.read()) {
			modelo.addRow(new Object[] {
				p.getId(),
				p.getDescricao(),
				p.getQuantidade(),
				p.getPreco()
			});
		}			
	}
}
