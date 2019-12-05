package br.com.violencia.estudos.servidor;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import br.com.violencia.estudos.servidor.RequisicaoHTTP;
import br.com.violencia.estudos.servidor.RespostaHTTP;

public class ThreadConexao implements Runnable{
	private final Socket cliente;
	private boolean conectado;
	
	public ThreadConexao(Socket cliente) {
		this.cliente = cliente;
	}
	
	public String pegarJSON() {
		JSONObject objeto = new JSONObject();
		String str;
		objeto.put("titulo", "JSON x XML: A batalha final");
		objeto.put("ano", 2012);
		objeto.put("genero", "acao");
		str = objeto.toString();
		 
		return str;
	}
	
	@Override
	public void run() {
		conectado = true;
		System.out.println("Requisicao HTTP");
		System.out.println("Endereco do cliente: " + cliente.getInetAddress());
		while(conectado) {
			try {
				RequisicaoHTTP requisicao = RequisicaoHTTP.lerRequisicao(cliente.getInputStream());
				
				if(requisicao.isManterViva()) {
					cliente.setKeepAlive(true);
					cliente.setSoTimeout((int)requisicao.getTempoLimite());
				}else {
					cliente.setSoTimeout(300);
				}
				
				System.out.println(requisicao.getRecurso());
				String recurso;
				if(requisicao.getRecurso().equals("/")) {
					recurso = this.pegarJSON();
				}else {
					recurso = null;
				}
				
				RespostaHTTP resposta;
				if(recurso!=null) {
					resposta = new RespostaHTTP(requisicao.getProtocolo(), 200, "OK");
				}else {
					resposta = new RespostaHTTP(requisicao.getProtocolo(), 404, "Not Found");
				}
				
				//resposta.setConteudo(Files.readAllBytes(arquivo.toPath()));
				resposta.setConteudo(recurso.getBytes());
				String dataFormatada = new Date().toString();
				resposta.setCabecalhos("Location", "https://localhost:8000/");
				resposta.setCabecalhos("Date", dataFormatada);
				resposta.setCabecalhos("Server", "MeuServidor/1.0");
				resposta.setCabecalhos("Content-Type", "application/json");
				resposta.setCabecalhos("Content-Length", resposta.getTamanhoResposta());
				resposta.setSaida(cliente.getOutputStream());
				resposta.envia();
			}catch(IOException e) {
				if(e instanceof SocketTimeoutException) {
					try {
						conectado = false;
						cliente.close();
					}catch(IOException ex) {
						Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}
	}
}
