package ru.edu.view;


import ru.edu.controller.SkillController;
import ru.edu.model.Message;
import ru.edu.repository.Impl.GsonSkillRepositoryImpl;
import java.util.Scanner;

public class ConsoleRunner {

    private final String menu = "Введите цифру:\n" +
            "1. Скилы\n" +
            "2. Выход";


    private Scanner cons = new Scanner(System.in);

    GsonSkillRepositoryImpl skillRep = new GsonSkillRepositoryImpl();
    SkillController skillController = new SkillController(skillRep);
    SkillView skillView = new SkillView(skillController, cons);


    public void start()  {
        System.out.println(menu);
        boolean isExit = false;
            String response = cons.next();

            switch (response)
            {
                case "1":
                    skillView.show();
                    cons.close();
                    break;
                case "2":
                    System.out.println(menu);
                    cons.close();
                    isExit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }

    }
}
