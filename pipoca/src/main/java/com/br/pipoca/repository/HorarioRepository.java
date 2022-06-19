package com.br.pipoca.repository;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Horario;
import com.br.pipoca.model.StatusHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    Horario findById(long id);
    @Query(value = "select * from horario where funcionario_id=:id order by data asc", nativeQuery = true)
    List<Horario> findFuncionario(@Param("id") long id);

    @Query(value = "select * from horario where data=:data", nativeQuery = true)
    List<Horario> findHora(@Param("data") Date data);

    @Query(value = "select * from horario where status=:status", nativeQuery = true)
    List<Horario> findStatus(@Param("status") StatusHorario status);
}
