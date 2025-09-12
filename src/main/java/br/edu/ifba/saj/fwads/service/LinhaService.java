package br.edu.ifba.saj.fwads.service;

import java.util.Map;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Linha;

public class LinhaService extends Service<Linha> {

    public LinhaService() {
        super(Linha.class);
    }

    public Linha validarDuplicidade(String nome) throws EvitarDuplicidadeException {
       if(!findByMap(Map.of("nome", nome)).isEmpty()){
            throw new EvitarDuplicidadeException("Já existe uma linha cadastrada com este nome.");
        }
        return null;
    }
}