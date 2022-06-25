package com.br.pipoca.repository;

import com.br.pipoca.entity.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    Atendimento findById(long id);
    @Query(value = "select * from atendimento where cliente_id=:cliente_id", nativeQuery = true)
    List<Atendimento> findByCliente(@Param("cliente_id") long cliente_id);
    @Query(value = "select * from atendimento where servico_id=:servico_id", nativeQuery = true)
    List<Atendimento> findByServico(@Param("servico_id") long servico_id);
    @Query(value = "select * from atendimento where horario_id=:horario_id", nativeQuery = true)
    List<Atendimento> findByHorario(@Param("horario_id") long horario_id);
    @Query(value = "select * from atendimento where dia=:dia", nativeQuery = true)
    List<Atendimento> findByData(@Param("dia")Date date);
}
