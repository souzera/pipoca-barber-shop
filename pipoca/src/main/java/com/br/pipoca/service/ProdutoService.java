package com.br.pipoca.service;

import com.br.pipoca.entity.Produto;
import com.br.pipoca.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto saveProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> produtos() throws IOException {
        Iterable<Produto> produtoIterable = this.produtoRepository.findAll();
        return Streamable.of(produtoIterable).toList();
    }

    public Produto findById(long id) {
        return produtoRepository.findById(id);
    }

    public void deleteById(long id) {
        produtoRepository.deleteById(id);
    }
}
