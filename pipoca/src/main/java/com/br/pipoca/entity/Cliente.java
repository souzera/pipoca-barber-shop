package com.br.pipoca.entity;

import com.br.pipoca.model.Sexo;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String telefone;
    private Sexo sexo;
    private Date dtNascimento;
}
