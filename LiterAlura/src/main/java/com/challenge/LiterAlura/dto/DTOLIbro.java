package com.challenge.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DTOLIbro(
        String title,
        List<DTOAutor> authors,
        List<String> languages,
        Double download_count
) {

}
