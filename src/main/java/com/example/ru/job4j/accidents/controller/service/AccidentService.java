package com.example.ru.job4j.accidents.controller.service;

import com.example.ru.job4j.accidents.model.Accident;
import com.example.ru.job4j.accidents.repository.AccidentMem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll().entrySet()
                .stream().map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
