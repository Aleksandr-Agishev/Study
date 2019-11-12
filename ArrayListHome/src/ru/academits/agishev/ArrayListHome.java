package ru.academits.agishev;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> fileStrings = new ArrayList<>();
        try (FileReader reader = new FileReader("input.txt")) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                fileStrings.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("file not found");
        }
        System.out.println(fileStrings); //Вывел в консоль текст из файла

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = -9; i <= 51; i++) {
            list.add(i);
        }
        removeEvenNumbers(list); //Убрал четные значения
        System.out.println(list); //Вывел в консоль лист без четных чисел

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 4, 5, 7, 2, 4, 1, 8)); //Создал список с повторяющимися элементами
        ArrayList<Integer> list3 = getListWithoutRepetitions(list2); //Убрал все повторяющиеся элементы
        System.out.println(list3); //Распечатал для наглядности
    }

    private static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                //list.remove(i); // - старый кусочек кода.
                //list.set(i, null);// Фразу "- нужно удалить из списка именно числа, которые являются четными" я воспринял как "не удалять элемент, а едалить значение, то есть присвоить значению "null". Правильно?
                list.remove(i); // - в счетчик цикла внес исправление. Теперь удаляются не цисла с четным индексом а именно четные числа.
            }
        }
    }

    private static ArrayList<Integer> getListWithoutRepetitions(ArrayList<Integer> list) {
        ArrayList<Integer> listWithoutRepetitions = new ArrayList<>();

        for (Integer integer : list) {
            if (!listWithoutRepetitions.contains(integer)) {
                listWithoutRepetitions.add(integer);
            }
        }
        return listWithoutRepetitions;
    }
}

