package facturacion;

public class DetalleFactura {
	private Producto producto;
	private double precioUnitario;
	private double porcentajeIVA;
	private double cantidad;
	private double precioVenta;
	private double precioNETO;
	private double montoIVA;
	
	public DetalleFactura(Producto producto, double porcentajeIVA) {
		this.producto = producto;
		this.precioUnitario = producto.getPrecioUnitario();
		this.porcentajeIVA = porcentajeIVA;
		this.cantidad = producto.getCantidad();
		this.precioNETO = producto.getPrecio();
		this.montoIVA = precioNETO * porcentajeIVA /100;
		this.precioVenta = precioNETO + montoIVA;
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public double getPorcentajeIVA() {
		return porcentajeIVA;
	}
	
	public void setPorcentajeIVA(double porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}
	
	public double getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getPrecioVenta() {
		return precioVenta;
	}
	
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	public double getPrecioNETO() {
		return precioNETO;
	}
	
	public void setPrecioNETO(double precioNETO) {
		this.precioNETO = precioNETO;
	}
	
	public double getMontoIVA() {
		return montoIVA;
	}
	
	public void setMontoIVA(double montoIVA) {
		this.montoIVA = montoIVA;
	}

	@Override
	public String toString() {
		return "  " + producto 
				+ ", precio Unitario: $" + precioUnitario 
				+ ", % de IVA: " + porcentajeIVA 
				+ ", cantidad: " + cantidad 
				+ ", precio de Venta: $" + precioVenta 
				+ ", precio NETO: $" + precioNETO 
				+ ", monto de IVA: " + montoIVA;
	}
}
