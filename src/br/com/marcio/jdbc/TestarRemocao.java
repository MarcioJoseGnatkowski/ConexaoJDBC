package br.com.marcio.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestarRemocao {
    public static void main(String[] args) throws SQLException {
        Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco();

        Statement declaracao = conexao.createStatement();
        declaracao.execute("delete from produto where idProduto > 3");
        int nNumeroRegAfetados = declaracao.getUpdateCount();
        System.out.println(nNumeroRegAfetados + " registros afetados!");
    }
}
