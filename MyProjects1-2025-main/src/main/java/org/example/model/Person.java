package org.example.model;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "Person")
@NamedQuery( // кастомный запрос к БД используем в репозитории ->
        name = "User.findByEmail",
        query = "SELECT u FROM Person u WHERE u.email = :email"
)
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2, max = 30 , message = "Name should be between 2 and 30 characters")
    @Column(name = "name", nullable = false, length = 30)
    private String name;

//    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "age", nullable = false)
    private int age;

//    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public Person() {}

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
