package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Motorista extends AbstractEntity {
    @Column
    @NotBlank
    @Size(min = 5)
    private String nome;
    @Column
    @NotBlank
    @Size(min = 5)
    private String cpf;

    public Motorista(@NotBlank @Size(min = 5) String nome, @NotBlank @Size(min = 5) String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Motorista() {
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Motorista [nome=" + nome + ", cpf=" + cpf + "]";
    }

}