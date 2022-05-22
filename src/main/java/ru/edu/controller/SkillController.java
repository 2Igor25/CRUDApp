package ru.edu.controller;

import ru.edu.model.Message;
import ru.edu.model.Skill;
import ru.edu.repository.SkillRepository;
import ru.edu.repository.impl.GsonSkillRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class SkillController {

    private final SkillRepository skillRep = new GsonSkillRepositoryImpl();


    public List<Skill> getAll() {
        return skillRep.getAll();
    }

    public Skill create(String name, String description) {

        Skill skill = new Skill();
        skill.setName(name);
        skill.setDescriptionSkill(description);

        return skillRep.add(skill);
    }

    public Skill getById(Long id) {
        return skillRep.getById(id);
    }

    public Skill update(Skill newSkill){
        return skillRep.update(newSkill);
    }

    public void delete(Long id) {
        skillRep.delete(id);
    }
}
