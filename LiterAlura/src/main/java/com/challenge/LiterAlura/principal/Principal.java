package com.challenge.LiterAlura.principal;

import com.challenge.LiterAlura.Service.ConsumoAPI;
import com.challenge.LiterAlura.Service.ConvierteDatos;
import com.challenge.LiterAlura.dto.DTOResultados;
import com.challenge.LiterAlura.model.Autor;
import com.challenge.LiterAlura.model.Libro;
import com.challenge.LiterAlura.repository.AutorRepository;
import com.challenge.LiterAlura.repository.LibroRepository;


import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private final String URL_BASE = "https://gutendex.com/books?";
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menu() {
        var opcion = -1;

        while (opcion != 0) {
            var menu = """
                    ++++++++++++++
                    Bienvenido a ListerAlura
                    ++++++++++++++
                    1-Buscar por titulo de libro
                    2-Listar Libros registrados
                    3-Listar Autores Registrados
                    4-Listar autores vivos por año
                    5-listar libros por idioma
                    
                    9- Salir
                    """;

            System.out.println(menu);
            var opcionBuscar = teclado.nextInt();
            teclado.nextLine();


            switch (opcionBuscar){
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    registroDeLibros();
                    break;
                case 3:
                    registroDeAutores();
                    break;
                case 4:
                    busquedaPorNacimientoDeAutor();
                    break;
                case 5:
                    busquedaPorLenguaje();
                    break;
                case 9:
                    System.out.println("Gracias por utilizar LiterAlura");
                    opcion =0;
                    break;
                default:
                    System.out.println("Error al ingresar los datos");

            }

        }


    }

    private void busquedaPorLenguaje() {
        System.out.println("Ahora vamos a buscar los titulos por lengua.");
        System.out.println("Selecciona entre es(espanol) o en(ingles)");
        var language = teclado.nextLine();

        List<Libro> libros = libroRepository.findForLanguagesEN();

        if ((!libros.isEmpty())) {

            for (Libro libro : libros) {
                System.out.println("Titulo: " + libro.getTitle());
            }
        }else{
            System.out.println("Error al ingresar el idioma");
            }
    }

    private void busquedaPorNacimientoDeAutor() {

        System.out.println("""
                Esta opcion lista los libros en nuestra base de datos segun si los autores estaban vivos o no.
                Introduce el año que desees.
                """);
        var year = teclado.nextInt();

        List<Autor> autoresVivos = autorRepository.findForYear(year);

        if (!autoresVivos.isEmpty()){
            for (Autor autor:autoresVivos){
                System.out.println("Nombre: " + autor.getName());
                System.out.println("Vivio desde: " + autor.getBirth_year()+" hasta: "+ autor.getDeath_year());
                System.out.println("Sus libros: "+ autor.getLibros().getTitle());
                System.out.println("******************");

            }
        }else {
            System.out.println("El no habia nacido o estaba muerto en esa fecha");
        }
    }

    private void registroDeAutores() {
        List<Autor> listaDeAutores = autorRepository.findAll();

        if (!listaDeAutores.isEmpty()){
            for (Autor autor:listaDeAutores){
                System.out.println("Nombre: " + autor.getName());
                System.out.println("Vivio desde: " + autor.getBirth_year()+" hasta: "+ autor.getDeath_year());
                System.out.println("Sus libros: "+ autor.getLibros().getTitle());
                System.out.println("******************");

            }
        }

    }


    private void registroDeLibros() {
        List<Libro> listaLibros = libroRepository.findAll();

        if ((!listaLibros.isEmpty())){

            for (Libro libro:listaLibros){
                System.out.println("Titulo: " + libro.getTitle());
                System.out.println("Autor: " + libro.getAuthors().getName() );
                System.out.println("Numero de Descargas: "+ libro.getDownload_count());
                System.out.println("******************");

            }

        }else {
            System.out.println("No hay coincidencias");
        }
    }


    private void detalles( Libro libro){
        System.out.println("Autor: " + libro.getAuthors().getName() );
        System.out.println("Numero de Descargas: "+ libro.getDownload_count());
        System.out.println("******************");
    }


    private DTOResultados getDatos(){
        var url = "https://gutendex.com/books/?search=";
        System.out.println("Escribe el nombre del libro: ");
        var titulo = teclado.nextLine();
        System.out.println("Titulo: " + titulo);
        var json = consumoAPI.obtenerDatos(url + titulo.replace(" ","%20"));
        System.out.println(json);
        return convierteDatos.obtenerDatos(json, DTOResultados.class);
    }

    private void buscarPorTitulo() {

            DTOResultados resultado = getDatos();

            if (!resultado.results().isEmpty()) {

                Libro libro = new Libro(resultado.results().get(0));
                libro = libroRepository.save(libro);

            }

            System.out.println("Resultado: ");
            System.out.println(resultado);
    }


}





