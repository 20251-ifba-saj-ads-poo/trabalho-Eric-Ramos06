package br.edu.ifba.saj.fwads.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Itinerario;

public class ItinerarioService extends Service<Itinerario> {

    public List<Itinerario> buscarTodosItinerarios() {
        return new ArrayList<>(super.findAll());
    }

    public ItinerarioService() {
        super(Itinerario.class);
    }

    public Itinerario validarDuplicidade(String nome) throws EvitarDuplicidadeException {
        if (!findByMap(Map.of("nome", nome)).isEmpty()) {
            throw new EvitarDuplicidadeException("Já existe um itinerário cadastrado com este nome.");
        }
        return null;
    }
}