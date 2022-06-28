package com.br.pipoca.repository;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.util.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findById(long id);
    Funcionario findByUsuario(Usuario usuario);

    @Query(value = "select * from funcionario where cargo=:cargo", nativeQuery = true)
    List<Funcionario> findByCargo(@Param("cargo") int cargo);
}
