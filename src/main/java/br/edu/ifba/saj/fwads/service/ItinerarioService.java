package br.edu.ifba.saj.fwads.service;

import java.util.Map;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Itinerario;

public class ItinerarioService extends Service<Itinerario> {

    public ItinerarioService() {
        super(Itinerario.class);
    }

    public Itinerario validarDuplicidade(String nome) throws EvitarDuplicidadeException {
       if(!findByMap(Map.of("nome", nome)).isEmpty()){
            throw new EvitarDuplicidadeException("Já existe um itinerário cadastrado com este nome.");
        }
        return null;
    }
}