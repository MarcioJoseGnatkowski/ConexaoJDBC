package br.com.marcio.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestarConexao {
    public static void main(String[] args ) throws SQLException {
        Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco();
        System.out.println("Conexão o banco de dados efetuada com sucesso!");
        conexao.close();

    }
}
