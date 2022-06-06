package com.br.pipoca.repository;

import com.br.pipoca.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    Venda findById(long id);

}
