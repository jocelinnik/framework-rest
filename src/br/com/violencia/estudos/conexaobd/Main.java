package br.com.violencia.estudos.conexaobd;

import br.com.violencia.estudos.conexaobd.ConexaoMySql;

public class Main {
	public static void main(String[] args) {
		ConexaoMySql conexao = new ConexaoMySql();
        
		conexao.getConexaoMySQL();
        conexao.fecharConexao();
	}
}