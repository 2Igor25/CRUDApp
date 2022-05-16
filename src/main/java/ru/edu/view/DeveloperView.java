package ru.edu.view;

import ru.edu.controller.DeveloperController;
import ru.edu.model.Developer;
import ru.edu.model.Message;
import ru.edu.model.Specialty;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {

    DeveloperController developerController;
    Scanner sc;

    private final String menu = "Разарботчик:\n"+
            "Выберете действие:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список специальностей\n" +
            " 5. Выход";

    private final String printMenu = "Список Разработчиков:\n" +
            "ID; FirstName; LastName; Specialty";

    private final String editMenu = "Редактирование.\n" +
            Message.ID.getMessage();

    private final String deleteMenu = "Удаление\n" +
            Message.ID.getMessage();

    public DeveloperView(DeveloperController developerController, Scanner sc) {
        this.developerController = developerController;
        this.sc = sc;
    }

    public void show () {

        boolean isExit = false;

        while (true) {

            System.out.println(menu);

            String response = sc.next();

            switch (response) {
                case "1":
                    create();
                    break;
                case "2":
                    edit();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    print();
                    break;
                case "5":
                    isExit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }

            if (isExit)
                break;
        }

    }

    public void create() {
        System.out.println();
        try {
           developerController.create(sc);
        } catch (Exception e) {
            System.out.println(Message.ERROR_INPUT.getMessage());
        }
    }

    public void edit() {

        try {
            if(developerController.getAll().isEmpty()) {
                System.out.println("Список разработчкиво пуст");
            } else {
                System.out.println();
                System.out.println(editMenu);
                developerController.update(sc.nextLong(), sc);
            }
        } catch (Exception e) {
            System.out.println(Message.ERROR_INPUT.getMessage());
        }
    }

    public void delete()
    {

        try {
            if(developerController.getAll().isEmpty()) {
                System.out.println("Список разработчкиво пуст");
            } else {
                System.out.println(deleteMenu);
                Long id = sc.nextLong();
                developerController.delete(id);
                System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
            }
        }
        catch (Exception e)
        {
            System.out.println(Message.ERROR.getMessage());
        }
        System.out.println();
    }

    public void print()
    {
        List<Developer> developers;
        try {
            developers = developerController.getAll();
        }
        catch (Exception e)
        {
            System.out.println(Message.ERROR.getMessage());
            return;
        }

        System.out.println(printMenu);
        Collections.sort(developers, Comparator.comparing(Developer::getId));
        if (developers.size() != 0) {
            for (Developer developer : developers) {
                System.out.println(developer.getId() + "; " + developer.getFirstName() + "; " + developer.getLastName() + "; " + developer.getSpecialty().getName()) ;
            }
        }
        else
        {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }

        System.out.println();
    }
}
