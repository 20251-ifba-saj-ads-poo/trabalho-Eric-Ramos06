package br.edu.ifba.saj.fwads.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Rota extends AbstractEntity {
    @Column
    @NotBlank
    @Size(min = 5)
    private String nome;

    @Column
    @NotBlank
    @Size(min = 5)
    private Ponto pontoInicial;

    @Column
    @NotBlank
    @Size(min = 5)
    private Ponto pontoFinal;

    @Column
    @NotBlank
    @Size(min = 5)
    private List<Ponto> pontos;

    public Rota(@NotBlank @Size(min = 5) String nome, @NotBlank @Size(min = 5) Ponto pontoInicial,
            @NotBlank @Size(min = 5) Ponto pontoFinal, List<Ponto> pontos) {
        this.nome = nome;
        this.pontoInicial = pontoInicial;
        this.pontoFinal = pontoFinal;
        this.pontos = pontos;
    }

    public Rota() {
    }

    public List<Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(List<Ponto> pontos) {
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public @NotBlank @Size(min = 5) Ponto getPontoInicial() {
        return pontoInicial;
    }

    public void setPontoInicial(@NotBlank @Size(min = 5) Ponto pontoInicial) {
        this.pontoInicial = pontoInicial;
    }

    public @NotBlank @Size(min = 5) Ponto getPontoFinal() {
        return pontoFinal;
    }

    public void setPontoFinal(@NotBlank @Size(min = 5) Ponto pontoFinal) {
        this.pontoFinal = pontoFinal;
    }

    @Override
    public String toString() {
        return "Rota [nome=" + nome + ", pontoInicial=" + pontoInicial + ", pontoFinal=" + pontoFinal + ", pontos="
                + pontos + "]";
    }

}