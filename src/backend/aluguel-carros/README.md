# 🚗 Sistema de Aluguel de Carros

Aplicação web completa para gestão de aluguel de veículos, com cadastro de clientes, frota de automóveis, agentes financeiros e fluxo de pedidos de aluguel. O projeto segue arquitetura MVC com Spring Boot, persistência via JPA, templates Thymeleaf, autenticação com Spring Security e banco em memória H2 por padrão.

---

## 📑 Sumário
- [🎯 Escopo do Sistema](#-escopo-do-sistema)
- [☕ Linguagem e Build](#-linguagem-e-build)
- [🏗️ Arquitetura e Tecnologias](#%EF%B8%8F-arquitetura-e-tecnologias)
- [📂 Estrutura do Projeto](#-estrutura-do-projeto)
- [⚙️ Configuração de Banco de Dados](#%EF%B8%8F-configuração-de-banco-de-dados)
- [🚀 Execução da Aplicação](#-execução-da-aplicação)
  - [▶️ Gradle (Desenvolvimento)](#️-gradle-desenvolvimento)
  - [📦 JAR Standalone](#-jar-standalone)
  - [🧪 Testes](#-testes)
- [🕹️ Como Usar (Passo a Passo)](#️-como-usar-passo-a-passo)
  - [1) Cadastro e Login](#1-cadastro-e-login)
  - [2) Catálogo de Carros](#2-catálogo-de-carros)
  - [3) Solicitar Aluguel](#3-solicitar-aluguel)
  - [4) Acompanhar Meus Pedidos](#4-acompanhar-meus-pedidos)
  - [5) Painel Administrativo](#5-painel-administrativo)
- [🧭 Endpoints Principais](#-endpoints-principais)
- [🗄️ Dados de Exemplo (data.sql)](#️-dados-de-exemplo-datasql)
- [💡 Melhorias Futuras](#-melhorias-futuras)
- [📬 Contato](#-contato)

---

## 🎯 Escopo do Sistema

Recursos principais gerenciados:

| Recurso | Descrição |
|---|---|
| Usuário/Cliente | Cadastro público e gestão administrativa de clientes |
| Automóvel | Cadastro e manutenção da frota de veículos |
| Agente | Bancos/empresas responsáveis por financiar os aluguéis |
| Pedido de Aluguel | Fluxo de solicitação, aprovação/rejeição e cancelamento |

Status de pedido: PENDENTE, APROVADO, REJEITADO, CANCELADO.

---

## ☕ Linguagem e Build
- Java 21
- Gradle (Wrapper incluso)
- Spring Boot 3.5.x

---

## 🏗️ Arquitetura e Tecnologias
- ☕ Java 21, Spring Boot 3.5.5
- 📦 Gradle + Spring Dependency Management
- 🧱 Spring MVC (Controllers, Views Thymeleaf)
- 🗄️ Spring Data JPA (H2 por padrão; PostgreSQL opcional)
- 🔐 Spring Security (login/sessão)
- 📝 Bean Validation (jakarta-validation)
- 🧪 JUnit 5 e Spring Security Test
- 🧰 Lombok (anotações)

---

## 📂 Estrutura do Projeto

```text
aluguel-carros/
  src/
    main/
      java/br/com/aluguel/aluguelcarros/
        controller/   # AdminController, AgenteController, AutomovelController, CadastroWebController, ClienteController, HomeController, PedidoController
        service/      # Regras de negócio (AutomovelService, UsuarioService, etc.)
        repository/   # Interfaces JPA (AutomovelRepository, UsuarioRepository, ...)
        model/        # Entidades JPA (Automovel, Usuario, Agente, PedidosDeAluguel)
        dto/          # DTOs (ClienteRequestDTO, ClienteResponseDTO, ...)
        facade/       # Orquestrações de caso de uso (ClienteFacade, PedidosDeAluguelFacade)
        config/       # SecurityConfig, UserDetailsServiceImpl
      resources/
        templates/    # Páginas Thymeleaf (index, login, cadastro, pedidos, admin, etc.)
        application.properties
        data.sql      # Carga inicial de dados (usuários, frota, agentes, pedidos)
  build.gradle
  settings.gradle
  README.md
```

---

## ⚙️ Configuração de Banco de Dados

Padrão (H2 em memória), conforme `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=update
```

- Console do H2: acesse `http://localhost:8080/h2-console` e use a URL JDBC acima.
- Dados de exemplo são carregados via `data.sql` no startup.

PostgreSQL (opcional): há dependência e propriedades comentadas no `application.properties`. Para ativar:
1. Comente as propriedades do H2 e descomente as do PostgreSQL.
2. Ajuste `spring.jpa.hibernate.ddl-auto` conforme sua estratégia (ex.: `update`).
3. Forneça `url`, `username`, `password` válidos.

---

## 🚀 Execução da Aplicação

### ▶️ Gradle (Desenvolvimento)
Pré-requisitos: Java 21 instalado.

```bash
./gradlew bootRun
# Windows PowerShell
./gradlew.bat bootRun
```

Aplicação iniciará em `http://localhost:8080`.

### 📦 JAR Standalone
Gerar artefato:

```bash
./gradlew clean bootJar
```

Executar:

```bash
java -jar build/libs/aluguel-carros-0.0.1-SNAPSHOT.jar
```

### 🧪 Testes

```bash
./gradlew test
```

---

## 🕹️ Como Usar (Passo a Passo)

### 1) Cadastro e Login
- Acesse `GET /cadastro` para criar sua conta de cliente (público).
- Após sucesso, você será direcionado ao `GET /login` para autenticação.
- Também existem usuários de exemplo no `data.sql` (senhas criptografadas). Caso necessário, ajuste ou cadastre-se via `/cadastro`.

### 2) Catálogo de Carros
- Acesse `GET /catalogo-carros` para visualizar a frota disponível (público/cliente logado).

### 3) Solicitar Aluguel
- A partir do catálogo, selecione um veículo e acesse `GET /pedidos/novo/{automovelId}`.
- Preencha datas e confirme. O pedido ficará com status PENDENTE.

### 4) Acompanhar Meus Pedidos
- Acesse `GET /pedidos/meus-pedidos` (cliente logado) para ver o histórico e status.

### 5) Painel Administrativo
- Lista de todos os pedidos: `GET /pedidos/todos`.
- Aprovar: `GET /pedidos/aprovar/{id}`.
- Rejeitar: `GET /pedidos/rejeitar/{id}`.
- Cancelar (admin): `GET /pedidos/cancelar-admin/{id}`.
- Gestão de clientes: `GET /admin/gestao-clientes` (criar/editar/excluir via formulário).
- Gestão de automóveis: `GET /automoveis` (criar/editar/excluir via formulário).
- Gestão de agentes: `GET /agentes` (criar/editar/excluir via formulário).

---

## 🧭 Endpoints Principais

Público/Cliente
- `GET /` → Home
- `GET /login` → Login
- `GET /cadastro` / `POST /cadastro` → Cadastro de cliente
- `GET /catalogo-carros` → Catálogo de veículos
- `GET /pedidos/novo/{automovelId}` → Form de novo pedido
- `POST /pedidos/salvar` → Salvar pedido
- `GET /pedidos/meus-pedidos` → Pedidos do cliente logado

Administrativo
- `GET /pedidos/todos` → Lista de pedidos (admin/agente)
- `GET /pedidos/aprovar/{id}` | `GET /pedidos/rejeitar/{id}` | `GET /pedidos/cancelar-admin/{id}`
- `GET /admin/gestao-clientes` | `GET /admin/clientes/novo` | `GET /admin/clientes/editar/{id}` | `POST /admin/clientes/salvar` | `GET /admin/clientes/excluir/{id}`
- `GET /automoveis` | `GET /automoveis/novo` | `POST /automoveis` | `GET /automoveis/editar/{id}` | `POST /automoveis/atualizar/{id}` | `GET /automoveis/deletar/{id}`
- `GET /agentes` | `GET /agentes/novo` | `POST /agentes` | `GET /agentes/editar/{id}` | `POST /agentes/atualizar/{id}` | `GET /agentes/deletar/{id}`

API REST (Clientes)
- `POST /api/clientes` → Cria cliente (admin)
- `GET /api/clientes/{id}` → Busca por id
- `GET /api/clientes` → Lista todos
- `DELETE /api/clientes/{id}` → Remove cliente

---

## 🗄️ Dados de Exemplo (data.sql)

O arquivo `src/main/resources/data.sql` popula:
- Tabela `usuarios` com exemplos de administrador e cliente (senhas com hash bcrypt)
- Tabela `automovel` com frota inicial
- Tabela `agente` com agentes financeiros
- Tabela `pedidos_de_aluguel` com um pedido de exemplo

Observações:
- Como as senhas estão criptografadas, caso não saiba os valores originais, utilize o fluxo de cadastro (`/cadastro`) para criar credenciais próprias ou substitua os hashes no `data.sql` por novos hashes gerados.
- Com H2 em memória, os dados existem enquanto a aplicação estiver em execução.

---

## 💡 Melhorias Futuras
- Perfis de ambiente (dev/test/prod) com PostgreSQL padrão em prod
- RBAC por perfis (cliente, admin, agente) com telas segregadas
- Paginação e filtros no catálogo e na lista administrativa de pedidos
- Logs estruturados e observabilidade (Micrometer/Prometheus)
- Mais testes de integração (controllers e repositórios)

---

## 📬 Contato
**Leandro Alencar**
- E-mail: `Leandro130333.dev@gmail.com`
- WhatsApp/Telefone: `(31) 98347-9067`


