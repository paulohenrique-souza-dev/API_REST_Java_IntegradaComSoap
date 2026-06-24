# Guia de testes, Git e CI/CD

## 1. Organização dos testes

Os testes estão separados por camada e por classe, para ficar limpo e fácil de manter.

```text
src/test/java/com/soap/apirest
├── ApirestApplicationTests.java              # teste de contexto Spring
├── controller                                # testes unitários dos controllers
│   ├── CategoriaControllerTest.java
│   ├── ClienteControllerTest.java
│   ├── EstoqueControllerTest.java
│   ├── FornecedoresControllerTest.java
│   ├── ItensPedidoControllerTest.java
│   ├── PedidosControllerTest.java
│   ├── ProdutoControllerTest.java
│   └── VendedoresControllerTest.java
├── service                                   # testes dos parsers XML
│   ├── CategoriaXmlParserTest.java
│   ├── ClienteXmlParserTest.java
│   ├── EstoqueXmlParserTest.java
│   ├── FornecedoresXmlParserTest.java
│   ├── ItensPedidoXmlParserTest.java
│   ├── PedidosXmlParserTest.java
│   ├── ProdutoXmlParserTest.java
│   └── VendedoresXmlParserTest.java
├── soap                                      # testes SOAP
│   ├── SoapClientTest.java
│   └── SoapEnvelopeBuilderTest.java
└── support
    └── TestXmlFactory.java                   # massas XML reutilizáveis nos testes
```

## 2. Como rodar os testes localmente

No Windows:

```bash
mvnw.cmd test
```

No Linux/macOS/Git Bash:

```bash
./mvnw test
```

Resultado esperado:

```text
BUILD SUCCESS
```

## 3. Como subir no GitHub pela primeira vez

Entre na pasta do projeto:

```bash
cd caminho/da/pasta/apirest
```

Inicialize o Git:

```bash
git init
```

Veja os arquivos alterados:

```bash
git status
```

Adicione tudo no controle de versão:

```bash
git add .
```

Crie o primeiro commit:

```bash
git commit -m "chore: configura projeto com testes e ci"
```

Renomeie a branch principal para `main`:

```bash
git branch -M main
```

Conecte com o repositório do GitHub:

```bash
git remote add origin URL_DO_SEU_REPOSITORIO
```

Envie para o GitHub:

```bash
git push -u origin main
```

## 4. Fluxo recomendado de versionamento

Nunca trabalhe direto na `main` quando estiver treinando boas práticas.

Crie uma branch para cada mudança:

```bash
git checkout -b feature/mais-testes-produtos
```

Faça alterações, rode os testes:

```bash
mvnw.cmd test
```

Adicione e faça commit:

```bash
git add .
git commit -m "test: adiciona testes de produtos"
```

Envie a branch:

```bash
git push -u origin feature/mais-testes-produtos
```

No GitHub, abra um Pull Request da branch `feature/mais-testes-produtos` para `main`.

## 5. Diferença entre commit, push, pull request e merge

### Commit

Salva uma versão no seu computador.

```bash
git commit -m "test: adiciona testes de clientes"
```

### Push

Envia seus commits locais para o GitHub.

```bash
git push
```

### Pull Request

Pedido para revisar e juntar sua branch na `main`.

Use Pull Request para o GitHub Actions rodar os testes antes de entrar na `main`.

### Merge

Junta uma branch em outra.

Exemplo: juntar `feature/testes` dentro da `main`.

Boa prática: só fazer merge quando o CI estiver verde.

## 6. Boas práticas de CI

- Toda branch deve rodar `mvnw test` antes do merge.
- A `main` deve sempre ficar funcionando.
- Se o CI quebrar, corrija na branch antes do merge.
- Use nomes claros de branch: `feature/...`, `fix/...`, `test/...`, `docs/...`.
- Use commits pequenos e com mensagem clara.

Exemplos de commits:

```bash
git commit -m "test: adiciona testes do controller de estoque"
git commit -m "fix: corrige parser de produtos sem preco"
git commit -m "ci: configura github actions para maven"
git commit -m "docs: adiciona guia de versionamento"
```

## 7. Workflow do GitHub Actions

O arquivo `.github/workflows/java-ci.yml` roda automaticamente em:

- push para `main`, `master` ou `develop`
- pull request para `main`, `master` ou `develop`

Ele executa:

```bash
./mvnw test
```

Ou seja: se algum teste quebrar, o GitHub mostra o erro antes do merge.
