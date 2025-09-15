package br.edu.ifba.saj.fwads.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Motorista;

public class MotoristaService extends Service<Motorista> {

    public List<Motorista> buscarTodosMotoristas() {
    return new ArrayList<>(super.findAll());
}

    public MotoristaService() {
        super(Motorista.class);
    }

    public Motorista validarDuplicidade(String cpf) throws EvitarDuplicidadeException {
       if(!findByMap(Map.of("cpf", cpf)).isEmpty()){
            throw new EvitarDuplicidadeException("Já existe uma conta vinculada à este CPF.");
        }
        return null;
    }
}