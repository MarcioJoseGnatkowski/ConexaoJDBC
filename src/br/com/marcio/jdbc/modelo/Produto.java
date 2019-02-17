package br.com.marcio.jdbc.modelo;

public class Produto {

    private int idProduto;
    private String nome;
    private String descricao;

    public Produto(String nome, String descricao) {
        super();
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        return String.format("[Produto: %d, %s, %s]", idProduto, nome, descricao);
    }
}
