package org.example.repositories;

import org.example.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

  //   Person findByEmail(String email);
     List<Person> findByEmail(@Param("email") String email); //для кастомного запрос jpa автоматом свяжет именованный запрос в сущности
     List<Person> findAll();

     // кастомный запрос через JPQL (Java Persistence Query Language)
     @Query("SELECT p FROM Person p WHERE p.age > :age") // :age - псевдоним , ниже параметр для него
     List<Person> findUsersOlderThan(@Param("age") int age);


}


// ПРИМЕР КАСТОМНЫХ ЗАПРОСОВ ДЛЯ ПРЕДЫДУЩЕГО ПРОЕКТА
// @Repository
//public interface BookRepository extends JpaRepository<Book, Integer> {
//
//     // Получение всех книг
//     List<Book> findAll();
//
//     // Получение книг, взятых конкретным человеком
//     List<Book> findByPersonId(int personId);
//
//     // Получение владельца книги по её ID
//     @Query("SELECT b.person FROM Book b WHERE b.id = :bookId")
//     Person findOwnerByBookId(@Param("bookId") int bookId);
//
//     // Получение конкретной книги по ID
//     Book findById(int id);
//
//     // Освобождение книги (установка person_id в NULL)
//     @Modifying
//     @Query("UPDATE Book b SET b.person = NULL WHERE b.id = :bookId")
//     void releaseBook(@Param("bookId") int bookId);
//
//     // Назначение книги человеку
//     @Modifying
//     @Query("UPDATE Book b SET b.person.id = :personId WHERE b.id = :bookId")
//     void assignBook(@Param("bookId") int bookId, @Param("personId") int personId);
//
//     // Удаление книги по ID
//     void deleteById(int id);
//}