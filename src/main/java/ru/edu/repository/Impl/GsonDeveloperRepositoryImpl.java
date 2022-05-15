package ru.edu.repository.Impl;

import com.google.gson.Gson;
import ru.edu.model.Developer;
import ru.edu.model.Skill;
import ru.edu.repository.DeveloperRepository;

import java.util.List;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    Skill[] sourceArray = {};
    String jsonString = new Gson().toJson(sourceArray);

    @Override
    public Developer getById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public void add(Developer item) throws Exception {

    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public void update(Developer developer) throws Exception {

    }

    @Override
    public void save(Long id) {

    }

    @Override
    public List<Developer> getAll() throws Exception {
        return null;
    }
}
