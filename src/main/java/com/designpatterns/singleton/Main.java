package com.designpatterns.singleton;

import com.designpatterns.singleton.catalog.CarCatalog;
import com.designpatterns.singleton.model.Car;

import java.util.List;

public class Main {

    private static final String LINE  = "=".repeat(55);
    private static final String DLINE = "-".repeat(55);

    public static void main(String[] args) {

        System.out.println(LINE);
        System.out.println("         CATALOGO DE CARROS - SINGLETON PATTERN");
        System.out.println(LINE);

        // Obtendo a instância única do catálogo
        CarCatalog catalog = CarCatalog.getInstance();

        // Verificando que é a mesma instância (essência do Singleton)
        CarCatalog anotherReference = CarCatalog.getInstance();
        System.out.println("\n[SINGLETON] Mesma instância? " +
                (catalog == anotherReference ? "SIM" : "NAO"));
        System.out.println("[SINGLETON] hashCode instância 1: " + System.identityHashCode(catalog));
        System.out.println("[SINGLETON] hashCode instância 2: " + System.identityHashCode(anotherReference));

        // ── 1. Todos os carros ordenados por marca/modelo ──────────────
        System.out.println("\n" + LINE);
        System.out.printf("  TODOS OS CARROS (%d no total)%n", catalog.getTotalCars());
        System.out.println(LINE);
        printHeader();
        for (Car car : catalog.getSortedByBrandAndModel()) {
            System.out.println("  " + car);
        }

        // ── 2. Filtro por marca ─────────────────────────────────────────
        printBrandSection(catalog, "Toyota");
        printBrandSection(catalog, "Volkswagen");
        printBrandSection(catalog, "Ford");

        // ── 3. Filtro por ano ───────────────────────────────────────────
        printYearSection(catalog, 2024);
        printYearSection(catalog, 2022);

        // ── 4. Adicionando um carro dinamicamente ───────────────────────
        System.out.println("\n" + LINE);
        System.out.println("  ADICIONANDO NOVO CARRO AO CATALOGO");
        System.out.println(LINE);
        catalog.addCar(new Car("BYD", "Seal", 2025));
        System.out.println("  Carro adicionado: BYD Seal 2025");
        System.out.printf("  Total agora: %d carros%n", catalog.getTotalCars());

        System.out.println("\n" + LINE);
        System.out.println("  FIM DO CATALOGO");
        System.out.println(LINE);
    }

    private static void printHeader() {
        System.out.println(DLINE);
        System.out.printf("  %-15s | %-25s | %s%n", "MARCA", "MODELO", "ANO");
        System.out.println(DLINE);
    }

    private static void printBrandSection(CarCatalog catalog, String brand) {
        List<Car> filtered = catalog.getCarsByBrand(brand);
        System.out.println("\n" + LINE);
        System.out.printf("  MARCA: %s (%d modelos)%n", brand.toUpperCase(), filtered.size());
        System.out.println(LINE);
        printHeader();
        filtered.forEach(c -> System.out.println("  " + c));
    }

    private static void printYearSection(CarCatalog catalog, int year) {
        List<Car> filtered = catalog.getCarsByYear(year);
        System.out.println("\n" + LINE);
        System.out.printf("  ANO: %d (%d modelos)%n", year, filtered.size());
        System.out.println(LINE);
        printHeader();
        filtered.forEach(c -> System.out.println("  " + c));
    }
}
