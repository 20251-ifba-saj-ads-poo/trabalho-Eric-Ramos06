    package br.edu.ifba.saj.fwads.controller;

    import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
    import br.edu.ifba.saj.fwads.exception.FormatoInvalidoException;
    import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
    import br.edu.ifba.saj.fwads.model.Motorista;
    import br.edu.ifba.saj.fwads.service.MotoristaService;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.Alert;
    import javafx.scene.control.TextField;
    import javafx.scene.control.Alert.AlertType;

    public class CadMotoristaController {
        @FXML
        private TextField txNome;
        @FXML
        private TextField txCPF;

        private MasterController masterController;
        private ListMotoristaController listMotoristaController;
        private MotoristaService serviceMotorista = new MotoristaService();


        public void setListMotoristaController(ListMotoristaController listMotoristaController) {
            this.listMotoristaController = listMotoristaController;
        }

        @FXML
        private void salvarMotorista(ActionEvent event) {
            String nome = txNome.getText().trim();
            String cpf = txCPF.getText().trim();

            Motorista novoMotorista = new Motorista(nome, cpf);

            try {
                serviceMotorista.salvarComValidacao(novoMotorista);
                new Alert(AlertType.INFORMATION,
                    "Motorista: " + novoMotorista.getNome() + " cadastrado com sucesso").showAndWait();
                limparTela();
                if (listMotoristaController != null) {
                    listMotoristaController.loadMotoristaList();
                }
            } catch (CampoObrigatorioException | FormatoInvalidoException | EvitarDuplicidadeException e) {
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }
        }

        @FXML
        private void limparTela() {
            txNome.setText("");
            txCPF.setText("");
            masterController.showFXMLFile("ListMotorista.fxml");
        }
    }
