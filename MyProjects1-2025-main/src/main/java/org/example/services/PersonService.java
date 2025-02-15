package org.example.services;

import org.example.model.Person;
import org.example.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // теперь не надо у каждого метода делать транзакцию, спринг сам делает
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // найти всех
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    // получаем одного
    public Person findById(int id) {
        return personRepository.findById(id).orElse(null);
    }


    public void save(Person person) {
        personRepository.save(person);
    }

    public void update(int id, Person updatedPerson) {
        Person person = personRepository.findById(id).orElse(null);

        if (person != null){
            person.setName(updatedPerson.getName());
            person.setAge(updatedPerson.getAge());
            person.setEmail(updatedPerson.getEmail());
            personRepository.save(person);
        }

    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }

}
