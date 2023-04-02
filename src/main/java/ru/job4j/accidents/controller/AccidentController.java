package ru.job4j.accidents.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.model.Accident;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccidentController {

    private final AccidentService accidentService;

    @GetMapping("/addAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("user", "Admin");
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/formEditAccident")
    public String editAccident(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", "Admin");
        model.addAttribute("accident", accidentService.findById(id));
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/";
    }
}
