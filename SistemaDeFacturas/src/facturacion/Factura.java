package facturacion;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Factura {
	private Pedido pedido;
	
	//Cabecera
	private Date fechaEmision;
	private static int num = 0;
	private String nroFactura;
	private String nroTalonario;
	private String letra;
	private Cliente cliente;
	
	//Detalle
	private ArrayList<Producto> productos;
	private double porcentajeIVA;
	private ArrayList<DetalleFactura> detalles;
	
	//Pie de Factura
	private double total;
	private double totalIVA;
	
	public Factura(Pedido pedido) {
		this.fechaEmision = new GregorianCalendar().getTime();
		num++;
		this.nroFactura = "00001-"+ facturaNumero(num);
		this.nroTalonario = facturaNumero(num) + "-5000";	
		this.pedido = pedido; 
		this.cliente = pedido.getCliente();
		
		if (cliente.getCodCondImpositiva() == 1) {
			letra = "A";
			this.porcentajeIVA = 10.05;
		} else if (cliente.getCodCondImpositiva() == 2) {
			letra = "B";
			this.porcentajeIVA = 21;
		} else if (cliente.getCodCondImpositiva() == 3) {
			letra = "X";
			this.porcentajeIVA = 70;
		}
		
		this.productos = pedido.getProductos();
		detalles = new ArrayList<DetalleFactura>();
		for(Producto producto : productos) {
			DetalleFactura detalle = new DetalleFactura(producto, porcentajeIVA);
			detalles.add(detalle);
		}
		
		totales();
	}
	
	private String facturaNumero(int num){
	     DecimalFormat format = new DecimalFormat("00000");
	     return format.format(Integer.valueOf(num));
	}
	
	private void totales() {
		for (int i = 0; i < detalles.size(); i++) {
			total += detalles.get(i).getPrecioNETO();		
			totalIVA += detalles.get(i).getPrecioVenta();	
		}	
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public String getNroFactura() {
		return nroFactura;
	}

	public String getNroTalonario() {
		return nroTalonario;
	}

	public String getLetra() {
		return letra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public ArrayList<DetalleFactura> getDetalles() {
		return detalles;
	}

	public double getTotal() {
		return total;
	}

	public double getTotalIVA() {
		return totalIVA;
	}

	public double getPorcentajeIVA() {
		return porcentajeIVA;
	}

	@Override
	public String toString() {
		return "Factura N°: " + nroFactura;
	}
}
