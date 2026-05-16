# Design Patterns em Java

> Projeto desenvolvido para a formação **EBAC — Engenheiro(a) de Software Java**, com implementações práticas de Design Patterns utilizando cenários reais.

---

## Sobre o Projeto

Este repositório reúne exemplos didáticos e funcionais dos principais **padrões de projeto (Design Patterns)** em Java, organizados por categoria. Cada padrão é apresentado com um caso de uso concreto, código limpo e saída demonstrável no terminal.

---

## Padrões Implementados

| # | Padrão | Categoria | Status |
|---|--------|-----------|--------|
| 1 | [Singleton — Catálogo de Carros](#singleton) | Criacional | ✅ Concluído |

> Novos padrões serão adicionados progressivamente ao longo do curso.

---

## Singleton — Catálogo de Carros

### O que é o Singleton?

O **Singleton** é um padrão criacional que garante que uma classe possua **apenas uma instância** durante toda a execução do programa, fornecendo um ponto de acesso global a ela.

```
┌─────────────────────────────────────────────────────────┐
│                     CarCatalog                          │
├─────────────────────────────────────────────────────────┤
│  - instance: CarCatalog  (static, volatile)             │
│  - cars: List<Car>                                      │
├─────────────────────────────────────────────────────────┤
│  - CarCatalog()           ← construtor PRIVADO          │
│  + getInstance(): CarCatalog  ← único ponto de acesso   │
│  + addCar(Car)                                          │
│  + getAllCars(): List<Car>                               │
│  + getCarsByBrand(String): List<Car>                    │
│  + getCarsByYear(int): List<Car>                        │
│  + getSortedByBrandAndModel(): List<Car>                │
│  + getTotalCars(): int                                  │
└─────────────────────────────────────────────────────────┘
```

### Por que usar o Singleton aqui?

Um catálogo de carros é um recurso compartilhado. Ter múltiplas instâncias significaria:

- Dados desincronizados entre partes diferentes da aplicação
- Consumo desnecessário de memória
- Inconsistências ao adicionar/remover veículos

Com o Singleton, **todos os módulos da aplicação acessam e modificam o mesmo catálogo**.

### Implementação Thread-Safe (Double-Checked Locking)

```java
public class CarCatalog {

    private static volatile CarCatalog instance;

    private CarCatalog() {
        // construtor privado — impede instanciação externa
    }

    public static CarCatalog getInstance() {
        if (instance == null) {                       // 1ª verificação (sem lock)
            synchronized (CarCatalog.class) {
                if (instance == null) {               // 2ª verificação (com lock)
                    instance = new CarCatalog();
                }
            }
        }
        return instance;
    }
}
```

| Elemento | Função |
|----------|--------|
| `private` construtor | Bloqueia criação externa via `new` |
| `static volatile` | Garante visibilidade correta entre threads |
| Double-checked locking | Evita sincronização desnecessária após a criação |

### Estrutura do Projeto

```
design_patterns/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── designpatterns/
│                   └── singleton/
│                       ├── model/
│                       │   └── Car.java          # Entidade carro (marca, modelo, ano)
│                       ├── catalog/
│                       │   └── CarCatalog.java   # Singleton — catálogo de carros
│                       └── Main.java             # Demonstração do padrão
└── README.md
```

### Marcas e Modelos Cadastrados

| Marca | Modelos |
|-------|---------|
| Toyota | Corolla Cross, Hilux, Yaris |
| Honda | Civic, HR-V, City |
| Volkswagen | Golf GTI, Polo, Virtus |
| Ford | Ranger, Bronco Sport, Territory |
| Chevrolet | Onix Plus, Tracker, S10 |
| Hyundai | HB20, Creta, Tucson |
| Jeep | Renegade, Compass |
| Fiat | Fastback, Pulse, Strada |
| Renault | Kardian, Kwid |

### Saída Esperada (trecho)

```
=======================================================
         CATALOGO DE CARROS - SINGLETON PATTERN
=======================================================

[SINGLETON] Mesma instância? SIM
[SINGLETON] hashCode instância 1: 1829164700
[SINGLETON] hashCode instância 2: 1829164700

=======================================================
  TODOS OS CARROS (25 no total)
=======================================================
-------------------------------------------------------
  MARCA           | MODELO                    | ANO
-------------------------------------------------------
  Chevrolet       | Onix Plus                 | 2024
  Chevrolet       | S10                       | 2022
  Chevrolet       | Tracker                   | 2023
  ...

=======================================================
  MARCA: TOYOTA (3 modelos)
=======================================================
  Toyota          | Corolla Cross             | 2024
  Toyota          | Hilux                     | 2023
  Toyota          | Yaris                     | 2022

=======================================================
  ADICIONANDO NOVO CARRO AO CATALOGO
=======================================================
  Carro adicionado: BYD Seal 2025
  Total agora: 26 carros
```

---

## Como Executar

### Pré-requisito

- Java 17+

```bash
# Compilar
javac -d out src/main/java/com/designpatterns/singleton/model/Car.java \
             src/main/java/com/designpatterns/singleton/catalog/CarCatalog.java \
             src/main/java/com/designpatterns/singleton/Main.java

# Executar
java -cp out com.designpatterns.singleton.Main
```

---

## Conceitos Abordados

- **Padrões Criacionais** — controlam como os objetos são criados
- **Encapsulamento** — construtor privado e acesso controlado via método estático
- **Thread Safety** — uso de `volatile` e `synchronized` para ambientes concorrentes
- **Imutabilidade** — classe `Car` com campos `final`
- **Coleções** — uso de `Collections.unmodifiableList` para proteger o estado interno

---

## Tecnologias

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=openjdk)

---

## Autor

**Gabriel Vinces**
Estudante de Engenharia de Software — EBAC

[![GitHub](https://img.shields.io/badge/GitHub-gabedossa-181717?style=flat-square&logo=github)](https://github.com/gabedossa)
