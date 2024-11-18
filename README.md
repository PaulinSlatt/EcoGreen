# EcoGreen - Monitoramento e Economia de Energia

## Integrantes do Grupo

*Paulo Sergio Caetano Moreno Mafra* - Estudante na unidade Paulista, responsável pelo código fonte.
*Giovanna Lima Giantomaso* - Estudante na unidade Paulista, responsável pelo banco de dados.    
*Rebeca Silva Lopes* - Estudante na unidade Paulista, responsável pela documentação do projeto.

## Problema
Atualmente, 30% da energia residencial é desperdiçada, aumentando os custos e impactando o meio ambiente. Embora 85% das pessoas queiram economizar, 60% não sabem como começar. As soluções existentes são complexas e caras, criando a oportunidade para algo mais simples e motivador.

## Solução
O EcoGreen é uma plataforma prática e interativa que ajuda usuários a economizarem energia. Com cadastro e login seguros, os usuários podem:
- Registrar mensalmente seu consumo de energia.
- Receber feedback personalizado, como "Bom uso de energia" ou "Alto uso de energia".
- Acompanhar um histórico visual para entender o impacto de suas ações.
- Acumular pontos ao reduzir o consumo, que podem ser convertidos em descontos na conta de energia.

Essa abordagem transforma a economia de energia em algo simples, sustentável e recompensador.

## Público-Alvo
- Consumidores residenciais conscientes sobre o impacto ambiental.
- Jovens adultos interessados em sustentabilidade.
- Pequenos negócios que buscam práticas sustentáveis e economia de energia.

## Benefícios e Impactos
- Redução nas contas de energia.
- Promoção de hábitos sustentáveis e educação sobre consumo energético.
- Diminuição da pegada de carbono.
- Engajamento contínuo através da gamificação.

## Modelo de Negócio
O EcoGreen utiliza um programa de pontos que recompensa usuários com descontos reais na conta de energia. Parcerias com empresas do setor ajudam a sustentar o sistema e promovem a fidelização de clientes.

## Protótipo de Telas
1. **Tela de Login e Cadastro**: Acesso seguro às informações do usuário.
2. **Dashboard de Consumo**: Visualização clara e interativa do uso mensal.
3. **Histórico de Consumo**: Acompanhamento do progresso e impacto ao longo do tempo.

## Nossa Visão
Com o EcoGreen, economizar energia se torna uma experiência recompensadora. Juntos, podemos transformar o consumo sustentável em um impacto positivo para todos.
 
## Requisitos do Software
 
- Instalação do Java 17;
- Instalação do MySQL na máquina.
- Criação do database na maquina local.
 
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
 
