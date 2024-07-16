package com.challenge.LiterAlura.model;

import com.challenge.LiterAlura.dto.DTOAutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birth_year;
    private Integer death_year;

    @OneToOne
    @JoinTable(
            name = "Libro",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns =@JoinColumn(name = "id"))
    private Libro libros;

    public Autor(DTOAutor dtoAutor) {
        this.name = dtoAutor.name();
        this.birth_year = dtoAutor.birth_year();
        this.death_year = dtoAutor.death_year();
    }


}
