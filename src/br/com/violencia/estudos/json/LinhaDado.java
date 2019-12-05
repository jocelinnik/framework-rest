package br.com.violencia.estudos.json;

public class LinhaDado {
	private String campo;
	private Object valor;
	
	public LinhaDado(String campo, Object valor) {
		this.setCampo(campo);
		this.setValor(valor);
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
}