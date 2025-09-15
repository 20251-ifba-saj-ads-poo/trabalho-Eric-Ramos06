package br.edu.ifba.saj.fwads.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Ponto;
import br.edu.ifba.saj.fwads.model.Rota;
import jakarta.validation.ValidationException;

public class RotaService extends Service<Rota>{

    public List<Rota> buscarTodosRotas() {
    return new ArrayList<>(super.findAll());
}

    public Rota validarDuplicidade(String nome) throws EvitarDuplicidadeException {
       if(!findByMap(Map.of("nome", nome)).isEmpty()){
            throw new EvitarDuplicidadeException("Já existe uma rota cadastrada com este nome.");
        }
        return null;
    }

    public RotaService() {
        super(Rota.class);
    }

    public String howManyOpenRotas(){
        List<Rota> allOpenRotas = findAll();
        int count = 0;

        for(Rota rota : allOpenRotas){
            count++;
        }

        return String.valueOf(count);

    }

    public Rota create(String nome, Ponto pontoInicial, Ponto pontoFinal) throws  ValidationException{
        List<String> errors = new ArrayList<>();

        if(nome == null || nome.trim().isEmpty()){
            errors.add("Informe um nome para a rota.");
        }

        if(pontoInicial == null){
            errors.add("Selecione um ponto inicial.");
        }

        if(pontoFinal == null){
            errors.add("Selecione um ponto final.");
        }
        

        Rota novaRota = new Rota(nome, pontoInicial, pontoFinal);
        return create(novaRota);
    }
    


}
