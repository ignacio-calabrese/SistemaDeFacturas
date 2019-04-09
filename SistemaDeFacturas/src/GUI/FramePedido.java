package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import facturacion.Cliente;
import facturacion.Pedido;
import facturacion.Producto;

public class FramePedido extends JFrame {
	private JPanel panel;
	
	private JPanel panelN;
	private JLabel nroPedidoLabel;
	private static int num = 0;
	private JTextField nroPedidoTextField;

	private JPanel panelW;
	private JLabel nroClienteLabel;
	private JTextField nroClienteTextField;
	private JLabel domicilioLabel;
	private JTextField domicilioTextField;
	private JLabel condImpositivaLabel;
	private JComboBox condImpositivaComboBox;
	private JLabel tipoDocumentoLabel;
	private JTextField tipoDocumentoTextField;
	private JLabel nroDocumentoLabel;
	private JTextField nroDocumentoTextField;
	
	private JPanel panelC;
	private JLabel codigoLabel;
	private JTextField codigoTextField;
	private JLabel nombreLabel;
	private JTextField nombreTextField;
	private JLabel precioUnitarioLabel;
	private JTextField precioUnitarioTextField;
	private JLabel cantidadLabel;
	private JTextField cantidadTextField;
	private JLabel precioLabel;
	private JTextField precioTextField;
	private JButton buttonCargarProducto;
	
	private int codigo;
	private String nombre;
	private double precioUnitario;
	private double cantidad;
	private double precio;
	private Producto producto;
	
	private JPanel panelE;
	private TableModel modeloProducto;
    private JTable tableProducto;
    private JScrollPane scrollpane;
	
	private JPanel panelS;
	private JButton buttonGenerarPedido;
	
	private ArrayList<Producto> productos;
	private Pedido pedido;
	private Cliente cliente;
	
