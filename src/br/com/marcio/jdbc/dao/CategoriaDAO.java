package br.com.marcio.jdbc.dao;

import br.com.marcio.jdbc.modelo.Categoria;
import br.com.marcio.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final Connection conexao;

    public CategoriaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Categoria> lista() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();
        String sql = "Select * from categoria";

        try (PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql)) {
            declaracaoPreparada.execute();
            try (ResultSet conjuntoDeDados = declaracaoPreparada.getResultSet()){
                while (conjuntoDeDados.next()) {
                   int id = conjuntoDeDados.getInt("idCategoria");
                   String nome = conjuntoDeDados.getString("nome");
                   Categoria categoria = new Categoria(id, nome);
                   categorias.add(categoria);
                }
            }
        }
        return categorias;
    }

    public List<Categoria> listaComProdutos() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        Categoria ultima = null;

        String sql = "Select c.idCategoria, c.nome as nomeCategoria,                             "+
                     "       p.idProduto, p.nome as nomeProduto, p.descricao as descricaoProduto " +
                     "  from  categoria c                                                        " +
                     "  join produto p on (p.id_categoria = c.idcategoria)                       ";

        try (PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql)) {
            declaracaoPreparada.execute();
            try (ResultSet conjuntoDeDados = declaracaoPreparada.getResultSet()){
                while (conjuntoDeDados.next()) {
                    int idCategoria = conjuntoDeDados.getInt("idCategoria");
                    String nomeCategoria = conjuntoDeDados.getString("nomeCategoria");

                    if (ultima== null || !ultima.getNome().equals(nomeCategoria)) {
                        Categoria categoria = new Categoria(idCategoria, nomeCategoria);
                        categorias.add(categoria);
                        ultima = categoria;
                    }

                    String nomeDoProduto = conjuntoDeDados.getString("nomeProduto");
                    String descricaoDoProduto = conjuntoDeDados.getString("descricaoProduto");
                    int idDoProduto = conjuntoDeDados.getInt("idProduto");
                    Produto produto = new Produto(nomeDoProduto, descricaoDoProduto);
                    produto.setIdProduto(idDoProduto);
                    ultima.adicionar(produto);
                }
            }
        }
        return categorias;
    }
}
