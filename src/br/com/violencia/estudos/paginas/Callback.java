package br.com.violencia.estudos.paginas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Callback {
	private String metodo;
	private Object escopo;
	
	public Callback(String metodo, Object escopo) {
		this.metodo = metodo;
		this.escopo = escopo;
	}
	
	public Object invocar(Object... parametros) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException{
		Method metodo = escopo.getClass().getMethod(this.metodo, getParametrosClasses(parametros));
		return metodo.invoke(this.escopo, parametros);
	}
	
	public Class[] getParametrosClasses(Object... parametros) {
		Class[] classes = new Class[parametros.length];
		int i;
		
		for(i=0;i<classes.length;i++) {
			classes[i] = parametros[i].getClass();
		}
		
		return classes;
	}
}
