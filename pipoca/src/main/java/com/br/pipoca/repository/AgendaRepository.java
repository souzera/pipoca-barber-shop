package com.br.pipoca.repository;

import com.br.pipoca.entity.Agenda;
import com.br.pipoca.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    Agenda findById(long id);
    @Query(value = "select * from agenda where cliente_id=:cliente_id", nativeQuery = true)
    List<Agenda> findByCliente(@Param("cliente_id") long cliente_id);
    @Query(value = "select * from agenda where servico_id=:servico_id", nativeQuery = true)
    List<Agenda> findByServico(@Param("servico_id") long servico_id);
    @Query(value = "select * from agenda where horario_id=:horario_id", nativeQuery = true)
    List<Agenda> findByHorario(@Param("horario_id") long horario_id);
}
