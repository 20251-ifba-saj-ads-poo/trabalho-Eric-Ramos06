package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.FormatoInvalidoException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Motorista;

import java.util.List;

public class MotoristaService extends Service<Motorista> {

    public MotoristaService() {
        super(Motorista.class);
    }

    public void salvarComValidacao(Motorista motorista)
        throws CampoObrigatorioException, FormatoInvalidoException, EvitarDuplicidadeException {

        validar(motorista);
        create(motorista);
    }

    private void validar(Motorista motorista)
        throws CampoObrigatorioException, FormatoInvalidoException, EvitarDuplicidadeException {

        String nome = motorista.getNome();
        String cpf = motorista.getCpf();

        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("O nome do motorista é obrigatório.");
        }

        if (cpf == null || cpf.trim().isEmpty()) {
            throw new CampoObrigatorioException("O CPF do motorista é obrigatório.");
        }

        if (nome.trim().length() < 3) {
            throw new FormatoInvalidoException("O nome deve ter pelo menos 3 caracteres.");
        }

        if (!cpf.matches("\\d{11}")) {
            throw new FormatoInvalidoException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        List<Motorista> motoristas = findAll();
        boolean duplicado = motoristas.stream()
            .anyMatch(m -> m.getCpf().equalsIgnoreCase(cpf.trim()));

        if (duplicado) {
            throw new EvitarDuplicidadeException("Já existe um motorista com esse CPF cadastrado.");
        }
    }
}
