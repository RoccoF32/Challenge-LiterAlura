package com.challenge.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DTOAutor(
        String name,
        Integer birth_year,
        Integer death_year

) {
}
