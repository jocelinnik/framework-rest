package br.com.violencia.estudos.conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql{
    public static String status = "Nao conectou...";

    public ConexaoMySql(){

    }

    public Connection getConexaoMySQL(){
        String driveName, host, port, bd, url, user, password;
        Connection conexao = null;

        try{
            driveName = "com.mysql.jdbc.Driver";
            Class.forName(driveName);

            host = "localhost";
            port = "3306";
            bd = "teste-java";
            url = "jdbc:mysql://" + host + ":" + port + "/" + bd;
            user = "root";
            password = "";

            conexao = DriverManager.getConnection(url, user, password);

            if(conexao!=null){
                status = "STATUS--->Conectado com sucesso!";
            }else{
                status = "STATUS--->Nao foi possivel realizar a conexao!";
            }

            return conexao;
        }catch(ClassNotFoundException e){
            System.out.println("O driver especificado nao foi encontrado");

            return null;
        }catch(SQLException e){
            System.out.println("Falha na conexao com o Banco de Dados!");

            return null;
        }
    }

    public String getStatus(){
        return status;
    }

    public boolean fecharConexao(){
        try{
            this.getConexaoMySQL().close();

            return true;
        }catch(SQLException e){
            return false;
        }
    }

    public Connection reiniciarConexao(){
        this.fecharConexao();

        return this.getConexaoMySQL();
    }
}