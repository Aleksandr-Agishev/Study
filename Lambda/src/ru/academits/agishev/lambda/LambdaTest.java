package ru.academits.agishev.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaTest {
    public static void main(String[] args) {
        List<Persons> personsList = Arrays.asList(new Persons("Иван", 15),
                new Persons("Степан", 25),
                new Persons("Петр", 27),
                new Persons("Афросий", 18),
                new Persons("Ерёма", 43),
                new Persons("Евклид", 155),
                new Persons("Профокл", 15),
                new Persons("Кузьма", 10),
                new Persons("Фома", 64),
                new Persons("Иван", 4),
                new Persons("Степан", 15),
                new Persons("Ерёма", 41));

        // А. получить список уникальных имен
        List<String> listName1 = personsList.stream().map(Persons::getName).distinct().collect(Collectors.toList());

        // Б. вывести список уникальных имен в формате Имена: Иван, Петр, Сергей
        boolean checkOfStartList = true;
        for (String e : listName1) {
            if (checkOfStartList) {
                System.out.print(e);
                checkOfStartList = false;
            } else {
                System.out.print(", " + e);
            }
        }
        System.out.println(".");

        // В. получить список людей младше 18 лет, посчитать для них средний возраст
        List<Persons> listName2 = personsList.stream().filter(p -> p.getAge() < 18).collect(Collectors.toList());

        double average = 0;
        int countOfPersons = 0;
        for (Persons persons : listName2) {
            average += persons.getAge();
            countOfPersons++;
        }
        average = average / countOfPersons;
        System.out.println("average Age = " + average);

        // Г. при помощи группировки получить Мар, в котором ключи - имена, а значения - средний возраст
        Map<String, List<Persons>> personsAverageAge = personsList.stream().collect(Collectors.groupingBy(Persons::getName));

        HashMap<String, Double> mapNames = new HashMap<>();
        personsAverageAge.forEach((key, value) -> {
            double averageAge = 0;
            int countOfPersonsOfNames = 0;
            for (Persons persons : value) {
                averageAge += persons.getAge();
                countOfPersonsOfNames++;
            }
            averageAge = averageAge / countOfPersonsOfNames;
            mapNames.put(key, averageAge);
        });

        //Map<String, Double> persons3 = personsList.stream().collect(Collectors.toMap((Persons p) -> p.getName(), (Persons p) -> (double) p.getAge())); - пытался реализовать через лямбды - не смог
        //Map<String, Double> persons3 = personsList.stream().collect(Collectors.groupingBy((Persons p)->p.getName()).toMap()); - пытался реализовать через лямбды - не смог

        // Д. получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        List<Persons> persons4 = personsList.stream().filter(p -> p.getAge() <= 45 && p.getAge() >= 20).sorted(Comparator.comparingInt(Persons::getAge)).collect(Collectors.toList());
        persons4.forEach(e -> System.out.print(e.getName() + "=" + e.getAge() + " "));
    }
}
