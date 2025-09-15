package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Linha;

import java.util.List;

public class LinhaService extends Service<Linha> {

    public LinhaService() {
        super(Linha.class);
    }

    public void salvarComValidacao(Linha linha)
        throws CampoObrigatorioException, EvitarDuplicidadeException {

        validar(linha);
        create(linha);
    }

    private void validar(Linha linha)
        throws CampoObrigatorioException, EvitarDuplicidadeException {

        String nome = linha.getNome();

        // ✅ Nome obrigatório
        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("O nome da linha é obrigatório.");
        }

        // ✅ Tamanho mínimo
        if (nome.trim().length() < 3) {
            throw new CampoObrigatorioException("O nome da linha deve ter pelo menos 3 caracteres.");
        }

        // ✅ Motorista obrigatório
        if (linha.getMotorista() == null) {
            throw new CampoObrigatorioException("O motorista da linha é obrigatório.");
        }

        // ✅ Ônibus obrigatório
        if (linha.getOnibus() == null) {
            throw new CampoObrigatorioException("O ônibus da linha é obrigatório.");
        }

        // ✅ Itinerário obrigatório
        if (linha.getItinerario() == null) {
            throw new CampoObrigatorioException("O itinerário da linha é obrigatório.");
        }

        // ✅ Evitar duplicidade de nome
        List<Linha> linhas = findAll();
        boolean duplicado = linhas.stream()
            .anyMatch(l -> l.getNome().equalsIgnoreCase(nome.trim()));

        if (duplicado) {
            throw new EvitarDuplicidadeException("Já existe uma linha com esse nome cadastrada.");
        }
    }
}
