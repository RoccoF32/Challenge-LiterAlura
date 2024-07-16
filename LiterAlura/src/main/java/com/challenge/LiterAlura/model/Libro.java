package com.challenge.LiterAlura.model;
import com.challenge.LiterAlura.dto.DTOAutor;
import com.challenge.LiterAlura.dto.DTOLIbro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToOne(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor authors;
//    @ElementCollection
    private List<String> languages;
    private Double download_count;

    public Libro(DTOLIbro dtolIbro) {
        this.title = dtolIbro.title();
        if (!dtolIbro.authors().isEmpty()){
            for (DTOAutor dtoAutor : dtolIbro.authors()){
                this.authors = new Autor(dtoAutor);
                break;
            }
        }
        this.languages = dtolIbro.languages();
        this.download_count = dtolIbro.download_count();
    }

}