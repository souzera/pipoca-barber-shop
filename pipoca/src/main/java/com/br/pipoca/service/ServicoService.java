package com.br.pipoca.service;

import com.br.pipoca.entity.Servico;
import com.br.pipoca.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico saveServico(Servico servico){
        return servicoRepository.save(servico);
    }

    public List<Servico> servicos() throws IOException {
        Iterable<Servico> servicoIterable = this.servicoRepository.findAll();
        return Streamable.of(servicoIterable).toList();
    }
}
