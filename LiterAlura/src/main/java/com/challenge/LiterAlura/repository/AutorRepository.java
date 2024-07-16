package com.challenge.LiterAlura.repository;

import com.challenge.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE :year between a.birth_year AND a.death_year")
    List<Autor> findForYear(int year);
}
