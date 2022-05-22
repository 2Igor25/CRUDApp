package ru.edu.controller;

import ru.edu.model.Message;
import ru.edu.model.Specialty;
import ru.edu.repository.SpecialtyRepository;
import ru.edu.repository.impl.GsonSpecialtyRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class SpecialtyController {

    private SpecialtyRepository specialtyRep = new GsonSpecialtyRepositoryImpl();

   public List<Specialty> getAll() {

        return specialtyRep.getAll();
    }

    public Specialty getById(Long id) {
        return  specialtyRep.getById(id);
    }

    public Specialty create(String name, String description) {

        Specialty specialty = new Specialty();
        specialty.setName(name);
        specialty.setDescriptionSpecialty(description);

        return specialtyRep.add(specialty);
    }

    public Specialty update(Specialty newSpecialty) {
            return specialtyRep.update(newSpecialty);
    }

    public void delete(Long id) {
            specialtyRep.delete(id);
    }
}
