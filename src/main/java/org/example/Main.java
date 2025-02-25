package org.example;

// Взаимодействие с пользователем
// Scanner; ввод/вывод
// while(true)
// switch-case
// List; Map

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static final String NAME = "name";
    static final String DESC = "desc";
    static final String STATUS = "status";


    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Менеджер Задач!");

        // [1, 2, 5, 2, 1, 2, 1, 0]
        // ["привет", "как", "жизнь", "друзья"]
        // {
        //   "name": "Моя задача",
        //   "desc": "Моя самая первая задача",
        //   "status": "OPEN",
        // }
/*
[
     {
       "name": "Моя задача",
       "desc": "Моя самая первая задача",
       "status": "OPEN",
    },
    {
       "name": "Моя задача номер 2",
       "desc": "Моя самая вторая задача",
       "status": "IN_PROGRESS",
    },
]
 */
        List<Map<String, String>> tasks = new ArrayList<>();

        while (true) {

            System.out.println("1 - Вывести задачи");
            System.out.println("2 - Добавить задачу");
            System.out.println("3 - Изменить статус");
            System.out.println("0 - Выход");
            System.out.print("> ");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> {
                    System.out.println("Все задачи");
                    if (tasks.isEmpty()) {
                        System.out.println("Задачи отсутствуют");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            Map<String, String> task = tasks.get(i);
                            System.out.println("№ " + (i + 1));
                            System.out.println("Имя: " + task.get(NAME));
                            System.out.println("Описание: " + task.get(DESC));
                            System.out.println("Статус: " + task.get(STATUS));
                        }
                    }
                }
                case "2" -> {
                    System.out.println("Создание задачи");
                    Map<String, String> task = new HashMap<>();

                    // name: Моя задача номер 1
                    System.out.print("Введите имя задачи: ");
                    String taskName = scanner.nextLine();
                    task.put(NAME, taskName);

                    // desc: Подробное описание моей задачи
                    System.out.print("Введите описание задачи: ");
                    String taskDesc = scanner.nextLine();
                    task.put(DESC, taskDesc);

                    // status: OPEN
                    System.out.print("Введите статус задачи: ");
                    String taskStatus = scanner.nextLine();
                    task.put(STATUS, taskStatus);
                    tasks.add(task);
                }
                case "3" -> {
                    System.out.println("Обновление задачи");
                    if (tasks.isEmpty()) {
                        System.out.println("Задачи отсутствуют");
                    } else {
                        System.out.print("Введите номер задачи для обновления: ");
                        int taskId = scanner.nextInt();
                        scanner.nextLine();
                        Map<String, String> task = tasks.get(taskId - 1);
                        System.out.print("Введите статус задачи: ");
                        String taskStatus = scanner.nextLine();
                        task.put(STATUS, taskStatus);
                    }
                }
                case "0" -> {
                    System.out.println("Выход");
                    return;
                }
                default -> System.out.println("Команда не поддерживается");
            }
        }

    }
}