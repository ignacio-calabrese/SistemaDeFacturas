package GUI;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import facturacion.OperacionDiaria;

public class FrameOperacionDiaria extends JFrame{
	private JPanel panel;
	private TableModel modeloOperacionDiaria;
	private JTable tableOperacionDiaria;
	private JScrollPane scrollpane;

	public FrameOperacionDiaria(ArrayList<OperacionDiaria> listaOperacionDiaria) {
		setTitle("Operación Diaria");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height -50;
		setBounds(0,0,width, height);
		setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, width-10, height-20);
		
		modeloOperacionDiaria = new AbstractTableModel() {
	          public int getColumnCount() { 
	        	  return 9; 
	          }
	          
	          public int getRowCount() { 
	        	  return listaOperacionDiaria.size();
	          }
	          
	          public Object getValueAt (int row, int col) {
	        	  OperacionDiaria aux = listaOperacionDiaria.get(row);          
	              switch (col) {
	                  case 0:                                                        
	                      return aux.getCliente();
	                      //break;
	                  case 1:                                                         
	                      return aux.getTipoDeDocumento();
	                      ///break;
	                  case 2:                                                        
	                      return aux.getLetra();
	                      //break;
	                  case 3:                                                         
	                      return aux.getNro();
	                      //break;
	                  case 4:                                                        
	                      return aux.getNroTalonario();
	                      //break;
	                  case 5:                                                         
	                      return aux.getFechaDeEmision();  
	                      ///break;
	                  case 6:                                                        
	                      return aux.getTotal();
	                      //break;
	                  case 7:                                                         
	                      return aux.getPorcentajeIVA();
	                      //break;
	                  case 8:                                                        
	                      return aux.getTotalIVA();
	                      //break;
	               }
	               return null;
	          }
	          
	          public String getColumnName(int col) {
	        	  switch (col) {
	                case 0:                                                        
	                    return "Cliente";
	                    //break;
	                case 1:                                                         
	                    return "Tipo de Documento";
	                    ///break;
	                case 2:                                                        
	                    return "Letra";
	                    //break;
	                case 3:                                                         
	                    return "N°";
	                    //break;
	                case 4:                                                        
	                    return "N° de Talonario";
	                    //break;
	                case 5:                                                         
	                    return "Fecha de Emisión";  
	                    ///break;
	                case 6:                                                        
	                    return "Total";
	                    //break;
	                case 7:                                                         
	                    return "PorcentajeIVA";
	                    //break;
	                case 8:                                                        
	                    return "TotalIVA";
	                    //break;
	        	  }
	             return null;
	          }
	      };
	      
	      tableOperacionDiaria = new JTable(modeloOperacionDiaria);
	      tableOperacionDiaria.setRowHeight(250);
	      TableColumnModel columnModel = tableOperacionDiaria.getColumnModel();
	      columnModel.getColumn(0).setPreferredWidth(250);
	      
	      scrollpane = new JScrollPane(tableOperacionDiaria);
	      scrollpane.setBounds(10, 10, width-35, height-50);
	      panel.add(scrollpane);
	      
	      add(panel);
	}
}
