package com.br.pipoca.repository;

import com.br.pipoca.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findById(long id);

    @Query(value = "select * from produto where valor=:valor", nativeQuery = true)
    List<Produto> findByValor(@Param("valor") float valor);

    @Query(value = "select * from produto where categoria=:categoria", nativeQuery = true)
    List<Produto> findByCategoria(@Param("categoria") String categoria);

    @Query(value = "select * from produto where marca=:marca", nativeQuery = true)
    List<Produto> findByMarca(@Param("marca") String marca);
}
