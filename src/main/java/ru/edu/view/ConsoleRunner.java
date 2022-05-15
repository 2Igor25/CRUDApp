package ru.edu.view;


import ru.edu.controller.SkillController;
import ru.edu.controller.SpecialtyController;
import ru.edu.model.Message;
import ru.edu.repository.Impl.GsonSkillRepositoryImpl;
import ru.edu.repository.Impl.GsonSpecialtyRepositoryImpl;

import java.util.Scanner;

public class ConsoleRunner {

    private final String menu = "Введите цифру:\n" +
            "1. Скилы\n" +
            "2. Специальности\n" +
            "2. Выход";


    private Scanner cons = new Scanner(System.in);

    GsonSkillRepositoryImpl skillRep = new GsonSkillRepositoryImpl();
    SkillController skillController = new SkillController(skillRep);
    SkillView skillView = new SkillView(skillController, cons);

    GsonSpecialtyRepositoryImpl specialtyRep = new GsonSpecialtyRepositoryImpl();
    SpecialtyController specialtyController = new SpecialtyController(specialtyRep);
    SpecialtyView specialtyView = new SpecialtyView(specialtyController, cons);

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
}