	public FramePedido (){
		
		productos = new ArrayList<Producto>();
		setTitle("Pedido");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height -50;
		setBounds(0,0,width, height);panel = new JPanel();
		panel.setLayout(new BorderLayout());
			
		// Pedido
		panelN = new JPanel();
		
		nroPedidoLabel = new JLabel("Nro de Pedido");
		num++;
		nroPedidoTextField = new JTextField(10);
		nroPedidoTextField.setText(Integer.toString(num));
		nroPedidoTextField.setEditable(false);
		
		panelN.add(nroPedidoLabel);
		panelN.add(nroPedidoTextField);
		
		panel.add(panelN, BorderLayout.NORTH);
		
		// Para cada cliente 
		panelW = new JPanel();
		panelW.setLayout(new GridLayout(0, 2, 10,10));
		
		nroClienteLabel = new JLabel("Nro de Cliente");
		nroClienteTextField = new JTextField();
		domicilioLabel = new JLabel("Domicilio");
		domicilioTextField = new JTextField(10);
		condImpositivaLabel = new JLabel("Condición Impositiva");
		
		condImpositivaComboBox = new JComboBox();
		condImpositivaComboBox.addItem("IVA Responsable Inscripto");
		condImpositivaComboBox.addItem("Monotributo");
		condImpositivaComboBox.addItem("IVA no Responsable");
		
		tipoDocumentoLabel = new JLabel("Tipo de Documento");
		tipoDocumentoTextField = new JTextField(10);
		nroDocumentoLabel = new JLabel("Nro de Documento");
		nroDocumentoTextField = new JTextField(10);
		
		
		panelW.add(nroClienteLabel);
		panelW.add(nroClienteTextField);
		panelW.add(domicilioLabel);
		panelW.add(domicilioTextField);
		panelW.add(condImpositivaLabel);
		panelW.add(condImpositivaComboBox);
		panelW.add(tipoDocumentoLabel);
		panelW.add(tipoDocumentoTextField);
		panelW.add(nroDocumentoLabel );
		panelW.add(nroDocumentoTextField);
			
		panel.add(panelW, BorderLayout.WEST);
		
		// cada Producto
		codigo = 0;
		nombre = "";
		precioUnitario = 0;
		cantidad = 0;
		precio = 0;
		producto = null;
		
		panelC = new JPanel();
		panelC.setLayout(new GridLayout(0,2));
		
		codigoLabel = new JLabel("Código");
		codigoTextField = new JTextField(10);
		codigoTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				try {
					codigo = Integer.parseInt(codigoTextField.getText());
					if (codigo <= 0) {
						JOptionPane.showMessageDialog(null, "El código debe ser mayor a 0", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					} 
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Los campos Código, Precio Unitario y Cantidad sólo admiten númreros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
		
		nombreLabel = new JLabel("Nombre");
		nombreTextField = new JTextField(10);
		precioUnitarioLabel = new JLabel("Precio Unitario");
		precioUnitarioTextField = new JTextField(10);
		cantidadLabel = new JLabel("Cantidad");
		cantidadTextField = new JTextField(10);
		precioLabel = new JLabel("Precio");
		precioTextField = new JTextField(10);
		precioTextField.setEnabled(false);
		
		precioUnitarioTextField.addFocusListener(new FocusAdapter() {		
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				try {
					precioUnitario = Double.parseDouble(precioUnitarioTextField.getText());
					if (precioUnitario <= 0) {
						JOptionPane.showMessageDialog(null, "El Precio Unitario debe ser mayor a 0", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						
					} else {						
						precio = precioUnitario * cantidad;
						precioTextField.setText(Double.toString(precio));
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Los campos Código, Precio Unitario y Cantidad sólo admiten númreros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					
				}
			}			
		});
		
		cantidadTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				try {
					cantidad = Double.parseDouble(cantidadTextField.getText());
					if (cantidad <= 0) {
						JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					} else {
						precio = precioUnitario * cantidad;
						precioTextField.setText(Double.toString(precio));						
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Los campos Código, Precio Unitario y Cantidad sólo admiten númreros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
		
		panelC.add(codigoLabel);
		panelC.add(codigoTextField);
		panelC.add(nombreLabel);
		panelC.add(nombreTextField);
		panelC.add(precioUnitarioLabel);
		panelC.add(precioUnitarioTextField);
		panelC.add(cantidadLabel);
		panelC.add(cantidadTextField);
		panelC.add(precioLabel );
		panelC.add(precioTextField);
		
		panel.add(panelC, BorderLayout.CENTER);
		
		panelE = new JPanel();
		panelE.setLayout(new BorderLayout());
	
		modeloProducto = new AbstractTableModel() {
	          public int getColumnCount() { 
	        	  return 5; 
	          }
	          
	          public int getRowCount() { 
	        	  return productos.size();
	          }
	          
	          public Object getValueAt (int row, int col) {
	              Producto aux = productos.get(row);          
	              switch (col) {
	                  case 0:                                                        
	                      return aux.getCodigo();
	                      //break;
	                  case 1:                                                         
	                      return aux.getNombre();
	                      ///break;
	                  case 2:                                                        
	                      return aux.getPrecioUnitario();
	                      //break;
	                  case 3:                                                         
	                      return aux.getCantidad();
	                      //break;
	                  case 4:                                                        
	                      return aux.getPrecio();
	                      //break;
	               }
	               return null;
	          }
	          
	          public String getColumnName(int col) {
	        	  switch (col) {
	                case 0:                                                        
	                    return "Codigo";
	                    //break;
	                case 1:                                                         
	                    return "Nombre";
	                    ///break;
	                case 2:                                                        
	                    return "Precio Unitario";
	                    //break;
	                case 3:                                                         
	                    return "Cantidad";
	                    //break;
	                case 4:                                                        
	                    return "Precio";
	                    //break;
	             }
	             return null;
	          }
	      };
	      
		 
	      tableProducto = new JTable(modeloProducto);
	      TableColumnModel columnModel = tableProducto.getColumnModel();
	      for (int i = 0; i < tableProducto.getColumnCount(); i++) {
	    	  columnModel.getColumn(i).setPreferredWidth(100);
	    	  columnModel.getColumn(i).setResizable(true);
	      }
	      
	      scrollpane = new JScrollPane(tableProducto);
	      panelE.add(scrollpane, BorderLayout.NORTH);
	      
	      buttonCargarProducto = new JButton("Agregar Producto");
	      buttonCargarProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(codigoTextField.getText().equals("") || nombreTextField.getText().equals("") || precioUnitarioTextField.getText().equals("") || cantidadTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Complete los datos del producto", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						codigo = Integer.parseInt(codigoTextField.getText());
						if (codigo <= 0) {
							JOptionPane.showMessageDialog(null, "El código debe ser mayor a 0", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						} else {
							nombre = nombreTextField.getText();
							precioUnitario = Double.parseDouble(precioUnitarioTextField.getText());
							cantidad = Double.parseDouble(cantidadTextField.getText());
							producto = new Producto(codigo, nombre, precioUnitario, cantidad);
							productos.add(producto);
							
							JOptionPane.showMessageDialog(null, "Producto cargado", "Agregado", JOptionPane.INFORMATION_MESSAGE);												
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Los campos Código, Precio Unitario y Cantidad sólo admiten númreros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						
					}
					
					panelE.remove(scrollpane);
					mostrarProductos();	
					
					codigoTextField.setText("");
					nombreTextField.setText("");
					precioUnitarioTextField.setText("");
					cantidadTextField.setText("");
					precioTextField.setText("");					
				}		
			}
		});
	      
		panelE.add(buttonCargarProducto, BorderLayout.SOUTH);
		
		panel.add(panelE, BorderLayout.EAST);
		
		panelS = new JPanel();
		
		buttonGenerarPedido = new JButton("Generar Pedido");
		buttonGenerarPedido.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int nroPedido =Integer.parseInt(nroPedidoTextField.getText()); 
				
				try {
					if(nroClienteTextField.getText().equals("") || domicilioTextField.equals("") || tipoDocumentoTextField.equals("") || nroDocumentoTextField.equals("")) {
						JOptionPane.showMessageDialog(null, "Complete los datos del cliente", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					} else {
						int nroCliente =Integer.parseInt(nroClienteTextField.getText()); 
						if (nroCliente <= 0) {
							JOptionPane.showMessageDialog(null, "El Nro de Cliente debe ser mayor a 0", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						} else {
							String domicilio = domicilioTextField.getText(); 
							int codCondImpositiva = 0;
							
							if(condImpositivaComboBox.getSelectedItem().equals("IVA Responsable Inscripto")) {
								codCondImpositiva = 1;
							} else if(condImpositivaComboBox.getSelectedItem().equals("Monotributo")) {
								codCondImpositiva = 2;
							} else if(condImpositivaComboBox.getSelectedItem().equals("IVA no Responsable")) {
								codCondImpositiva = 3;
							}
							
							String tipoDocumento = tipoDocumentoTextField.getText(); 
							int nroDocumento = Integer.parseInt(nroDocumentoTextField.getText());
							
							if (nroDocumento <= 0) {
								JOptionPane.showMessageDialog(null, "El Nro de Documento debe ser mayor a 0", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
							} else {
								cliente = new Cliente(nroCliente, domicilio, codCondImpositiva, tipoDocumento, nroDocumento);
								
								if(productos.size() > 0) {
									pedido = new Pedido(nroPedido, cliente, productos);				
									JOptionPane.showMessageDialog(panel, "Pedido cargado Total: " + pedido.getTotal());			
								} else {
									JOptionPane.showMessageDialog(null, "Debe agregar al menos un producto", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
								}								
							}							
						}
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Los campos Nro de Cliente y Nro de Documento sólo admiten númreros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);		
				}
			}
		});
				
		panelS.add(buttonGenerarPedido);
		panel.add(panelS, BorderLayout.SOUTH);	
		
		add(panel);
	}
	
	public void mostrarProductos() {
		modeloProducto = new AbstractTableModel() {
	          public int getColumnCount() { 
	        	  return 5; 
	          }
	          
	          public int getRowCount() { 
	        	  return productos.size();
	          }
	          
	          public Object getValueAt (int row, int col) {
	              Producto aux = productos.get(row);              
	              switch (col) {
	                  case 0:                                                        
	                      return aux.getCodigo();
	                      //break;
	                  case 1:                                                         
	                      return aux.getNombre();
	                      ///break;
	                  case 2:                                                        
	                      return aux.getPrecioUnitario();
	                      //break;
	                  case 3:                                                         
	                      return aux.getCantidad();
	                      //break;
	                  case 4:                                                        
	                      return aux.getPrecio();
	                      //break;
	               }
	              return null;
	          }
	          
	          public String getColumnName(int col) {
	        	  switch (col) {
	                  case 0:                                                        
	                      return "Codigo";
	                      //break;
	                  case 1:                                                         
	                      return "Nombre";
	                      ///break;
	                  case 2:                                                        
	                      return "Precio Unitario";
	                      //break;
	                  case 3:                                                         
	                      return "Cantidad";
	                      //break;
	                  case 4:                                                        
	                      return "Precio";
	                      //break;
	               }
	               return null;
	          }
	      };
	      
	      tableProducto = new JTable(modeloProducto);
	      TableColumnModel columnModel = tableProducto.getColumnModel();
	      for (int i = 0; i < tableProducto.getColumnCount(); i++) {
	    	  columnModel.getColumn(i).setPreferredWidth(100);			
	      }
	      
	      scrollpane.add(tableProducto);
	      panelE.add(scrollpane, BorderLayout.NORTH);
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
}
