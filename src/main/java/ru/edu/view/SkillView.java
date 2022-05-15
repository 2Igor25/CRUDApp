package ru.edu.view;

import ru.edu.controller.SkillController;
import ru.edu.model.Message;
import ru.edu.model.Skill;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SkillView {

    SkillController skillController;
    Scanner sc;

    private final String menu = "Скилы:\n"+
            "Выберете действие:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список скилов\n" +
            " 5. Выход";

    private final String printMenu = "Список скилов:\n" +
            "ID; NAME; DESCRIPTION";


    private final String editMenu = "Редактирование.\n" +
            Message.ID.getMessage();

    private final String deleteMenu = "Удаление\n" +
            Message.ID.getMessage();


    public SkillView (SkillController skillController, Scanner sc) {
        this.skillController = skillController;
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
            skillController.create(sc);
        } catch (Exception e) {
            System.out.println(Message.ERROR_INPUT.getMessage());
        }
    }

    public void edit() {
        System.out.println();
        System.out.println(editMenu);
        try {
            skillController.update(sc.nextLong(), sc);
        } catch (Exception e) {
            System.out.println(Message.ERROR_INPUT.getMessage());
        }
    }

    public void delete()
    {
        System.out.println(deleteMenu);
        Long id = sc.nextLong();
        try {
            skillController.delete(id);
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
        List<Skill> skills;
        try {
            skills = skillController.getAll();
        }
        catch (Exception e)
        {
            System.out.println(Message.ERROR.getMessage());
            return;
        }

        System.out.println(printMenu);
        Collections.sort(skills, Comparator.comparing(Skill::getId));
        if (skills.size() != 0) {
            for (Skill skill : skills) {
                System.out.println(skill.getId() + "; " + skill.getName() + "; " + skill.getDescriptionSkill()) ;
            }
        }
        else
        {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }

        System.out.println();
    }
}
