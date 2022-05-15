package ru.edu.controller;

import ru.edu.model.Message;
import ru.edu.model.Skill;
import ru.edu.model.Specialty;
import ru.edu.repository.Impl.GsonSkillRepositoryImpl;
import ru.edu.repository.Impl.GsonSpecialtyRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class SpecialtyController {

    private GsonSpecialtyRepositoryImpl specialtyRep;

    public SpecialtyController(GsonSpecialtyRepositoryImpl specialtyRep) {
        this.specialtyRep = specialtyRep;
    }

    public List<Specialty> getAll() throws Exception {

        return specialtyRep.getAll();
    }

    public void create(Scanner sc) throws Exception {

        Specialty specialty = new Specialty();

        try {
            System.out.println("Введите id");
            specialty.setId(sc.nextLong());

            System.out.println("Введите название специальности");
            specialty.setName(sc.next());

            System.out.println("Введите описание");
            specialty.setDescriptionSpecialty(sc.next());

            specialtyRep.add(specialty);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
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
            Specialty specialty = specialtyRep.getById(id);

            System.out.println("Введите название специальности");
            specialty.setName(sc.next());

            System.out.println("Введите описание");
            specialty.setDescriptionSpecialty(sc.next());

            specialtyRep.update(specialty);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR.getMessage());
        }

        System.out.println();
    }

    public void delete(Long id) throws Exception {

       specialtyRep.delete(id);
    }
}
