package br.com.marcio.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestarInsercao {

    public void inserirProdutoMetodoVulneravel() throws SQLException{

        Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco();
        Statement declaracao = conexao.createStatement();
        declaracao.execute("INSERT INTO produto (nome, descricao) values ('Notebook', 'I5')");

        ImprimirDados oImprimirDados = new ImprimirDados();
        oImprimirDados.ImprimirTodosProdutos();

        declaracao.close();
        conexao.close();
    }

    public void inserirProdutoMetodoRobusto() throws SQLException{
        String nome = "NoteBook'";
        String descricao = "I5";

        Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco();
        String sql = "INSERT INTO produto (nome, descricao) values (?, ?)";
        PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql);
        adicionarProduto(nome, descricao, declaracaoPreparada, true);
        conexao.close();
    }

    public void inserirProdutoMetodoRobustoCommit() throws SQLException{

        //quando adiciono o meu create em um try automaticamente o java sabe que tem que fechar esse Connection
        //então não me procupo em add o close, mas tem que tomar cuidados com esses detalhs
        try(Connection conexao = BancoDeDadosUtil.PegarConexaoComBanco()) {
            conexao.setAutoCommit(false);
            String sql = "INSERT INTO produto (nome, descricao) values (?, ?)";

            try (PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql)) {
                adicionarProduto("TV LCD", "32 Polegadas", declaracaoPreparada, false);
                adicionarProduto("Blueray", "Full HDMI", declaracaoPreparada, false);
                adicionarProduto("", "I7", declaracaoPreparada, true);
                conexao.commit();
                declaracaoPreparada.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                conexao.rollback();
            }
            conexao.close();
        }
    }

    private void adicionarProduto(String nome, String descricao, PreparedStatement declaracaoPreparada, Boolean imprimirDados) throws SQLException {
        if (nome == ""){
            throw new IllegalArgumentException("Nome não foi informado!");
        }

        declaracaoPreparada.setString(1,nome);
        declaracaoPreparada.setString(2,descricao);

        declaracaoPreparada.execute();
        if (imprimirDados) {
            ImprimirDados oImprimirDados = new ImprimirDados();
            oImprimirDados.ImprimirTodosProdutos();
        }
    }

    public static void main(String[] args) throws SQLException{

        //insert simples que podem ocorrer erros caso o usuário mande ' aspas ou pode fazer uma Injeção  de SQL e causar danos
        TestarInsercao oTestarInsercaoProdutoVulneravel = new TestarInsercao();
        oTestarInsercaoProdutoVulneravel.inserirProdutoMetodoVulneravel();

        //neste insert não me preocupo com escape ou sql injection
        TestarInsercao oTestarInsercaoProdutoRobusto = new TestarInsercao();
        oTestarInsercaoProdutoRobusto.inserirProdutoMetodoVulneravel();

        TestarInsercao oTestarInsercaoProdutoRobustoCommit = new TestarInsercao();
        oTestarInsercaoProdutoRobustoCommit.inserirProdutoMetodoRobustoCommit();
    }
}
