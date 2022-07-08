package com.br.pipoca.repository;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.util.Hora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {


    Horario findById(long id);
    @Query(value = "select * from horario where funcionario_id=:id", nativeQuery = true)
    List<Horario> findFuncionario(@Param("id") long id);
    @Query(value = "select * from horario where status=:status", nativeQuery = true)
    List<Horario> findStatus(@Param("status") int status);
    @Query(value = "select * from horario where hora=:hora", nativeQuery = true)
    List<Horario> findByHora(int hora);
    @Query(value = "select * from horario where funcionario_id=:funcionario_id and hora=:hora", nativeQuery = true)
    Horario findByHoraAndFuncionario(@Param("funcionario_id")long funcionario_id, @Param("hora") Hora hora);
    @Query(value = "select * from horario where date=:date", nativeQuery = true)
    List<Horario> findByData(@Param("date") Date date);
    @Query(value = "select * from horario where date=:date and hora=:hora", nativeQuery = true)
    List<Horario> findByDataAndHora(@Param("date") Date date, @Param("hora") Hora hora);
    @Query(value = "select * from horario where funcionario_id=:funcionario_id and hora=:hora and date=:date", nativeQuery = true)
    Horario findByHoraAndFuncionarioAndDia(@Param("funcionario_id")long funcionario_id, @Param("hora") int hora, @Param("date") Date date);
    @Query(value = "select * from horario where date=:date and funcionario_id=:funcionario_id", nativeQuery = true)
    List<Horario> findByFuncionarioAndDia(@Param("date") Date date, @Param("funcionario_id") long funcionario_id);
}
