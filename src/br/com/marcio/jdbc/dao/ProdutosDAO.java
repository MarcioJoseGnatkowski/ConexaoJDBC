package br.com.marcio.jdbc.dao;

import br.com.marcio.jdbc.modelo.Categoria;
import br.com.marcio.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {
    //variavel membro
    private final Connection conexao;

    public ProdutosDAO(Connection conexao) {
        this.conexao = conexao;
    }

    private void tranformarResultadoEmProdutos(List<Produto> produtos, PreparedStatement declaracaoPreparada) throws SQLException {
        try (ResultSet conjuntoDeDados = declaracaoPreparada.getResultSet()) {
            while (conjuntoDeDados.next()) {
                int idProduto = conjuntoDeDados.getInt("idProduto");
                String nome = conjuntoDeDados.getString("nome");
                String descricao = conjuntoDeDados.getString("descricao");
                Produto produto = new Produto(nome, descricao);
                produto.setIdProduto(idProduto);
                produtos.add(produto);
            }
        }
    }

    public void salvar(Produto produto) throws SQLException {
        conexao.setAutoCommit(false);
        String sql = "Insert into Produto (nome, descricao) values (?, ?)";
        try (PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql)) {
            declaracaoPreparada.setString(1, produto.getNome());
            declaracaoPreparada.setString(2, produto.getDescricao());
            declaracaoPreparada.execute();
            conexao.commit();
            declaracaoPreparada.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            conexao.rollback();
        }
    }

    public List<Produto> lista () throws SQLException {

        //lista para retornar todos os produtos
        List<Produto> produtos = new ArrayList<>();
        String sql = "select * from produto";

        try (PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql)){
            declaracaoPreparada.execute();

            tranformarResultadoEmProdutos(produtos, declaracaoPreparada);
        }
        return produtos;
    }

    public List<Produto> busca(Categoria categoria) throws SQLException{
        List<Produto> produtos = new ArrayList<>();
        String sql = "select * from produto where id_Categoria = ?";

        try (PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql)){
            declaracaoPreparada.setInt(1, categoria.getId());
            declaracaoPreparada.execute();

            tranformarResultadoEmProdutos(produtos, declaracaoPreparada);
        }
        return produtos;
    }

}
