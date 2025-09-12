package br.edu.ifba.saj.fwads.service;

import java.util.Map;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Onibus;

public class OnibusService extends Service<Onibus> {

    public OnibusService() {
        super(Onibus.class);
    }

    public Onibus validarDuplicidade(String placa) throws EvitarDuplicidadeException {
       if(!findByMap(Map.of("placa", placa)).isEmpty()){
            throw new EvitarDuplicidadeException("Já existe um ônibus cadastrado com esta placa.");
        }
        return null;
    }
}