# Flood Fill

Este repositório contém uma implementação do algoritmo de preenchimento por inundação utilizando estruturas de dados de **Pilha** e **Fila** construídas manualmente. O projeto foi desenvolvido como parte da disciplina **Resolução de Problemas Estruturados em Computação** do curso de Bacharelado em Sistemas de Informação da PUCPR.


### 📖 Descrição 

O **Flood Fill** é um algoritmo que altera a informação de nós (pixels) conectados, similar à ferramenta "balde de tinta" encontrada em softwares de desenho. Nesta implementação, o usuário pode escolher entre duas estruturas de dados para armazenar os pixels:
- **Pilha (Stack)** – Processamento em profundidade (LIFO)
- **Fila (Queue)** – Processamento em largura (FIFO)


---
### 🔍 Como o algoritmo funciona?

1. O usuário seleciona uma imagem PNG e fornece uma coordenada inicial (X, Y)  
2. O programa visualiza a coordenada selecionada com um ponto vermelho  
3. O usuário escolhe a cor de preenchimento e a tolerância de cor  
4. O algoritmo armazena a cor original do pixel na coordenada inicial  
5. O algoritmo usa Pilha ou Fila (escolhida pelo usuário) para armazenar os pixels a processar  
6. Ele verifica os vizinhos (direita, esquerda, abaixo e acima) e continua preenchendo se forem semelhantes  
7. Durante o processo, frames da animação são capturados e exibidos  
8. A imagem final é salva como `resultado.png`


---   
### 🎥 Demonstração do preenchimento

- **Usando Pilha**
  
  ![Animação com Pilha](https://raw.githubusercontent.com/joaoson/FloodFillJava/refs/heads/main/Gifs/pilha.gif?token=GHSAT0AAAAAADAVWQTQUWEQ44S7XYCO7IXAZ7MMQJA)

- **Usando Fila**
  
 ![Animação com Fila](https://raw.githubusercontent.com/joaoson/FloodFillJava/refs/heads/main/Gifs/fila.gif?token=GHSAT0AAAAAADAVWQTQNUH2WTOOLU46SAKIZ7MMPWQ)


---   
### 🗂️ Estrutura do projeto

O projeto segue os princípios da Programação Orientada a Objetos e está organizado da seguinte forma:

#### Pacote "Principal"
- **Main.java**: Classe principal que controla o fluxo do programa, interage com o usuário e coordena as demais classes.

#### Pacote "Preenchimento"
- **FloodFill.java**: Implementa o algoritmo de preenchimento, com métodos para uso de Pilha (fillStack) e Fila (fillQueue).  
- **ImageHandler.java**: Responsável pelo carregamento e salvamento de imagens.  
- **PixelPosition.java**: Representa uma posição de pixel, armazenando coordenadas e cor.  
- **AnimationCanvas.java**: Gerencia a animação do processo de preenchimento, exibindo os frames em uma janela.

#### Pacote "EstruturasDeDados"
- **Node.java**: Implementa um nó genérico para uso nas estruturas de dados.  
- **Pending.java**: Interface que define os métodos comuns entre Pilha e Fila, permitindo o uso polimórfico dessas estruturas.  
- **Stack.java**: Implementação da estrutura de dados Pilha (LIFO).  
- **Queue.java**: Implementação da estrutura de dados Fila (FIFO).


---
### 🔎 Detalhamento das classes

#### Main.java
- Interação com o usuário
- Carregamento e visualização da imagem
- Escolha de coordenada, cor e estrutura
- Execução do algoritmo e exibição da animação

#### FloodFill.java
- Métodos `fillStack()` e `fillQueue()`
- Captura de frames
- Verificação de cor e tolerância

#### ImageHandler.java
- Carrega e salva imagens PNG 

#### PixelPosition.java
- Armazena coordenadas e cor do pixel

#### AnimationCanvas.java
- Lista encadeada de frames
- Timer para animação
- Exibição em janela Swing

#### Estruturas de dados
- **Node.java:** Nó genérico com dado e próximo
- **Pending.java:** Interface comum entre Stack e Queue
- **Stack.java:** Pilha (LIFO)
- **Queue.java:** Fila (FIFO)


---
### 🖥️ Requisitos do sistema

- Java 17 ou superior  
- Suporte à leitura e escrita de imagens PNG


---
### 🚀 Como executar

1. Compile todas as classes do projeto  
2. Execute a classe `Main`  
3. Siga as instruções no console:
   - Caminho da imagem
   - Coordenada inicial
   - Cor e tolerância
   - Pilha ou Fila
4. Acompanhe a animação e visualize o resultado


---
### ✅ Considerações finais

##### 1. Encapsulamento
As classes encapsulam seus dados e comportamentos, expondo apenas o necessário através de métodos públicos. Na classe `FloodFill`, por exemplo, variáveis como `frameCounter`, `modifiedCounter` e `colorTolerance` são declaradas como privadas, sendo acessíveis apenas através de métodos específicos como `setColorTolerance()`.  


##### 2. Herança
Utilizada nas estruturas de dados, com a interface `Pending` sendo implementada por `Stack` e `Queue`.  
Essa abordagem estabelece um contrato comum com os métodos `add()`, `remove()` e `empty()` que ambas as estruturas devem implementar, permitindo que sejam usadas de forma intercambiável no algoritmo de preenchimento. Cada classe implementa esses métodos de acordo com seu comportamento específico (**LIFO** para `Stack`, **FIFO** para `Queue`).

##### 3. Polimorfismo
O método `fill` na classe `FloodFill` trabalha de forma polimórfica com qualquer estrutura que implemente a interface `Pending`. Ele utiliza os métodos abstratos definidos na interface sem se preocupar com a implementação concreta, permitindo que o mesmo algoritmo funcione tanto com pilha (processamento em profundidade) quanto com fila (processamento em largura), produzindo resultados visualmente diferentes.

##### 4. Abstração
O código abstrai conceitos complexos em componentes gerenciáveis, como a manipulação de imagens e as estruturas de dados. A classe `ImageHandler` encapsula toda a complexidade de leitura e escrita de arquivos de imagem em métodos simples como `loadImage()` e `saveImage()`, ocultando detalhes de implementação como conversão de formatos e manipulação de pixels.

##### 5. Modularidade
O projeto é dividido em classes com responsabilidades bem definidas, seguindo o princípio da responsabilidade única.  
Cada componente do sistema tem uma função específica:
- `Main` gerencia a interface com o usuário;
- `FloodFill` implementa o algoritmo;
- `ImageHandler` gerencia as operações de imagem;
- `AnimationCanvas` controla a animação;
- `Stack` e `Queue` gerenciam o armazenamento temporário de pixels durante o processamento.


---
### 👩‍💻 Alunos

- Amanda Queiroz Sobral 
- Carlos Domingues Hobmeier
- João Vitor de Freitas
