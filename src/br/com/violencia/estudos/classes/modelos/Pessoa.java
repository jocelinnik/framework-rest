package br.com.violencia.estudos.classes.modelos;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.violencia.estudos.conexaobd.ConexaoMySql;
import org.json.JSONObject;
import org.json.JSONArray;

public class Pessoa {
	public static ConexaoMySql conexao = new ConexaoMySql();
	
	public JSONArray getPessoas() {
		String query = "SELECT * FROM tabela";
		JSONArray res = new JSONArray();
		
		try {
			Statement stmt = conexao.getConexaoMySQL().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				JSONObject novo = new JSONObject();
				novo.put("id", rs.getInt("id"));
				novo.put("nome", rs.getString("nome"));
				res.put(novo);
			}
			
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			conexao.fecharConexao();
		}catch(SQLException e) {
			System.out.println("Erro na consulta SQL");
			System.err.println(e);
		}
		
		return res;
	}
	
	public JSONObject getPessoa(int id) {
		String query = "SELECT * FROM tabela WHERE id = '" + id + "'";
		JSONObject res = new JSONObject();
		
		try {
			Statement stmt = conexao.getConexaoMySQL().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs!=null) {
				res.put("id", rs.getInt("id"));
				res.put("nome", rs.getString("nome"));
			}
			
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			conexao.fecharConexao();
		}catch(SQLException e) {
			System.out.println("Erro na consulta SQL");
			System.err.println(e);
		}
		
		return res;
	}
	
	public int addPessoa(int id, String nome) {
		String query = "INSERT INTO tabela (id, nome) VALUES ('" + id + "', '" + nome + "')";
		int res = 0;
		
		try {
			Statement stmt = conexao.getConexaoMySQL().createStatement();
			int rs = stmt.executeUpdate(query);
			
			if(rs==1) {
				res = 200;
			}
			
			try {
				if(stmt!=null) stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			conexao.fecharConexao();
		}catch(SQLException e) {
			System.out.println("Erro na consulta SQL");
			System.err.println(e);
			res = 412;
		}
		
		return res;
	}
}