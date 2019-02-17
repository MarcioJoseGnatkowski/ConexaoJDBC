package br.com.marcio.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private final int id;
    private final String nome;

    private  final List<Produto> produtos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria(int id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public void adicionar(Produto produto){
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

}
