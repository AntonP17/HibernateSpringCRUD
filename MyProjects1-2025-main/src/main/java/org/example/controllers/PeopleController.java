package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.PersonDAO;
import org.example.model.Person;
import org.example.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }


    // гет запрос для отображения людей
    @GetMapping()
    public String index(Model model) {
        // получим вех людей из сервиса и передаем в представление
        model.addAttribute("people", personService.getAllPerson());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получаем 1 чела по id из сервиса и передаем в представление
        model.addAttribute("person", personService.findById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        // тут еще можно исопьзовать вторую форму записи @ModelAttribute "person" Person person
        //  будет тоже самое что и внизу , только она автоматически добавится спрингом

        model.addAttribute("person", new Person());

        return "people/new";
    }

    @PostMapping() // ручной вариант данного метод в файле text1
    public String create(@ModelAttribute("person") @Valid Person person,  // добавим валидацию для полей класса Person
                         BindingResult bindingResult) { // так как могут быть ошибки то они должны хранится в BindingResult

        if (bindingResult.hasErrors())
            return "people/new";

        personService.save(person);
        return "redirect:/people";

    }

    // форма для редактирования человека по id
    // обязательно прописывать аннотацию @PathVariable потмоу что мы конкретного человека по id меняем
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id) {

        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }
    // тут естественно Path запрос для редактирование (как POst только тут не добавляет человека в спикок а меняет уже существующего человека)
    // чтобы каждый раз не путатся с моделью , надо понять механизм передачи в модель ключ-значение
    // тут пример ключ "person" - значение обьект класса Person
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                        BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "people/edit";

        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}


