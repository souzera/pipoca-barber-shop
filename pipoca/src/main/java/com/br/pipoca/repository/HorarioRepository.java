package com.br.pipoca.repository;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    Horario findById(long id);
    @Query(value = "select * from horario where funcionario_id=:id order by data", nativeQuery = true)
    List<Horario> findFuncionario(@Param("id") long id);
}
