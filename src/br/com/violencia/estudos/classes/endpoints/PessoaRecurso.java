package br.com.violencia.estudos.classes.endpoints;

import br.com.violencia.estudos.classes.modelos.Pessoa;

public class PessoaRecurso {
	public static Pessoa p = new Pessoa();
	
	public String getPessoas(){
		return p.getPessoas().toString();
	}
	
	public int addPessoas(int id, String nome) {
		return p.addPessoa(id, nome);
	}
}