package br.com.violencia.estudos.json;

import java.util.ArrayList;
import br.com.violencia.estudos.json.LinhaDado;

public class Json {
	private ArrayList<LinhaDado> json;
	
	public Json() {
		json = new ArrayList<LinhaDado>();
	}
	
	public void adicionar(String campo, Object valor) {
		campo = "\"" + campo + "\"";
		LinhaDado novo = new LinhaDado(campo, valor);
		this.json.add(novo);
	}
	
	public String gerarJSON() {
		String saida = "{\n";
		
		for(LinhaDado l : this.json) {
			saida += l.getCampo() + ": " + l.getValor().toString() + ",\n";
		}
		saida += "}";
		
		return saida;
	}
}