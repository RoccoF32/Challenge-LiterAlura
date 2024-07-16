package com.challenge.LiterAlura.repository;

import com.challenge.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query(value = "SELECT * FROM libro WHERE '{en}' = ANY(string_to_array(languages, ','))",nativeQuery = true)
    List<Libro> findForLanguagesEN();
}
