package br.com.violencia.estudos.json;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		JSONObject objeto = new JSONObject();
		
		objeto.put("titulo", "JSON x XML: A batalha final");
		objeto.put("ano", 2012);
		objeto.put("genero", "acao");
		
		String str = objeto.toString();
		
		System.out.println(str);
	}
}