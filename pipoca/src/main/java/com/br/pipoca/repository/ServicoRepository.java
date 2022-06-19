package com.br.pipoca.repository;

import com.br.pipoca.entity.Produto;
import com.br.pipoca.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Servico findById(long id);

    @Query(value = "select * from servico where valor=:valor", nativeQuery = true)
    List<Servico> findByValor(@Param("valor") float valor);
}
