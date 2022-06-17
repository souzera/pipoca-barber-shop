package com.br.pipoca.repository;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findById(long id);
}
