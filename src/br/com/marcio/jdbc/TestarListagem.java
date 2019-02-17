package br.com.marcio.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestarListagem {
    public static void main(String[] args) throws SQLException {
        Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco();
        Statement declaracao = conexao.createStatement();
        declaracao.execute("SELECT * FROM PRODUTO");

        ImprimirDados oImprimirDados = new ImprimirDados();
        oImprimirDados.ImprimirTodosProdutos();

        declaracao.close();
        conexao.close();
    }
}
