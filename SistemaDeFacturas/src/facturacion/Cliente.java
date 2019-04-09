package facturacion;

public class Cliente{
	private int nroCliente;
	private String domicilio;
	private int	codCondImpositiva;
	private String	condImpositiva;
	private String tipoDocumento;
	private int nroDocumento;
	
	public Cliente(int nroCliente, String domicilio, int codCondImpositiva, String tipoDocumento, int nroDocumento) {
		this.nroCliente = nroCliente;
		this.domicilio = domicilio;
		this.codCondImpositiva = codCondImpositiva;
		if(codCondImpositiva == 1) {
			condImpositiva = "IVA Responsable Inscripto";
		} else if(codCondImpositiva == 2) {
			condImpositiva = "Monotributo";
		} else if(codCondImpositiva == 3) {
			condImpositiva = "IVA no Responsable";
		}
		
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
	}	

	public int getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(int nroCliente) {
		this.nroCliente = nroCliente;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getCodCondImpositiva() {
		return codCondImpositiva;
	}

	public void setCodCondImpositiva(int codCondImpositiva) {
		this.codCondImpositiva = codCondImpositiva;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getCondImpositiva() {
		return condImpositiva;
	}

	@Override
	public String toString() {
		return "<html>"
				+ "<p>Cliente:</p>"
				+ "<p>Nro de Cliente: " + nroCliente + "</p>" 
				+ "<p>Domicilio: " + domicilio + "</p>"
				+ "<p>Condición impositiva: " + condImpositiva + "</p>"
				+ "<p>Tipo de documento: " + tipoDocumento + "</p>"
				+ "<p>Nro de documento: " + nroDocumento + "</p>"
				+ "</html>";
	}
		
}