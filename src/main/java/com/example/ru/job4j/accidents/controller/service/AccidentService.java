package com.example.ru.job4j.accidents.controller.service;

import com.example.ru.job4j.accidents.model.Accident;
import com.example.ru.job4j.accidents.repository.AccidentMem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }
}
