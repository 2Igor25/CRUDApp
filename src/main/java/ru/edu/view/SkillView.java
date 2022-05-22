package ru.edu.view;

import ru.edu.controller.SkillController;
import ru.edu.model.Message;
import ru.edu.model.Skill;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SkillView {

    private final SkillController skillController = new SkillController();
    private final Scanner sc = new Scanner(System.in);

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
        String name;
        String description;

        try {
            System.out.println("Введите названия скила");
            name = sc.next();

            System.out.println("Ведите описание");
            description = sc.next();

            Skill newSkill = skillController.create(name, description);
            System.out.println(Message.SUCCESSFUL_OPERATION);
            System.out.println("Добавлен скил\n"
                    + newSkill.toString());

        } catch (Exception e) {
            System.out.println(Message.ERROR_INPUT.getMessage());
        }
    }

    public void edit() {

        Long id;
        Skill newSkill = new Skill();

        try {
            if(skillController.getAll().isEmpty()) {
                System.out.println("Список скилов пуст");
            } else {
                print();

                System.out.println();
                System.out.println(editMenu);
                id = sc.nextLong();

                if (skillController.getById(id)!=null) {
                    System.out.println("Скилл на редактирование:\n"
                            + skillController.getById(id));

                    newSkill.setId(id);
                } else {
                    throw new Exception("Скилл с таким ID не найден");
                }


                System.out.println("Введите новое название скила: ");
                newSkill.setName(sc.next());

                System.out.println("Введите новое описание: ");
                newSkill.setDescriptionSkill(sc.next());

                Skill printSkill = skillController.update(newSkill);
                System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
                System.out.println(printSkill.toString());
            }
        } catch (Exception e) {
            System.out.println(Message.ERROR_INPUT.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void delete()
    {
        try {
            if(skillController.getAll().isEmpty()) {
                System.out.println("Список скилов пуст");
            } else {
                System.out.println(deleteMenu);
                Long id = sc.nextLong();
                skillController.delete(id);
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
