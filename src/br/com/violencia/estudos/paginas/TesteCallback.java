package br.com.violencia.estudos.paginas;

import java.lang.reflect.InvocationTargetException;
import br.com.violencia.estudos.paginas.Callback;

public class TesteCallback {
	public void testeCallback() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
		TestClass teste = new TestClass();
		Callback callback = new Callback("hello", teste);
		callback.invocar();
		callback.invocar("Fred");
	}
	
	public class TestClass{
		public void hello() {
			System.out.println("Hello World");
		}
		
		public void hello(String nome) {
			System.out.println("Hello " + nome);
		}
	}
}