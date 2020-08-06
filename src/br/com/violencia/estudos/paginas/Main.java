package br.com.violencia.estudos.paginas;

import java.lang.reflect.InvocationTargetException;
import br.com.violencia.estudos.paginas.Callback;
import br.com.violencia.estudos.classes.endpoints.PessoaRecurso;

public class Main{
	public static PessoaRecurso pessoa = new PessoaRecurso();
	
	public static void getPessoas() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
		Callback callback = new Callback("getPessoas", pessoa);
		long tempo, tempo2;
		
		tempo = System.currentTimeMillis();
		String res = (String)callback.invocar();
		tempo2 = System.currentTimeMillis();
		
		System.out.println(res);
		System.out.println("Tempo de resposta da requisicao: " + (tempo2-tempo) + " ms.");
		
		callback = null;
	}
	
	public static void addPessoa(int id, String nome) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Callback callback = new Callback("addPessoa", pessoa);
		long tempo, tempo2;
		
		tempo = System.currentTimeMillis();
		int res = (int)callback.invocar(id, nome);
		tempo2 = System.currentTimeMillis();
		
		System.out.println("Status " + res);
		System.out.println("Tempo de resposta da requisicao: " + (tempo2-tempo) + " ms.");
		
		callback = null;
	}
	
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		//getPessoas();
		addPessoa(4, "Programador violento");
	}
}