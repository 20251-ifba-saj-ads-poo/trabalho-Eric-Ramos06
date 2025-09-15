package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Rota;

import java.util.List;

public class RotaService extends Service<Rota> {

    public RotaService() {
        super(Rota.class);
    }

    public void salvarComValidacao(Rota rota)
        throws CampoObrigatorioException, EvitarDuplicidadeException {

        validar(rota);
        create(rota);
    }

    private void validar(Rota rota)
        throws CampoObrigatorioException, EvitarDuplicidadeException {

        String nome = rota.getNome();

        // ✅ Nome obrigatório
        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("O nome da rota é obrigatório.");
        }

        // ✅ Tamanho mínimo
        if (nome.trim().length() < 3) {
            throw new CampoObrigatorioException("O nome da rota deve ter pelo menos 3 caracteres.");
        }

        // ✅ Ponto inicial e final obrigatórios
        if (rota.getPontoInicial() == null) {
            throw new CampoObrigatorioException("O ponto inicial da rota é obrigatório.");
        }

        if (rota.getPontoFinal() == null) {
            throw new CampoObrigatorioException("O ponto final da rota é obrigatório.");
        }

        // ✅ Evitar duplicidade de nome
        List<Rota> rotas = findAll();
        boolean duplicado = rotas.stream()
            .anyMatch(r -> r.getNome().equalsIgnoreCase(nome.trim()));

        if (duplicado) {
            throw new EvitarDuplicidadeException("Já existe uma rota com esse nome cadastrada.");
        }
    }
}
