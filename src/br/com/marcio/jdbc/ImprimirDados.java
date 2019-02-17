package br.com.marcio.jdbc;

/*
  printf é como se fosse o format do delphi
  \n é o enter
  %20s o número define o tamanho da string preenchendo com espaços a esquerda
  ...printf("%d: %20s - %s\n",idProduto, nome, descricao);
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImprimirDados {
    public void ImprimirTodosProdutos() throws SQLException {
        Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco();
        Statement declaracao = conexao.createStatement();
        declaracao.execute("select * from produto");
        ResultSet conjuntoDeResultados = declaracao.getResultSet();

        while (conjuntoDeResultados.next()){
            int idProduto = conjuntoDeResultados.getInt("idProduto");
            String nome = conjuntoDeResultados.getString("nome");
            String descricao = conjuntoDeResultados.getString("descricao");
            System.out.printf("%3d: %s - %s\n",idProduto, nome, descricao);
        }
        conjuntoDeResultados.close();
        declaracao.close();
        conexao.close();
    }
}
