package br.com.marcio.jdbc;

import br.com.marcio.jdbc.dao.CategoriaDAO;
import br.com.marcio.jdbc.dao.ProdutosDAO;
import br.com.marcio.jdbc.modelo.Categoria;
import br.com.marcio.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestarCategoria {

    public static void main(String[] args ) throws SQLException {

        try (Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco()){
            List<Categoria> categorias = new CategoriaDAO(conexao).listaComProdutos();

            for (Categoria categoria : categorias) {
                System.out.println(categoria.getNome());

                for (Produto produto : categoria.getProdutos()) {
                    System.out.println("        " + produto.getNome() + " Descr. " + produto.getDescricao());
                }

                //carrega todos os produtoss
                //for (Produto produto : new ProdutosDAO(conexao).busca(categoria)) {
                //    System.out.println(categoria.getNome() + " - " + produto.getNome());
                //}
            }
        }

    }
}
