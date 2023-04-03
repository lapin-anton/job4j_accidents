package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void create(Accident accident) {
        accidentMem.add(accident);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }

    public Accident findById(Integer id) {
        return accidentMem.findById(id);
    }

    public List<AccidentType> findAllTypes() {
        return accidentMem.findAllTypes();
    }

    public List<Rule> findAllRules() {
        return accidentMem.findAllRules();
    }

    public Set<Rule> findAllByStrArray(String[] ids) {
        return accidentMem.findAllByStrArray(ids);
    }
}
