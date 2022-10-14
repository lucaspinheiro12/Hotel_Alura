package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.result.DoubleValueFactory;

import Controller.HospedeController;
import Controller.ReservaController;
import Modelo.Hospede;
import Modelo.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.management.RuntimeErrorException;
import javax.swing.ImageIcon;
import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HospedeController hospedeController;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		
		this.reservaController = new ReservaController();
		this.hospedeController = new HospedeController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 190, 865, 307);
		contentPane.add(panel);
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		preencherTabelaReservas();
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), tbHospedes, null);
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		
		preencherTabelaHospedes();
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
					if(! txtBuscar.getText().equals("")) {
						limparTabelaReservas();
						limparTabelaHospedes();
						if(Busca()) {
							txtBuscar.setText("");
						}else {
							JOptionPane.showMessageDialog(null, "O Numero da reserva ou sobrenome do cliente não foi encontrado");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Digite o Numero da reserva ou sobrenome do cliente");
					}				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 113, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		
		//botao limpar busca
		JPanel btnLimpar = new JPanel();
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparTabelaReservas();
				limparTabelaHospedes();
				preencherTabelaReservas();
				preencherTabelaHospedes();
				
			}
		});
		btnLimpar.setLayout(null);
		btnLimpar.setBackground(Color.red);
		btnLimpar.setBounds(748, 163, 122, 27);
		btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnLimpar);
		
		JLabel lblLimpar = new JLabel("LIMPAR BUSCA");
		lblLimpar.setBounds(0, 0, 122, 27);
		btnLimpar.add(lblLimpar);
		lblLimpar.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimpar.setForeground(Color.WHITE);
		lblLimpar.setFont(new Font("Roboto", Font.PLAIN, 15));
				
				
				
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer valorDeVerificacao = -1;
				if(  ! valorDeVerificacao.equals(tbHospedes.getSelectedRow())) {
					alterarHospede();
					limparTabelaHospedes();
					preencherTabelaHospedes();
					tbHospedes.clearSelection();
				}else if( ! valorDeVerificacao.equals(tbReservas.getSelectedRow())) {
					alterarReserva();
					limparTabelaReservas();
					preencherTabelaReservas();
					 tbReservas.clearSelection();
				}	
			}	
		});
		
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		//evento do botao editar
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Integer valorDeVerificacao = -1;
				int filaReservas = tbReservas.getSelectedRow();
				int filaHospedes = tbHospedes.getSelectedRow();

				 if(  ! valorDeVerificacao.equals(tbReservas.getSelectedRow())) {

					String reserva = tbReservas.getValueAt(filaReservas, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja excluir os dados?");

					if(confirmar == JOptionPane.YES_OPTION){

						String valor = tbReservas.getValueAt(filaReservas, 0).toString();			
						reservaController.deletarController(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Excluído");
						limparTabelaReservas();
						preencherTabelaReservas();
					}
				}else if ( ! valorDeVerificacao.equals(tbHospedes.getSelectedRow())) {

					String hospedes = tbHospedes.getValueAt(filaHospedes, 0).toString();
					int confirmarh = JOptionPane.showConfirmDialog(null, "Deseja excluir os dados?");

					if(confirmarh == JOptionPane.YES_OPTION){

						String valor = tbHospedes.getValueAt(filaHospedes, 0).toString();
						hospedeController.deletarController(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Excluído");
						limparTabelaHospedes();
						preencherTabelaHospedes();
						preencherTabelaReservas();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Erro: fileira não selecionada, por favor realize uma busca e selecione uma fileira para excluir");
				}							
			}	
		});
}		

	
	
	
	public boolean Busca() {
			List<Reservas> listaDeReservas = listarReservas();
			List<Hospede> listaDeHospedes = listarHospedes();
			
			for(Hospede sobrenomeHospedeOuId : listaDeHospedes) {
				String ValorIdReserva = sobrenomeHospedeOuId.getIdReserva().toString();

				
				 if(  txtBuscar.getText().equals(sobrenomeHospedeOuId.getSobrenome().toString()) || txtBuscar.getText().equals(ValorIdReserva) ){	
					 					 modeloHospedes.addRow(new String[] {"N° Hóspede:", "Nome:", "Sobrenome:", "Nascimento:", "Nacionalidade:", "Telefone:","N° Reserva:"});
					 modeloHospedes.addRow(new Object[] {sobrenomeHospedeOuId.getId(), sobrenomeHospedeOuId.getNome(), sobrenomeHospedeOuId.getSobrenome(), sobrenomeHospedeOuId.getDataNacimento(),
							 sobrenomeHospedeOuId.getNacionalidade(), sobrenomeHospedeOuId.getTelefone(), sobrenomeHospedeOuId.getIdReserva()});    
					 for(Reservas idReserva : listaDeReservas) {
						 String ValorId = idReserva.getId().toString();
						 if(ValorId.equals(ValorIdReserva)) {
							 modelo.addRow(new String[] {"Numero de Reserva:", "Data Check In:", "Data Check Out:", "Valor:", "Forma de Pagamento:"});
							 modelo.addRow(new Object[] { idReserva.getId(), idReserva.getDataEntrada(),
									 idReserva.getDataSaida(), idReserva.getValor(), idReserva.getFormaPagamento()});
						 }
					 }	 
					 return true;
		        }
			}
			return false;
	}
	
	private void limparTabelaReservas() {
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}
	
	private void limparTabelaHospedes() {
		((DefaultTableModel) tbHospedes.getModel()).setRowCount(0);
	}
	
		
	private void alterarHospede() {
		
		Object objetoDaLinhaHospede = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
		Integer valorDeVerificacao = -1;
		if( objetoDaLinhaHospede instanceof Integer ){
			//hospede
			Integer idHospede = (Integer) objetoDaLinhaHospede ;
			String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
			String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
			String dataNascimento= (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3); 
			String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
			String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
			Integer idReservaH = (Integer) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6);
			this.hospedeController.alterarController( nome, sobrenome, dataNascimento, nacionalidade, telefone, idReservaH, idHospede);
			JOptionPane.showMessageDialog(this, "Hospede alterado com Sucesso");
		}else  {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID do hospede");
		}
	}
	
	private void alterarReserva() {
		List<Reservas> reserva = listarReservas();
		Object objetoDaLinhaReserva = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
		
		if ( objetoDaLinhaReserva instanceof Integer ) {
			//reserva
				// caso o problema data seja resolvido 
				//Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
			Integer idReserva = (Integer) objetoDaLinhaReserva;
			String dataEntrada = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
			String dataSaida = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
			String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
			String formaPagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
			this.reservaController.alterarController( dataEntrada, dataSaida, valor, formaPagamento, idReserva);
			 JOptionPane.showMessageDialog(this, "Reserva alterado com Sucesso");	
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID da reserva");
		}
	}
	
	private void preencherTabelaReservas() {
		List<Reservas> reserva = listarReservas() ;
		try {
			modelo.addRow(new String[] {"Numero de Reserva:", "Data Check In:", "Data Check Out:", "Valor:", "Forma de Pagamento:"});
			for (Reservas listaReserva : reserva) {
				modelo.addRow(new Object[] { listaReserva.getId(), listaReserva.getDataEntrada(),
						listaReserva.getDataSaida(), listaReserva.getValor(), listaReserva.getFormaPagamento()});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	private List<Reservas> listarReservas() {
		return this.reservaController.listarController();
	}
	
	
	private void preencherTabelaHospedes() {
		List<Hospede> hospede = listarHospedes() ;
			modeloHospedes.addRow(new String[] {"N° Hóspede:", "Nome:", "Sobrenome:", "Nascimento:", "Nacionalidade:", "Telefone:", "N° Reserva:"});
			for (Hospede listaHospede : hospede) {
				modeloHospedes.addRow(new Object[] {listaHospede.getId(), listaHospede.getNome(), listaHospede.getSobrenome(), listaHospede.getDataNacimento(),
						listaHospede.getNacionalidade(), listaHospede.getTelefone(), listaHospede.getIdReserva()});
			}
	}
	
	private void deletarReservas() {
		Object objetoDaLinhaReservas = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
		if (objetoDaLinhaReservas instanceof Integer) {
			Integer id = (Integer) objetoDaLinhaReservas;
			this.reservaController.deletarController(id);
			modelo.removeRow(tbReservas.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Item excluido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}
	
	private void deletarHospede() {
		Object objetoDaLinhaHospede = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
		if (objetoDaLinhaHospede instanceof Integer) {
			Integer id = (Integer) objetoDaLinhaHospede;
			this.hospedeController.deletarController(id);
			modeloHospedes.removeRow(tbHospedes.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Item excluido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}
	
	private List<Hospede> listarHospedes() {
		return this.hospedeController.listarController();
	}
	
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
