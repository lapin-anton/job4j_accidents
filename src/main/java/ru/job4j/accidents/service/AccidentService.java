package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void create(Accident accident, HttpServletRequest req) {
        setRules(accident, req);
        accidentMem.add(accident);
    }

    public void update(Accident accident, HttpServletRequest req) {
        setRules(accident, req);
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

    private Set<Rule> findAllByStrArray(String[] ids) {
        return accidentMem.findAllByStrArray(ids);
    }

    private void setRules(Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> rules = findAllByStrArray(ids);
        accident.setRules(rules);
    }

}
