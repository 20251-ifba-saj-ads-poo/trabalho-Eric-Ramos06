package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Ponto;

import java.util.List;

public class PontoService extends Service<Ponto> {

    public PontoService() {
        super(Ponto.class);
    }

    public void salvarComValidacao(Ponto ponto) throws CampoObrigatorioException, EvitarDuplicidadeException {
        validar(ponto);
        create(ponto);
    }

    private void validar(Ponto ponto) throws CampoObrigatorioException, EvitarDuplicidadeException {
        String endereco = ponto.getEndereco();

        if (endereco == null || endereco.trim().isEmpty()) {
            throw new CampoObrigatorioException("O campo endereço é obrigatório.");
        }

        if (endereco.trim().length() < 5) {
            throw new CampoObrigatorioException("O endereço deve ter pelo menos 5 caracteres.");
        }

        List<Ponto> pontosExistentes = findAll();
        boolean duplicado = pontosExistentes.stream()
            .anyMatch(p -> p.getEndereco().equalsIgnoreCase(endereco.trim()));

        if (duplicado) {
            throw new EvitarDuplicidadeException("Já existe um ponto com esse endereço cadastrado.");
        }
    }
}
