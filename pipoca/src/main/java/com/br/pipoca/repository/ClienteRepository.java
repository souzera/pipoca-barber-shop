package com.br.pipoca.repository;


import com.br.pipoca.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findById(long id);

}
