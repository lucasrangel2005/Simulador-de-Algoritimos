# Simulador de Algoritmos de Substituição de Páginas

Este projeto foi desenvolvido para a disciplina de Sistemas Operacionais com o objetivo de comparar o número de faltas de página entre diferentes algoritmos de substituição.

## Algoritmos implementados

1. FIFO (First In, First Out)
2. LRU (Least Recently Used)
3. Relógio (Clock / Second Chance)
4. Ótimo

> Os outros algoritmos citados em sala (NFU e Envelhecimento) podem ser adicionados futuramente.

## Como funciona

O programa recebe:
- uma sequência de páginas (números inteiros)
- a quantidade de molduras (quadros) disponíveis na memória

Para cada algoritmo, o programa simula o carregamento das páginas e conta quantas **faltas de página** ocorreram.

No final, exibe assim:

- Método 1 (FIFO) - X faltas de página  
- Método 2 (LRU) - X faltas de página  
- Método 3 (Relógio) - X faltas de página  
- Método 4 (Ótimo) - X faltas de página  

## Como executar

1. Compile o projeto:

```bash
javac -d bin src/br/com/simuladorpaginas/*.java
