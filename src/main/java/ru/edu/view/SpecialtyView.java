package ru.edu.view;

import ru.edu.controller.SpecialtyController;
import ru.edu.model.Message;
import ru.edu.model.Skill;
import ru.edu.model.Specialty;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SpecialtyView {

    SpecialtyController specialtyController;
    Scanner sc;

    private final String menu = "Специальность:\n"+
            "Выберете действие:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список скилов\n" +
            " 5. Выход";

    private final String printMenu = "Список специальностей:\n" +
            "ID; NAME; DESCRIPTION";

    private final String editMenu = "Редактирование.\n" +
            Message.ID.getMessage();

    private final String deleteMenu = "Удаление\n" +
            Message.ID.getMessage();

    public SpecialtyView(SpecialtyController specialtyController, Scanner sc) {
        this.specialtyController = specialtyController;
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
            specialtyController.create(sc);
        } catch (Exception e) {
            System.out.println(Message.ERROR_INPUT.getMessage());
        }
    }

    public void edit() {
        System.out.println();
        System.out.println(editMenu);
        try {
            specialtyController.update(sc.nextLong(), sc);
        } catch (Exception e) {
            System.out.println(Message.ERROR_INPUT.getMessage());
        }
    }

    public void delete()
    {
        System.out.println(deleteMenu);
        Long id = sc.nextLong();
        try {
            specialtyController.delete(id);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(Message.ERROR.getMessage());
        }
        System.out.println();
    }

    public void print()
    {
        List<Specialty> specialties;
        try {
            specialties = specialtyController.getAll();
        }
        catch (Exception e)
        {
            System.out.println(Message.ERROR.getMessage());
            return;
        }

        System.out.println(printMenu);
        Collections.sort(specialties, Comparator.comparing(Specialty::getId));
        if (specialties.size() != 0) {
            for (Specialty specialty : specialties) {
                System.out.println(specialty.getId() + "; " + specialty.getName() + "; " + specialty.getDescriptionSpecialty()) ;
            }
        }
        else
        {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }

        System.out.println();
    }
}
