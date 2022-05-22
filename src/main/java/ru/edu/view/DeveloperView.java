package ru.edu.view;

import ru.edu.controller.DeveloperController;
import ru.edu.controller.SkillController;
import ru.edu.controller.SpecialtyController;
import ru.edu.model.Developer;
import ru.edu.model.Message;
import ru.edu.model.Skill;
import ru.edu.model.Specialty;

import java.util.*;

public class DeveloperView {

    private final DeveloperController developerController = new DeveloperController();
    private final SpecialtyView specialtyView;
    private final SpecialtyController specContr;
    private final SkillView skillView = new SkillView();
    private final SkillController skillCont;
    private final Scanner sc = new Scanner(System.in);

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

    public DeveloperView(SpecialtyView specialtyView, SpecialtyController specContr, SkillController skillCont) {
        this.specialtyView = specialtyView;
        this.specContr = specContr;
        this.skillCont = skillCont;
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
        String firstName;
        String lastName;
        Specialty specialty;
        List<Skill> skills;


        try {
            System.out.println("Введите имя разработчика:");
            firstName = sc.next();

            System.out.println("Введите фамилию разработчика:");
            lastName = sc.next();

            System.out.println("Выберите id специальности: ");
            specialty = specialtyId();

            System.out.println("Выбирите скилы: ");
            skills = chooseSkills();

           developerController.create(firstName, lastName, specialty, skills);
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
                Long id = sc.nextLong();

                Developer developer = new Developer();
                developer.setId(developerController.getById(id).getId());
                System.out.println("Введите имя разработчика:");
                developer.setFirstName(sc.next());

                System.out.println("Введите фамилию разработчика:");
                developer.setLastName(sc.next());

                System.out.println("Выберите id специальности: ");
                developer.setSpecialty(specialtyId());

                System.out.println("Выбирите скилы: ");
                developer.setSkills(chooseSkills());

                developerController.update(developer);
                System.out.println(Message.SUCCESSFUL_OPERATION);
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
                System.out.println(developer.getId() + "; " + developer.getFirstName() + "; " + developer.getLastName() + "; " + developer.getSpecialty().getName() + developer.getSkills().toString()) ;
            }
        }
        else
        {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }

        System.out.println();
    }

    private Specialty specialtyId() {

        if(specContr.getAll().isEmpty()){
            System.out.println("Специальности не созданы");
            return null;
        }
        Long specId;
        while (true) {
            specialtyView.print();
            System.out.println(Message.ID.getMessage());
            try {
                specId = sc.nextLong();
                if (specContr.getById(specId) == null) {
                    throw new Exception("Специальности с таким ID нет");
                }
                break;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                continue;
            }
        }
        return specContr.getById(specId);
    }

    private List<Skill> chooseSkills() {

        List<Skill> skills = new ArrayList<>();

        if (skillCont.getAll().isEmpty()) {
            System.out.println("Список пуст");
            return skills;
        }

        while (true) {
            skillView.print();
            System.out.println(Message.ID.getMessage());
            try {
               Long skillId = sc.nextLong();
               if (skillCont.getById(skillId) == null) {
                   throw  new Exception("Не верный ID");
               }
               else {
                   skills.add(skillCont.getById(skillId));
               }

                System.out.println("Добавить еще скилов: \n" +
                        "1. Да\n" +
                        "2. Нет");
                Long response = sc.nextLong();
                if (response == 2) {
                    break;
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                continue;
            }


        }
        return skills;
    }
}
