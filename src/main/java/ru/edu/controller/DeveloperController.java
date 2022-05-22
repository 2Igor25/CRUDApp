package ru.edu.controller;

import ru.edu.model.Developer;
import ru.edu.model.Message;
import ru.edu.model.Skill;
import ru.edu.model.Specialty;
import ru.edu.repository.DeveloperRepository;
import ru.edu.repository.impl.GsonDeveloperRepositoryImpl;
import ru.edu.repository.impl.GsonSkillRepositoryImpl;
import ru.edu.repository.impl.GsonSpecialtyRepositoryImpl;
import ru.edu.view.SkillView;
import ru.edu.view.SpecialtyView;

import java.util.*;

public class DeveloperController {

    private DeveloperRepository developerRep = new GsonDeveloperRepositoryImpl();


    public List<Developer> getAll() throws Exception {

        return developerRep.getAll();
    }

    public Developer create(String firstname, String lastName, Specialty specialty, List<Skill> skills) {
        Developer developer = new Developer();
        developer.setFirstName(firstname);
        developer.setLastName(lastName);
        developer.setSpecialty(specialty);
        developer.setSkills(skills);

        return developer;
    }

    public  Developer getById(Long id) {
        return  developerRep.getById(id);
    }

    public Developer update(Developer newDeveloper)  {
            return developerRep.update(newDeveloper);
    }

    public void delete(Long id) throws Exception {
            developerRep.delete(id);
    }
}
