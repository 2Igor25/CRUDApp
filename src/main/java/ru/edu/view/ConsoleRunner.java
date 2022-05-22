package ru.edu.view;


import ru.edu.controller.DeveloperController;
import ru.edu.controller.SkillController;
import ru.edu.controller.SpecialtyController;
import ru.edu.model.Message;
import ru.edu.repository.impl.GsonDeveloperRepositoryImpl;
import ru.edu.repository.impl.GsonSkillRepositoryImpl;
import ru.edu.repository.impl.GsonSpecialtyRepositoryImpl;

import java.util.Scanner;

public class ConsoleRunner {

    private final String menu = "Введите цифру:\n" +
            "1. Скилы\n" +
            "2. Специальности\n" +
            "3. Разработчики\n" +
            "4. Выход";


    private Scanner cons = new Scanner(System.in);

    SkillView skillView = new SkillView();

    SpecialtyView specialtyView = new SpecialtyView();
    SpecialtyController specialtyContr = new SpecialtyController();
    SkillController skillContr = new SkillController();

    DeveloperView developerView = new DeveloperView(specialtyView, specialtyContr, skillContr);


    public void start()  {

        boolean isExit = false;

        while (true) {
            System.out.println(menu);
            String response = cons.next();
            switch (response) {
                case "1":
                    skillView.show();
                    break;
                case "2":
                    specialtyView.show();
                    break;
                case "3":
                    developerView.show();
                    break;
                case "4":
                    isExit = true;
                    System.out.println("Пока!");
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }

            if (isExit)
                break;
        }

    }
}
