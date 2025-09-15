package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.model.Visitante;

public class VisitanteService extends Service<Visitante> {

    public VisitanteService() {
        super(Visitante.class);
    }

    public Visitante criarVisitante() {
        return new Visitante();
    }
}