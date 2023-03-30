package ru.job4j.accidents.controller;

import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.model.Accident;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = accidentService.findAll();
        model.addAttribute("user", "Admin");
        model.addAttribute("accidents", accidents);
        return "index";
    }

}
