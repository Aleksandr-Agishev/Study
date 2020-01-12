package ru.academits.agishev.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaTest {
    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(new Person("Иван", 15),
                new Person("Степан", 25),
                new Person("Петр", 27),
                new Person("Афросий", 18),
                new Person("Ерёма", 43),
                new Person("Евклид", 155),
                new Person("Профокл", 15),
                new Person("Кузьма", 10),
                new Person("Фома", 64),
                new Person("Иван", 4),
                new Person("Степан", 15),
                new Person("Ерёма", 41));

        // А. получить список уникальных имен
        List<String> distinctNamesList = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        // Б. вывести список уникальных имен в формате Имена: Иван, Петр, Сергей
        System.out.println(String.join(", ", distinctNamesList) + ".");

        // В. получить список людей младше 18 лет, посчитать для них средний возраст
        List<Person> youngPersonsList = personsList.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());
        youngPersonsList.forEach(x -> System.out.print(x.getName() + "=" + x.getAge() + ", "));
        System.out.println();

        int sumAge = personsList.stream()
                .filter(p -> p.getAge() < 18)
                .mapToInt(Person::getAge)
                .sum();

        double countAge = personsList.stream() // при указании int и применении mapToInt почему то выдает ошибку
                .filter(p -> p.getAge() < 18)
                .mapToInt(Person::getAge)
                .count(); //Попытка взять не count а average выдает ошибку при любой комбинации countAge (int и double) и mapToInt и mapToDouble

        double averageAge = sumAge / countAge;
        System.out.println("averageAge Age = " + averageAge);

        // Г. при помощи группировки получить Мар, в котором ключи - имена, а значения - средний возраст
        Map<String, Double> personsAverageAge = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        distinctNamesList.forEach(e -> System.out.print(e + "=" + personsAverageAge.get(e) + ", "));
        System.out.println();

        // Д. получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        List<Person> personsListFrom20To45 = personsList.stream()
                .filter(p -> p.getAge() <= 45 && p.getAge() >= 20)
                .sorted(Comparator.comparingInt(Person::getAge))//(p1,p2)->p2.getAge()-p1.getAge()
                .collect(Collectors.toList());

        personsListFrom20To45.forEach(e -> System.out.print(e.getName() + "=" + e.getAge() + " "));
    }
}
