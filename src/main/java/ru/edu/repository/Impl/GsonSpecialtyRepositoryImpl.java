package ru.edu.repository.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.edu.model.Skill;
import ru.edu.model.Specialty;
import ru.edu.repository.SpecialtyRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    List<String> specialties = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    public Specialty getById(Long id) throws Exception {

        for (String specialty: specialties) {
            Specialty specialtyById = new Gson().fromJson(specialty, Specialty.class);
            if (specialtyById.getId() == id) {
                return  specialtyById;
            }
        }
        return null;
    }

    @Override
    public void add(Specialty item) {
        specialties.add(gson.toJson(item));
    }

    @Override
    public void delete(Long id) throws Exception {
        for (String specialty: specialties) {
            Specialty specialtyById = new Gson().fromJson(specialty, Specialty.class);
            if(specialtyById.getId() == id) {
                specialties.remove(specialty);
                break;
            }
        }
    }

    @Override
    public void update(Specialty updateSpecialty) throws Exception {
        for (String specialty: specialties) {
            Specialty specialtyById = new Gson().fromJson(specialty, Specialty.class);
            if(specialtyById.getId() == updateSpecialty.getId()) {
                specialties.remove(specialty);
                specialties.add(gson.toJson(updateSpecialty));
                break;
            }
        }
    }

    @Override
    public void save(Long aLong) {

    }


    @Override
    public List<Specialty> getAll() throws Exception {
        String json = specialties.toString();
        Type targetClassType = new TypeToken<ArrayList<Specialty>>() { }.getType();
        List<Specialty> list = new Gson().fromJson(json, targetClassType);
        return list;
    }
}
