package GUI;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import facturacion.DetalleFactura;
import facturacion.NotaDeCredito;

public class FrameNotaDeCredito extends JFrame{

	//Cabecera
	private JPanel panelN;
	private JLabel nums;
	private JLabel cliente;
	
	//Detalle
	private JPanel panelC;
	private TableModel modeloDetallesNotaDeCredito;
    private JTable tableDetallesNotaDeCredito;
    private JScrollPane scrollpane;
    
	private ArrayList<DetalleFactura> detalles;
	
	//Pie de Factura
	private JPanel panelS;
	private JLabel total;
	private JLabel totalIVA;
	
	public FrameNotaDeCredito(NotaDeCredito notaDeCredito) {
		
		setTitle("Nota De Cr�dito");
		setBounds(10, 100, 1000, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
		
		panelN = new JPanel();
		
		nums = new JLabel("<html>"
				+ "<p>Fecha de Emisi�n: " + new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(notaDeCredito.getFechaEmision()) + "</p>"
				+ "<p>Nota De Cr�dito N�: " + notaDeCredito.getNroNotaDeCredito() + "</p>"
				+ "<p>Talonario N�: " + notaDeCredito.getNroTalonario() + "</p>"
				+ "<p>Letra: " + notaDeCredito.getLetra() + "</p>" 
				+ "</html>");
		cliente = new JLabel(notaDeCredito.getCliente().toString());
		
		panelN.add(cliente);
		panelN.add(nums);
		
		add(panelN, BorderLayout.NORTH);
		
		//Detalle
		panelC = new JPanel();
		panelC.setBounds(10, 50, this.getWidth()-30, this.getHeight()-100);
		panelC.setLayout(null);
		
		detalles = notaDeCredito.getDetalles();
		
		modeloDetallesNotaDeCredito = new AbstractTableModel() {
	          public int getColumnCount() { 
	        	  return 9; 
	          }
	          
	          public int getRowCount() { 
	        	  return detalles.size();
	          }
	          
	          public Object getValueAt (int row, int col) {
	        	  DetalleFactura aux = detalles.get(row);          
	              switch (col) {
	                  case 0:                                                        
	                      return aux.getProducto().getCodigo();
	                      //break;
	                  case 1:                                                         
	                      return aux.getProducto().getNombre();
	                      ///break;
	                  case 2:                                                        
	                      return aux.getPrecioUnitario();
	                      //break;
	                  case 3:                                                         
	                      return aux.getCantidad();
	                      //break;
	                  case 4:                                                        
	                      return aux.getProducto().getPrecio();
	                      //break;
	                  case 5:                                                         
	                      return aux.getPrecioNETO();
	                      ///break;
	                  case 6:                                                        
	                      return aux.getPorcentajeIVA();
	                      //break;
	                  case 7:                                                         
	                      return aux.getMontoIVA();
	                      //break;
	                  case 8:                                                        
	                      return aux.getPrecioVenta();
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
		              case 5:                                                         
		                  return "precio NETO";
		                  ///break;
		              case 6:                                                        
		                  return "% de IVA";
		                  //break;
		              case 7:                                                         
		                  return "monto de IVA";
		                  //break;
		              case 8:                                                        
		                  return "precio de Venta";
		                  //break;
		           }
		           return null;
	          }
	      };
	      
	      tableDetallesNotaDeCredito = new JTable(modeloDetallesNotaDeCredito);
	      TableColumnModel columnModel = tableDetallesNotaDeCredito.getColumnModel();
	      for (int i = 0; i < tableDetallesNotaDeCredito.getColumnCount(); i++) {
	    	  columnModel.getColumn(i).setPreferredWidth(200);	
	      }
	      
	      scrollpane = new JScrollPane(tableDetallesNotaDeCredito);
	      scrollpane.setBounds(10, 10, panelC.getWidth()-35, panelC.getHeight()-100);
	      panelC.add(scrollpane);
	      	
	      add(panelC, BorderLayout.CENTER);
		
	      //Pie de Factura
		
	      panelS = new JPanel();
	      total = new JLabel("TOTAL: $" + Double.toString(notaDeCredito.getTotal()));
	      totalIVA  = new JLabel("TOTAL IVA: $" + Double.toString(notaDeCredito.getTotalIVA()));;
		
	      panelS.add(total);
	      panelS.add(totalIVA);
		
	      add(panelS, BorderLayout.SOUTH);
	}
}
