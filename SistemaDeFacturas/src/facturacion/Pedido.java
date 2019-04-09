package facturacion;

import java.util.ArrayList;

public class Pedido{
	
	private int nroPedido;
	private Cliente cliente;
	private ArrayList<Producto> productos;
	private double total;
	
	public Pedido(int nroPedido, Cliente cliente,  ArrayList<Producto> productos) {
		this.nroPedido = nroPedido;
		this.cliente = cliente;
		this.productos =  productos;	
		for (Producto p : productos) {
			total += p.getPrecio();
		}		
	}
	
	public int getNroPedido() {
		return nroPedido;
	}

	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public double getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "Pedido N°: " + nroPedido;
	}	
}
