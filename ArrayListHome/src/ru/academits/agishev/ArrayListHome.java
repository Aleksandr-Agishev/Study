package ru.academits.agishev;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        java.util.ArrayList<String> text = new java.util.ArrayList<>();
        try (FileReader reader = new FileReader("input.txt")) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                text.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("file not found");
        }
        System.out.println(text.toString()); //Вывел в консоль текст из файла

        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int i = 0; i <= 51; i++) {
            list.add(i);
        }
        removeEvenNumber(list); //Убрал четные значения
        System.out.println(list.toString()); //Вывел в консоль лист без четных чисел

        java.util.ArrayList<Integer> list2 = new java.util.ArrayList<>(Arrays.asList(1, 2, 3, 2, 4, 5, 7, 2, 4, 1, 8)); //Создал список с повторяющимися элементами
        java.util.ArrayList<Integer> list3 = getListWithoutRepetition(list2); //Убрал все повторяющиеся элементы
        System.out.println(list3.toString()); //Распечатал для наглядности
    }

    private static void removeEvenNumber(java.util.ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                list.remove(i);
            }
        }
    }

    private static java.util.ArrayList<Integer> getListWithoutRepetition(java.util.ArrayList<Integer> list) {
        if (list.isEmpty() || list.size() <= 1) {
            return list;
        }
        java.util.ArrayList<Integer> listWithoutRepetition = new java.util.ArrayList<>();
        for (Integer integer : list) {
            if (!listWithoutRepetition.contains(integer)) {
                listWithoutRepetition.add(integer);
            }
        }
        return listWithoutRepetition;
    }
}

