package ru.edu.controller;

import ru.edu.model.Message;
import ru.edu.model.Skill;
import ru.edu.repository.Impl.GsonSkillRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class SkillController {

    private GsonSkillRepositoryImpl skillRep;

    public SkillController(GsonSkillRepositoryImpl skillRep) {
        this.skillRep = skillRep;
    }

    public List<Skill> getAll() throws Exception {

        return skillRep.getAll();
    }

    public void create(Scanner sc) throws Exception {

        Skill skill = new Skill();

        try {
            System.out.println("Введите id");
            skill.setId(sc.nextLong());

            System.out.println("Введите название скила");
            skill.setName(sc.next());

            System.out.println("Введите описание");
            skill.setDescriptionSkill(sc.next());

            skillRep.add(skill);
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
                Skill skill = skillRep.getById(id);

                System.out.println("Введите название скила");
                skill.setName(sc.next());

                System.out.println("Введите описание");
                skill.setDescriptionSkill(sc.next());

                skillRep.update(skill);
                System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(Message.ERROR.getMessage());
            }

            System.out.println();

    }

    public void delete(Long id) throws Exception {
            skillRep.delete(id);
    }
}
