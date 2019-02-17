package br.com.marcio.jdbc;

import br.com.marcio.jdbc.modelo.Produto;
import br.com.marcio.jdbc.dao.ProdutosDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestarDAODeProduto {
    public static void main(String[] args) throws SQLException {

        Produto mesa = new Produto("Mesa","Mesa 4 pés");

        try (Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco()){
           ProdutosDAO dao = new ProdutosDAO(conexao);
           dao.salvar(mesa);

            List<Produto> produtos = dao.lista();
            for (Produto produto : produtos) {
                System.out.println("Existe o produto: " + produto);
            }
        }
    }
}
