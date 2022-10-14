package com.br.pipoca.repository;

import com.br.pipoca.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    Venda findById(long id);
    @Query(value = "select * from venda where atendimento_id is not null", nativeQuery = true)
    List<Venda> vendaAgenda();
    @Query(value = "select * from venda where produto_id is not null", nativeQuery = true)
    List<Venda> vendaProdutos();
    @Query(value = "select * from venda where date=:date", nativeQuery = true)
    List<Venda> vendaDate(@Param("date") Date date);
    @Query(value ="select * from venda where date=:date and atendimento_id is not null",nativeQuery = true)
    List<Venda> vendaAgendaDate(@Param("date") Date date);
    @Query(value ="select * from venda where date=:date and produto_id is not null",nativeQuery = true)
    List<Venda> vendaProdutoDate(@Param("date") Date date);

}
