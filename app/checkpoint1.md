# Navegação De Telas

## Descrição do projeto:
Aplicativo Android feito com Jetpack Compose que trabalha navegação entre telas — Menu, Perfil e Pedidos. O projeto foi evoluído de forma incremental, onde cada commit adiciona um conceito novo de passagem de parâmetros pelas rotas de navegação.

## Objetivo da prova:
O objetivo da prova é avaliar minha capacidade de evoluir um projeto, já iniciado, aplicando conceitos de navegação entre telas no Android com Jetpack Compose Navigation.

- Domínio técnico do código
- Capacidade de expansão do código
- Documentação

## Evoluções implementadas:
A baixo a explicação de cada evolução implementada

### Commit 1 — Parâmetro obrigatório na tela de Perfil

A tela de Perfil abria sem nenhuma informação do usuário. A solução foi passar o nome pela rota de navegação.

**`PerfilScreen.kt`** recebeu o parâmetro `nome: String` e passou a exibir `"PERFIL - Fulano de Tal"` no título.

**`MainActivity.kt`** teve a rota alterada de `"perfil"` para `"perfil/{nome}"` — as chaves indicam que aquele trecho é variável. O valor é extraído com `getString("nome", "Usuário Genérico")`, onde o segundo argumento é o fallback.

**`MenuScreen.kt`** passou a chamar `navigate("perfil/Fulano de Tal")`, embutindo o nome direto na URL.

```
navigate("perfil/Fulano de Tal")
  → rota "perfil/{nome}" extrai "Fulano de Tal"
  → PerfilScreen exibe "PERFIL - Fulano de Tal"
```
 
---

### Commit 2 — Parâmetro opcional na tela de Pedidos

Nem sempre o cliente é conhecido na hora de navegar, então esse parâmetro precisava ser opcional. A sintaxe da rota muda pra isso.

**`PedidosScreen.kt`** recebeu `cliente: String?` e o título virou `"PEDIDOS - $cliente"`.

**`MainActivity.kt`** teve a rota definida como `"pedidos?cliente={cliente}"` (query string). Parâmetros opcionais exigem `navArgument` com `defaultValue` — aqui ficou `"Cliente Genérico"`.

| | Obrigatório (Perfil) | Opcional (Pedidos) |
|---|---|---|
| Sintaxe da rota | `perfil/{nome}` | `pedidos?cliente={cliente}` |
| Precisa de `navArgument`? | Não | Sim |
| Funciona sem passar o valor? | Não | Sim |
 
---

### Commit 3 — Passando um valor real pro parâmetro opcional

A rota já estava pronta desde o commit anterior. Aqui só atualizei o botão da `MenuScreen` pra enviar um valor de fato.

**`MenuScreen.kt`** passou de `navigate("pedidos")` para `navigate("pedidos?cliente=Cliente XPTO")`. A tela agora exibe `"PEDIDOS - Cliente XPTO"`.
 
---

### Commit 4 — Múltiplos parâmetros com tipos diferentes

O Perfil evoluiu pra receber dois valores: `nome` (String) e `idade` (Int). O ponto novo aqui é declarar o tipo de cada parâmetro explicitamente, porque o Navigation Compose precisa saber como converter cada trecho da URL pro tipo certo.

**`PerfilScreen.kt`** recebeu `idade: Int` e o texto virou `"PERFIL - Fulano de Tal tem 27 anos"`.

**`MainActivity.kt`** teve a rota atualizada pra `"perfil/{nome}/{idade}"`, com `navArgument` declarado pra cada parâmetro usando `NavType.StringType` e `NavType.IntType`.

**`MenuScreen.kt`** passou a navegar pra `"perfil/Fulano de Tal/27"`.

```
navigate("perfil/Fulano de Tal/27")
  → nome = "Fulano de Tal" (String)
  → idade = 27 (Int)
  → exibe: "PERFIL - Fulano de Tal tem 27 anos"
```