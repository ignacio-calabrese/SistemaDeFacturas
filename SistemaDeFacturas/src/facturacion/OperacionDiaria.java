package facturacion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OperacionDiaria {
	private Cliente cliente;
	private String tipoDeDocumento;
	private String letra;
	private String nro;
	private String nroTalonario;
	private String fechaDeEmision;
	private double total;
	private double porcentajeIVA;
	private double totalIVA;
	
	public OperacionDiaria(Factura factura) {
		cliente = factura.getCliente();
		tipoDeDocumento = factura.getClass().getSimpleName();
		letra = factura.getLetra();
		nro = factura.getNroFactura();
		nroTalonario = factura.getNroTalonario();
		fechaDeEmision = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(factura.getFechaEmision());
		total = factura.getTotal();
		porcentajeIVA = factura.getPorcentajeIVA();
		totalIVA = factura.getTotalIVA();
	}
	
	public OperacionDiaria(NotaDeCredito notaDeCredito) {
		cliente = notaDeCredito.getCliente();
		tipoDeDocumento = notaDeCredito.getClass().getSimpleName();
		letra = notaDeCredito.getLetra();
		nro = notaDeCredito.getNroNotaDeCredito();
		nroTalonario = notaDeCredito.getNroTalonario();
		fechaDeEmision = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(notaDeCredito.getFechaEmision());
		total = notaDeCredito.getTotal();
		porcentajeIVA = notaDeCredito.getPorcentajeIVA();
		totalIVA = notaDeCredito.getTotalIVA();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getTipoDeDocumento() {
		return tipoDeDocumento;
	}

	public String getLetra() {
		return letra;
	}

	public String getNro() {
		return nro;
	}

	public String getNroTalonario() {
		return nroTalonario;
	}

	public String getFechaDeEmision() {
		return fechaDeEmision;
	}

	public double getTotal() {
		return total;
	}

	public double getPorcentajeIVA() {
		return porcentajeIVA;
	}

	public double getTotalIVA() {
		return totalIVA;
	}
}
