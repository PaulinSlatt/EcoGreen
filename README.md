# Projeto_Energia

## Integrantes do Grupo

*Paulo Sergio Caetano Moreno Mafra* - Estudante na unidade Paulista, responsável pelo código fonte.
*Giovanna Lima Giantomaso* - Estudante na unidade Paulista, responsável pelo banco de dados.    
*Rebeca Silva Lopes* - Estudante na unidade Paulista, responsável pela documentação do projeto.
 
## Requisitos do Software
 
- Instalação do Java 17;
- Instalação do MySQL na máquina.
- - Cração do database na maquina local.
 
## Instruções de como rodar a aplicação
 
1. Clonar o projeto;
2. Baixar as dependências do Groovy;
3. Criar o database localmente;
4. Rodar os scripts SQL;
5. Rodar o projeto;
6. Executar as requisições.
 
## Link da Proposta Tecnológica
 
[Proposta Tecnológica](link vai aqui)
 
## Link para Documentação Swagger
 
A documentação Swagger da API está disponível em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
 
## Listagem de Endpoints
 
### Recursos disponíveis
 
- */usuarios*: Representa os usuarios cadastrados no sistema.
- */consumo*: Representa os consumos associados aos usuarios.

 
## Uso de Métodos HTTP
 
### Usuarios
 
- GET /usuarios: Retorna a lista de todos os usuarios cadastrados.
- GET /usuarios/{id}: Retorna os detalhes de um usuario específico, baseado no ID.
- POST /usuarios: Cria um novo registro de usuario.
- POST /usuarios/login: Autentifica um usuario já cadastrado.
- PUT /usuarios/editar: Atualiza os dados de um usuario existente.
- DELETE /usuarios/{id}: Remove um usuario do sistema.
 
### Consumo
 
- GET /consumo: Lista todos os consumos disponíveis.
- GET /consumo/{id}: Retorna os detalhes de um consumo específico.
- GET /consumo/usuario/{usuarioId}: Retorna os detalhes do consumo do usuario com aquele determinado id.
- POST /consumo: Adiciona um novo consumo.
- PUT /consumo/{id}: Atualiza um consumo existente.
- DELETE /consumo/{id}: Remove um consumo.
 
