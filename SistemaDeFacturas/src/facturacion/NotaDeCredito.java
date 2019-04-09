package facturacion;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class NotaDeCredito {
	private Pedido pedido;
	
	//Cabecera
	private Date fechaEmision;
	private static int num = 0;
	private String nroNotaDeCredito;
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
	
	public NotaDeCredito(Factura factura) {
		this.fechaEmision = new GregorianCalendar().getTime();
		num++;
		this.nroNotaDeCredito = "00001-"+ notaDeCreditoNumero(num);
		this.nroTalonario = notaDeCreditoNumero(num) + "-5000";	
		this.pedido = factura.getPedido(); 
		this.cliente = factura.getCliente();
		this.letra = factura.getLetra();
		this.porcentajeIVA = factura.getPorcentajeIVA();
		this.productos = pedido.getProductos();
		this.detalles = factura.getDetalles();
		this.total = factura.getTotal();		
		this.totalIVA = factura.getTotalIVA();
	}
	
	private String notaDeCreditoNumero(int num){
	     DecimalFormat format = new DecimalFormat("00000");
	     return format.format(Integer.valueOf(num));
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public String getNroNotaDeCredito() {
		return nroNotaDeCredito;
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
		return "Nota De Crédito N°: " + nroNotaDeCredito; 
	}
}
