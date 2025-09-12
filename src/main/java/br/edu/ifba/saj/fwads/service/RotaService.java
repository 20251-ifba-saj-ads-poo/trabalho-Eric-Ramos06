package br.edu.ifba.saj.fwads.service;

import java.util.Map;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Rota;

public class RotaService extends Service<Rota> {

    public RotaService() {
        super(Rota.class);
    }

    public Rota validarDuplicidade(String nome) throws EvitarDuplicidadeException {
       if(!findByMap(Map.of("nome", nome)).isEmpty()){
            throw new EvitarDuplicidadeException("Já existe uma rota cadastrada com este nome.");
        }
        return null;
    }
}