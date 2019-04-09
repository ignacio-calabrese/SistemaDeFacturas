package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import facturacion.*;

public class FramePrincipal extends JFrame {
	private JPanel panelN;
	private JPanel panelC;
	private JPanel panelS;
	
	private JButton buttonPedido;
	private JButton buttonFactura;
	private JButton buttonNotaCredito;
	
	private JButton buttonEliminarPedidos;
	private JButton buttonOperacionDiaria;
	private JButton buttonSalir;
	
	
	private ArrayList<Pedido> listaPedidos;
	private JList listaPedidosList;
	private Pedido[] arrayPedido;
	private JComboBox comboPedido;
	
	private ArrayList<Factura> listaFacturas;
	private JList listaFacturasList;
	private Factura[] arrayFactura;
	private JComboBox comboFactura;
	
	private ArrayList<NotaDeCredito> listaNotaDeCreditos;
	private JList listaNotaDeCreditosList;
	private Factura[] arrayNotaDeCredito;
	private JComboBox comboNotaDeCredito;
	
	private ArrayList<OperacionDiaria> listaOperacionDiaria;
	
	public FramePrincipal() {
		listaPedidos = new ArrayList<Pedido>();
		listaFacturas = new ArrayList<Factura>();
		listaNotaDeCreditos = new ArrayList<NotaDeCredito>();
		
		setTitle("Programa de facturación");
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
			
		listaPedidosList = new JList();
		
		buttonPedido = new JButton("Crear Pedido");
		buttonPedido.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread threadPedido = new Thread() {
					public void run() {
						FramePedido pedidoFrame = new FramePedido();
						pedidoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						pedidoFrame.setVisible(true);
						pedidoFrame.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								// TODO Auto-generated method stub
								if(pedidoFrame.getPedido() != null) {
									listaPedidos.add(pedidoFrame.getPedido());
					
									if (listaPedidos.size() > 0) {
										mostrarPedidos();								
									}
								} else {
									JOptionPane.showMessageDialog(null, "Debe crear un pedido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
									pedidoFrame.getPedido().setNroPedido(pedidoFrame.getPedido().getNroPedido());
								}
							}
							
						});
					}
				};
				threadPedido.start();
			}
		});
		
		arrayPedido = new Pedido[listaPedidos.size()];
		listaPedidos.toArray(arrayPedido);
		comboPedido = new JComboBox(arrayPedido);
		
		buttonFactura = new JButton("Crear Factura");
		buttonFactura.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Thread threadFactura = new Thread() {
					public void run() {
						mostrarPedidos();
						
						if (listaPedidos.size() > 0) {
							arrayPedido = new Pedido[listaPedidos.size()];
							listaPedidos.toArray(arrayPedido);
							comboPedido = new JComboBox(arrayPedido);
							int opcion = JOptionPane.showConfirmDialog(null, comboPedido, "Seleccione pedido", JOptionPane.YES_NO_OPTION);
							
							if(opcion == JOptionPane.YES_OPTION) {
								Pedido pedidoSelect = (Pedido) comboPedido.getSelectedItem();
								Factura factura = new Factura(pedidoSelect);
								listaFacturas.add(factura);
								
								FrameFactura facturaFrame = new FrameFactura(factura);
								facturaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								facturaFrame.setVisible(true);
							}
							if (listaFacturas.size() > 0) {
								mostrarFacturas();						
							}						
						} else {
							JOptionPane.showMessageDialog(null, "No hay pedidos", "ERROR", JOptionPane.ERROR_MESSAGE);
						}						
					}
				};
				threadFactura.start();
			}
		});
		
		listaPedidosList = new JList();
		listaFacturasList = new JList();
		listaNotaDeCreditosList	= new JList();
		
		buttonNotaCredito = new JButton("Crear Nota de Crédito");
		buttonNotaCredito.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread threadNotaCredito = new Thread() {
					public void run() {
						mostrarFacturas();
						if (listaFacturas.size() > 0) {
							arrayFactura = new Factura[listaFacturas.size()];
							listaFacturas.toArray(arrayFactura);
							comboFactura = new JComboBox(arrayFactura);
							int opcion = JOptionPane.showConfirmDialog(null, comboFactura, "Seleccione Factura", JOptionPane.YES_NO_OPTION);
							
							if(opcion == JOptionPane.YES_OPTION) {
								Factura facturaSelect = (Factura) comboFactura.getSelectedItem();
								NotaDeCredito notaDeCredito = new NotaDeCredito(facturaSelect);
								listaNotaDeCreditos.add(notaDeCredito);
								
								FrameNotaDeCredito notaDeCreditoFrame = new FrameNotaDeCredito(notaDeCredito);
								notaDeCreditoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								notaDeCreditoFrame.setVisible(true);						
							}
							if (listaNotaDeCreditos.size() > 0) {
								mostrarNotaDeCreditos();	
							}
						} else {
							JOptionPane.showMessageDialog(null, "No hay Facturas", "ERROR", JOptionPane.ERROR_MESSAGE);
						}						
					}
				};
				threadNotaCredito.start();
			}
		});
		
		buttonEliminarPedidos = new JButton("Eliminar Pedidos");
		buttonEliminarPedidos.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (listaPedidos.size() > 0) {
					arrayPedido = new Pedido[listaPedidos.size()];
					listaPedidos.toArray(arrayPedido);
					comboPedido = new JComboBox(arrayPedido);
					JOptionPane.showMessageDialog(null, comboPedido, "Seleccione pedido", JOptionPane.INFORMATION_MESSAGE);
					int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro?", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION) {
						Pedido pedidoSelect = (Pedido) comboPedido.getSelectedItem();
						listaPedidos.remove(pedidoSelect);
						mostrarPedidos();						
					}
				} else {
					JOptionPane.showMessageDialog(null, "No hay pedidos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		buttonOperacionDiaria = new JButton("Operación Diaria");
		buttonOperacionDiaria.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (listaFacturas.size() > 0) {
					mostrarOperatoriaDiaria();
					
					FrameOperacionDiaria operacionDiariaFrame = new FrameOperacionDiaria(listaOperacionDiaria);
					operacionDiariaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					operacionDiariaFrame.setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "No hay Operaciones hoy", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		buttonSalir = new JButton("Salir");
		buttonSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(null, "Salir del programa", "Salir", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}); 
		
		panelN = new JPanel();
		
		panelN.add(buttonPedido);
		panelN.add(buttonEliminarPedidos);
		panelN.add(buttonFactura);
		panelN.add(buttonNotaCredito);
		
		add(panelN, BorderLayout.NORTH);
		
		panelC = new JPanel();
		
		panelC.setLayout(new BorderLayout());	
		panelC.add(new JScrollPane(listaPedidosList), BorderLayout.WEST);
		panelC.add(new JScrollPane(listaFacturasList), BorderLayout.CENTER);
		
		panelC.add(new JScrollPane(listaNotaDeCreditosList), BorderLayout.EAST);
		
		add(panelC, BorderLayout.CENTER);
		
		panelS = new JPanel();
		
		panelS.add(buttonOperacionDiaria);
		panelS.add(buttonSalir);
		add(panelS, BorderLayout.SOUTH);
		
	}
	
	public void mostrarPedidos() {
		listaPedidos.sort(new Comparator<Pedido>() {
			@Override
			public int compare(Pedido p1, Pedido p2) {
				// TODO Auto-generated method stub
				return new Integer(p1.getNroPedido()).compareTo(new Integer(p2.getNroPedido()));
			}
		});
		
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i < listaPedidos.size(); i++) {
		model.addElement(listaPedidos.get(i));	
		}
		listaPedidosList.setModel(model);
	}
	
	public void mostrarFacturas() {
		listaFacturas.sort(new Comparator<Factura>() {
			@Override
			public int compare(Factura f1, Factura f2) {
				// TODO Auto-generated method stub
				return f1.getNroFactura().compareTo(f2.getNroFactura());
			}
		});
		
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i < listaFacturas.size(); i++) {
		model.addElement(listaFacturas.get(i));	
		}
		listaFacturasList.setModel(model);
	}
	
	public void mostrarNotaDeCreditos() {
		listaNotaDeCreditos.sort(new Comparator<NotaDeCredito>() {
			@Override
			public int compare(NotaDeCredito n1, NotaDeCredito n2) {
				// TODO Auto-generated method stub
				return n1.getNroNotaDeCredito().compareTo(n2.getNroNotaDeCredito());
			}
		});
		
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i < listaNotaDeCreditos.size(); i++) {
		model.addElement(listaNotaDeCreditos.get(i));	
		}
		listaNotaDeCreditosList.setModel(model);
	}
	
	public void mostrarOperatoriaDiaria() {
		listaOperacionDiaria = new ArrayList<OperacionDiaria>();
		Date actual = new GregorianCalendar().getTime();
		String hoy = new SimpleDateFormat("dd/MM/yy").format(actual);
		for (int i = 0; i < listaFacturas.size(); i++) {
			if (new SimpleDateFormat("dd/MM/yy").format(listaFacturas.get(i).getFechaEmision()).equals(hoy) ) {
				listaOperacionDiaria.add(new OperacionDiaria(listaFacturas.get(i)));							
			}
		}
		
		for (int i = 0; i < listaNotaDeCreditos.size(); i++) {
			if (new SimpleDateFormat("dd/MM/yy").format(listaNotaDeCreditos.get(i).getFechaEmision()).equals(hoy)) {
				listaOperacionDiaria.add(new OperacionDiaria(listaNotaDeCreditos.get(i)));
			}

		}
	}

}
