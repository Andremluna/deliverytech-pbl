# DeliveryTech — Projeto-base PBL

**Disciplina:** Desenvolvimento de Aplicações para Internet  
**Organização:** em duplas, uma entrega por dupla  
**Tecnologias:** Java 21, Spring Boot 4.0.9, HTML, CSS, JavaScript, HTTP e JSON  
**Tempo de implementação:** 40 minutos, após a fundamentação da aula

## Situação-problema

A DeliveryTech vende kits de R$ 25,00. O usuário informa uma quantidade inteira de 1 a 20, e o Backend Java deve calcular e retornar o valor total. O Frontend deverá enviar uma requisição HTTP e apresentar a resposta.

O projeto **é intencionalmente incompleto**: o Controller retorna HTTP `501` e o JavaScript mostra uma mensagem provisória. Os testes de comportamento **devem falhar inicialmente**, até a dupla implementar a funcionalidade.

## Tarefas da dupla

| Tempo | Tarefa | Onde |
|---|---|---|
| 5 min | Identificar dado, método HTTP, rota e responsabilidades | Discussão da dupla |
| 10 min | Completar validação e cálculo Java | `PedidoController.java` |
| 15 min | Completar `fetch()`, JSON e apresentação do resultado | `index.html` |
| 10 min | Verificar testes, interface e erros | GitHub Actions e navegador |

### Atividade 2: Backend

Edite `src/main/java/com/deliverytech/demo/PedidoController.java`. No método `calcular`:

1. Leia `pedido.quantidade()`.
2. Se o valor estiver fora de 1 a 20, responda `400 Bad Request` com um `Erro` explicativo.
3. Caso contrário, calcule `PRECO.multiply(BigDecimal.valueOf(quantidade))`.
4. Devolva `200 OK` com `Resultado(quantidade, PRECO, total)`.
5. Remova o retorno temporário com status `501`.

### Atividade 3: Frontend

Edite `src/main/resources/static/index.html`:

1. Substitua os `TODO` do listener `submit`.
2. Mostre `Calculando...` enquanto aguarda.
3. Use `fetch("/api/pedidos/calcular", { method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify({ quantidade }) })`.
4. Interprete o JSON por meio de `await resposta.json()`.
5. Verifique `resposta.ok` e apresente erros recebidos da API.
6. Apresente `dados.valorTotal` com `toLocaleString("pt-BR", {style: "currency", currency: "BRL"})`.
7. Trate falhas de comunicação com `try/catch`.
8. Remova a mensagem provisória.

## Contrato da API

`POST /api/pedidos/calcular`

Requisição:

```json
{"quantidade":4}
```

Resposta esperada (`200`):

```json
{"quantidade":4,"valorUnitario":25.00,"valorTotal":100.00}
```

Resposta esperada para quantidade `0` ou `21` (`400`):

```json
{"mensagem":"A quantidade deve estar entre 1 e 20."}
```

## Testes

| Quantidade | Esperado |
|---|---|
| 1 | R$ 25,00 |
| 4 | R$ 100,00 |
| 20 | R$ 500,00 |
| 0 | HTTP 400 |
| 21 | HTTP 400 |

**Importante:** os valores inválidos devem ser enviados diretamente à API, pois o formulário HTML pode impedi-los. Os testes Java incluídos são apenas parte da validação: a dupla também precisa demonstrar a integração real na interface.

## Sem instalação nos computadores dos estudantes

1. O professor publica este projeto-base no GitHub **antes da aula**.
2. Cada dupla utiliza um fork ou repositório previamente autorizado e edita o código pela interface do GitHub.
3. Depois de registrar o commit, abre a aba **Actions**, seleciona a execução e consulta o resultado do job `testes`.
4. O professor disponibiliza antecipadamente uma estratégia de publicação no Coolify, com URL de teste. **Fazer fork não cria automaticamente um deploy.**
5. Na URL publicada, a dupla testa a página e usa **F12 → Network → Fetch/XHR** para conferir a requisição e a resposta.

### Orientação para o professor: Coolify

- Criar previamente a aplicação utilizando o `Dockerfile` deste repositório.
- Confirmar a branch conectada, a porta interna `8080` e a URL pública.
- Testar `GET /api/pedidos/health` (deve responder `OK`).
- O `Dockerfile` usa `-DskipTests` para permitir publicar o projeto-base ainda incompleto; isso **não substitui** o resultado do GitHub Actions, que deverá executar os testes.
- Organizar como cada dupla demonstrará sua versão. Pode ser necessário trocar a branch/repositório e realizar um redeploy supervisionado.
- Não publicar chaves, tokens ou senhas no repositório.

## Entrega por dupla

1. Link do repositório com as alterações.
2. Evidência de GitHub Actions (resultado do build e dos testes).
3. URL da funcionalidade executável ou demonstração em ambiente compartilhado.
4. Evidência de teste válido e inválido, mostrando Network/Response.
5. Explicação curta do fluxo: interface, HTTP, Controller, processamento, resposta e DOM.

## Referências de documentação

- [Spring Boot 4.0 — primeira aplicação](https://docs.spring.io/spring-boot/4.0/tutorial/first-application/index.html)
- [Spring Boot 4.0 — sistemas de build](https://docs.spring.io/spring-boot/4.0/reference/using/build-systems.html)
- [MDN — Fetch API](https://developer.mozilla.org/pt-BR/docs/Web/API/Fetch_API)

## Execução opcional fora da aula

Se o professor desejar reproduzir em um ambiente com Maven e Java 21:

```bash
mvn test
mvn spring-boot:run
```

A página estará em `http://localhost:8080/` quando o processo estiver ativo. Essa execução local **não é exigida dos estudantes**.
