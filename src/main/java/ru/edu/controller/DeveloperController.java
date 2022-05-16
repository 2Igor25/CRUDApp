package ru.edu.controller;

import ru.edu.model.Developer;
import ru.edu.model.Message;
import ru.edu.model.Skill;
import ru.edu.repository.Impl.GsonDeveloperRepositoryImpl;
import ru.edu.repository.Impl.GsonSpecialtyRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class DeveloperController {

    private GsonDeveloperRepositoryImpl developerRep;
    private GsonSpecialtyRepositoryImpl specialtyRep;

    public DeveloperController(GsonDeveloperRepositoryImpl developerRep, GsonSpecialtyRepositoryImpl specialtyRep) {
        this.developerRep = developerRep;
        this.specialtyRep = specialtyRep;
    }

    public List<Developer> getAll() throws Exception {

        return developerRep.getAll();
    }

    public void create(Scanner sc) throws Exception {

        Developer developer = new Developer();

        try {
            System.out.println("Введите id");
            developer.setId(sc.nextLong());

            System.out.println("Введите имя разработчика");
            developer.setFirstName(sc.next());

            System.out.println("Введите фамилию разработчика");
            developer.setLastName(sc.next());

            System.out.println("Введите ID специальности");

            Long id = Long.valueOf(sc.next());

            if (specialtyRep.getById(id) != null){
                developer.setSpecialty(specialtyRep.getById(id));
                System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
            } else {
                throw new Exception("Специальности с таким ID не найдено!\n" +
                        "Повторите попытку");
            }

            developerRep.add(developer);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR.getMessage());
        }
        System.out.println();
    }

    public void update(Long id, Scanner sc) throws Exception {

            try {
                Developer developer = developerRep.getById(id);

                System.out.println("Введите имя разработчика");
                developer.setFirstName(sc.next());

                System.out.println("Введите фамилию разработчика");
                developer.setLastName(sc.next());

                developerRep.update(developer);
                System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(Message.ERROR.getMessage());
            }

            System.out.println();
    }

    public void delete(Long id) throws Exception {
            developerRep.delete(id);
    }
}
