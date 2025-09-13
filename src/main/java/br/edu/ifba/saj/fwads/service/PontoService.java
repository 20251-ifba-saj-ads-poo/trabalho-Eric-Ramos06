package br.edu.ifba.saj.fwads.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Ponto;

public class PontoService extends Service<Ponto> {

    public PontoService() {
        super(Ponto.class);
    }

    public Ponto validarDuplicidade(String endereco) throws EvitarDuplicidadeException {
       if(!findByMap(Map.of("endereco", endereco)).isEmpty()){
            throw new EvitarDuplicidadeException("Já existe um ponto cadastrado com este endereço.");
        }
        return null;
    }

    public List<Ponto> bucasTodos() {
    return new ArrayList<>(super.findAll());
}
}