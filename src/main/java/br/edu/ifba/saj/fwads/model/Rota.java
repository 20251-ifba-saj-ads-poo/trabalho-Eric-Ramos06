package br.edu.ifba.saj.fwads.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Rota extends AbstractModel<UUID> {

    private String nome;
    private Ponto pontoInitial;
    private Ponto pontoFinal;
    private List<Ponto> paradas;

    public Rota(String nome, Ponto pontoInitial, Ponto pontoFinal, List<Ponto> paradas) {
        this.nome = nome;
        this.pontoInitial = pontoInitial;
        this.pontoFinal = pontoFinal;
        this.paradas = new ArrayList<>(paradas);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Ponto getPontoInitial() {
        return pontoInitial;
    }
    public void setPontoInitial(Ponto pontoInitial) {
        this.pontoInitial = pontoInitial;
    }
    public Ponto getPontoFinal() {
        return pontoFinal;
    }
    public void setPontoFinal(Ponto pontoFinal) {
        this.pontoFinal = pontoFinal;
    }
    public ArrayList<Ponto> getParadas() {
        return (ArrayList<Ponto>) paradas;
    }
    public void setParadas(ArrayList<Ponto> paradas) {
        this.paradas = paradas;
    }
    
}
