package ru.edu.repository.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.edu.model.Developer;
import ru.edu.model.Skill;
import ru.edu.model.Specialty;
import ru.edu.repository.DeveloperRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    List<String> developers = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    public Developer getById(Long id) throws Exception {

        for (String developer: developers) {
            Developer developerById = new Gson().fromJson(developer, Developer.class);
            if (developerById.getId() == id) {
                return  developerById;
            }
        }
        return null;
    }

    @Override
    public void add(Developer item) throws Exception {
        developers.add(gson.toJson(item));
    }

    @Override
    public void delete(Long id) throws Exception {
        for (String developer: developers) {
            Developer developerById = new Gson().fromJson(developer, Developer.class);
            if (developerById.getId() == id) {
                developers.remove(developer);
                break;
            }
        }
    }

    @Override
    public void update(Developer updateDeveloper) throws Exception {
        for (String developer: developers) {
            Developer specialtyById = new Gson().fromJson(developer, Developer.class);
            if(specialtyById.getId() == updateDeveloper.getId()) {
                developers.remove(developer);
                developers.add(gson.toJson(updateDeveloper));
                break;
            }
        }
    }

    @Override
    public void save(Long id) {

    }

    @Override
    public List<Developer> getAll() throws Exception {
        String json = developers.toString();
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> list = new Gson().fromJson(json, targetClassType);
        return list;
    }
}
