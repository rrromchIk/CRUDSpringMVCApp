package com.rom4ik.controller;

import com.rom4ik.dao.TestDAO;
import com.rom4ik.model.Test;
import com.rom4ik.validator.TestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author rom4ik
 */
@Controller
@RequestMapping("/tests")
public class TestsController {
    private final TestDAO testDAO;
    private final TestValidator testValidator;

    @Autowired
    public TestsController(TestDAO testDAO, TestValidator testValidator) {
        this.testDAO = testDAO;
        this.testValidator = testValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("tests", testDAO.getAll());
        return "/tests/index";
    }

    @GetMapping("/{id}")
    public String showTest(@PathVariable int id, Model model) {
        model.addAttribute("tests", testDAO.getById(id));
        return "/tests/index";
    }

    @GetMapping("/new")
    public String newTest(@ModelAttribute("test") Test test) {
        return "tests/new";
    }

    @GetMapping("/{id}/edit")
    public String editTest(Model model, @PathVariable int id) {
        model.addAttribute("test", testDAO.getById(id));
        return "tests/edit";
    }

    @PostMapping()
    public String createTest(@ModelAttribute("test") @Valid Test test,
                             BindingResult bindingResult) {
        testValidator.validate(test, bindingResult);

        if(bindingResult.hasErrors()) {
            return "/tests/new";
        }

        testDAO.addTest(test);
        return "redirect:tests";
    }

    @PatchMapping("/{id}")
    public String updateTest(@ModelAttribute("test") @Valid Test test,
                             BindingResult bindingResult,
                            @PathVariable("id") int id) {
        testValidator.validate(test, bindingResult);
        if(bindingResult.hasErrors()) {
            return "tests/edit";
        }
        testDAO.update(test, id);
        return "redirect:/tests";
    }

    @DeleteMapping("/{id}")
    public String deleteTest(@PathVariable("id") int id) {
        testDAO.delete(id);
        return "redirect:/tests";
    }
}
