package com.designpatterns.singleton.catalog;

import com.designpatterns.singleton.model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarCatalog {

    private static volatile CarCatalog instance;

    private final List<Car> cars;

    private CarCatalog() {
        cars = new ArrayList<>();
        loadDefaultCars();
    }

    public static CarCatalog getInstance() {
        if (instance == null) {
            synchronized (CarCatalog.class) {
                if (instance == null) {
                    instance = new CarCatalog();
                }
            }
        }
        return instance;
    }

    private void loadDefaultCars() {
        cars.add(new Car("Toyota",     "Corolla Cross",         2024));
        cars.add(new Car("Toyota",     "Hilux",                 2023));
        cars.add(new Car("Toyota",     "Yaris",                 2022));
        cars.add(new Car("Honda",      "Civic",                 2024));
        cars.add(new Car("Honda",      "HR-V",                  2023));
        cars.add(new Car("Honda",      "City",                  2022));
        cars.add(new Car("Volkswagen", "Golf GTI",              2024));
        cars.add(new Car("Volkswagen", "Polo",                  2023));
        cars.add(new Car("Volkswagen", "Virtus",                2022));
        cars.add(new Car("Ford",       "Ranger",                2024));
        cars.add(new Car("Ford",       "Bronco Sport",          2023));
        cars.add(new Car("Ford",       "Territory",             2022));
        cars.add(new Car("Chevrolet",  "Onix Plus",             2024));
        cars.add(new Car("Chevrolet",  "Tracker",               2023));
        cars.add(new Car("Chevrolet",  "S10",                   2022));
        cars.add(new Car("Hyundai",    "HB20",                  2024));
        cars.add(new Car("Hyundai",    "Creta",                 2023));
        cars.add(new Car("Hyundai",    "Tucson",                2022));
        cars.add(new Car("Jeep",       "Renegade",              2024));
        cars.add(new Car("Jeep",       "Compass",               2023));
        cars.add(new Car("Fiat",       "Fastback",              2024));
        cars.add(new Car("Fiat",       "Pulse",                 2023));
        cars.add(new Car("Fiat",       "Strada",                2022));
        cars.add(new Car("Renault",    "Kardian",               2024));
        cars.add(new Car("Renault",    "Kwid",                  2023));
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getAllCars() {
        return Collections.unmodifiableList(cars);
    }

    public List<Car> getCarsByBrand(String brand) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> getCarsByYear(int year) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYear() == year) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> getSortedByBrandAndModel() {
        List<Car> sorted = new ArrayList<>(cars);
        sorted.sort(Comparator.comparing(Car::getBrand).thenComparing(Car::getModel));
        return sorted;
    }

    public int getTotalCars() {
        return cars.size();
    }
}
