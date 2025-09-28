# Relatório de Avaliação do Trabalho

## Descrição do Trabalho
Sistema de Controle de Transportes Urbanos
Modele um sistema para controlar linhas de ônibus de uma cidade. Cada linha possui vários ônibus, motoristas e itinerários. Os motoristas podem dirigir diferentes ônibus em diferentes linhas. Implemente funcionalidades para consultar ônibus por linha, motoristas por linha e itinerários de cada linha


## Barema de Avaliação

| Critério | Peso | Pontuação | Justificativa |
| --- | --- | --- | --- |
| **Interface Gráfica (JavaFX)** | **20** | **20** | A interface implementa as funcionalidades propostas e exibe mensagens de erro de forma clara. |
| &nbsp;&nbsp;&nbsp;Completude e Funcionalidade | 10 | 10 | As telas de cadastro e listagem para todas as entidades foram implementadas. |
| &nbsp;&nbsp;&nbsp;Exibição de Mensagens de Erro | 10 | 10 | O sistema utiliza `Alert` para exibir exceções e mensagens de feedback, o que é uma boa prática. |
| **Camada de Negócio** | **30** | **20** | As regras de negócio e exceções personalizadas foram implementadas, mas com desvios importantes do modelo proposto. |
| &nbsp;&nbsp;&nbsp;Implementação das Regras de Negócio | 20 | 10 | A lógica de validação existe, mas foi implementada em um método `salvarComValidacao` em vez de sobrescrever `create`/`update`. O uso direto da classe `Service` genérica nos controllers de listagem ignora a camada de serviço específica. |
| &nbsp;&nbsp;&nbsp;Tratamento e Lançamento de Exceções | 10 | 10 | O aluno criou e utilizou exceções personalizadas (`CampoObrigatorioException`, `FormatoInvalidoException`, etc.) corretamente. |
| **Camada de Dados** | **20** | **15** | As entidades estão bem mapeadas, mas a implementação do repositório contém uma falha grave. |
| &nbsp;&nbsp;&nbsp;Implementação das Entidades | 10 | 10 | As entidades herdam de `AbstractEntity` e usam anotações JPA corretamente. |
| &nbsp;&nbsp;&nbsp;Implementação do Padrão Repository | 10 | 10 |  |
| **Separação em Camadas** | **20** | **15** | A estrutura de pacotes está correta, mas a comunicação entre as camadas é inconsistente e falha. |
| &nbsp;&nbsp;&nbsp;Segregação Correta das Funções | 10 | 10 | O projeto está bem organizado em pacotes (`controller`, `service`, `repository`, `model`). |
| &nbsp;&nbsp;&nbsp;Comunicação Entre as Camadas | 10 | 5 | Erro grave na comunicação, com os controllers de listagem acessando a classe `Service` genérica diretamente, pulando as regras de negócio das classes de serviço específicas. |
| **Boas Práticas** | **10** | **3** | O código apresenta diversas falhas arquiteturais e de design que violam as boas práticas e as sugestões do modelo. |
| &nbsp;&nbsp;&nbsp;Boas Práticas de Programação | 10 | 7 | Violação do SRP em alguns métodos demonstram uma compreensão parcial dos princípios de design de software. |
| **TOTAL** | **100** | **82** | |

---

## Análise Detalhada

### Pontos Positivos
- **Estrutura do Projeto:** O projeto está bem organizado em pacotes, refletindo uma clara separação de responsabilidades entre as camadas de apresentação, negócio e dados.
- **Tratamento de Exceções:** O uso de exceções personalizadas para lidar com erros de validação e regras de negócio é um ponto forte, tornando o código mais robusto e a comunicação de erros mais clara.
- **Interface Gráfica:** A interface do usuário é funcional, cobre todos os requisitos de cadastro e listagem do sistema e fornece feedback adequado ao usuário.
- **Mapeamento de Entidades:** As entidades JPA estão corretamente mapeadas, utilizando herança de `AbstractEntity` e as anotações apropriadas para atributos e relacionamentos.

### Pontos a Melhorar (Extremamente Rigoroso)

#### 1. [GRAVE] Uso Indevido da Classe `Service` Genérica
Os controllers de listagem (`ListMotoristaController`, `ListOnibusController`, etc.) instanciam e utilizam a classe `Service` genérica diretamente, ignorando as classes de serviço específicas que contêm a lógica de negócio.

**Código do Aluno (`ListMotoristaController.java`):**
```java
public void loadMotoristaList() {
    // Errado: Usa o Service genérico, pulando MotoristaService
    tblMotorista.setItems(FXCollections.observableList(new Service(Motorista.class).findAll()));
}
```
**Correção Sugerida:**
```java
private MotoristaService motoristaService = new MotoristaService();

public void loadMotoristaList() {
    // Correto: Usa a classe de serviço específica
    tblMotorista.setItems(FXCollections.observableList(motoristaService.findAll()));
}
```
**Impacto:** Essa prática viola a arquitetura em camadas. A classe `Service` genérica, como sugerido no `Modelo.md`, deveria ser `abstract` para impedir sua instanciação direta. Ao contornar as classes de serviço (`MotoristaService`, `OnibusService`), qualquer regra de negócio ou validação específica presente nelas é ignorada.

#### 2. Validação Fora do Padrão `create`/`update`
O `Modelo.md` orienta a adicionar validações dentro dos métodos `create` e `update`. O aluno criou um novo método (`salvarComValidacao`), o que quebra a interface padrão da camada de serviço.

**Código do Aluno (`MotoristaService.java`):**
```java
public void salvarComValidacao(Motorista motorista) throws ... {
    // ... lógica de validação ...
    create(motorista);
}
```
**Modelo Esperado (`MotoristaService.java`):**
```java
@Override
public Motorista create(Motorista entity) {
    // ... lógica de validação aqui ...
    if (entity.getNome() == null || entity.getNome().isEmpty()) {
        throw new CampoObrigatorioException("O campo 'Nome' é obrigatório.");
    }
    // ... outras validações ...
    return super.create(entity);
}
```
**Impacto:** A criação de um método separado para validação torna a camada de serviço inconsistente. Um desenvolvedor que chame `motoristaService.create(novoMotorista)` esperaria que as validações fossem executadas, mas elas não seriam, pois estão em outro método.

#### 3. Ausência de Repositórios Específicos
O `Modelo.md` incentiva a criação de subclasses de `Repository` para adicionar métodos de consulta específicos a uma entidade. O aluno não criou nenhuma, limitando-se às operações genéricas do `Repository` base. Isso dificulta a implementação de consultas mais complexas de forma organizada.

#### 4. Violação do Princípio da Responsabilidade Única (SRP)
Em vários controllers, métodos com um propósito claro acumulam responsabilidades adicionais.

**Código do Aluno (`CadMotoristaController.java`):**
```java
@FXML
private void limparTela() {
    txNome.setText("");
    txCPF.setText("");
    masterController.showFXMLFile("ListMotorista.fxml"); // Problema: Navegação em um método de "limpar"
}
```
**Impacto:** O método `limparTela` não deveria ser responsável por navegar para outra tela. Sua única responsabilidade deveria ser limpar os campos do formulário. Isso torna o código menos previsível e mais difícil de manter.
