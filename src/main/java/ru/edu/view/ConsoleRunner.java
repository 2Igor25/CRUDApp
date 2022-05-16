package ru.edu.view;


import ru.edu.controller.DeveloperController;
import ru.edu.controller.SkillController;
import ru.edu.controller.SpecialtyController;
import ru.edu.model.Message;
import ru.edu.repository.Impl.GsonDeveloperRepositoryImpl;
import ru.edu.repository.Impl.GsonSkillRepositoryImpl;
import ru.edu.repository.Impl.GsonSpecialtyRepositoryImpl;

import java.util.Scanner;

public class ConsoleRunner {

    private final String menu = "Введите цифру:\n" +
            "1. Скилы\n" +
            "2. Специальности\n" +
            "3. Разработчики\n" +
            "4. Выход";


    private Scanner cons = new Scanner(System.in);

    GsonSkillRepositoryImpl skillRep = new GsonSkillRepositoryImpl();
    SkillController skillController = new SkillController(skillRep);
    SkillView skillView = new SkillView(skillController, cons);

    GsonSpecialtyRepositoryImpl specialtyRep = new GsonSpecialtyRepositoryImpl();
    SpecialtyController specialtyController = new SpecialtyController(specialtyRep);
    SpecialtyView specialtyView = new SpecialtyView(specialtyController, cons);

    GsonDeveloperRepositoryImpl developerRep = new GsonDeveloperRepositoryImpl();
    DeveloperController developerController = new DeveloperController(developerRep, specialtyRep);
    DeveloperView developerView = new DeveloperView(developerController, cons);


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
