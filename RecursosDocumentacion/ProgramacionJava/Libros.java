package libros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
Osmar Andres Debegnach
Erika Cologne
Ezequiel Dominguez
Julieta de los Ángeles Negrete
Escobar Brenda Giselle
Escobar Maximiliano Alexis
Ana Ochonga
 */
class Libro {

    //Atributo Numerico
    private int ISBN;

    //Atributo AlfaNumerico
    private String Titulo;

    //Atributo AlfaNumerico
    private String Autor;

    //Atributo Numerico
    private int NumeroPaginas;

    //Atributo AlfaNumerico
    private String Genero;

    public Libro() {
        //Constructor Vacio
    }

    public Libro(int ISBN, String Titulo, String Autor, int NumeroPaginas, String Genero) {
        this.ISBN = ISBN;
        this.Titulo = Titulo;
        this.Autor = Autor;
        this.NumeroPaginas = NumeroPaginas;
        this.Genero = Genero;
    }

    //Getters and Setters
    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public int getNumeroPaginas() {
        return NumeroPaginas;
    }

    public void setNumeroPaginas(int NumeroPaginas) {
        this.NumeroPaginas = NumeroPaginas;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    @Override
    public String toString() {
        return "Libro{ " + "ISBN: " + ISBN + ", Titulo: " + Titulo + ", Autor: " + Autor + ", NumeroPaginas: " + NumeroPaginas + ", Genero: " + Genero + '}';
    }
}

//Comparamos y ordenamos por Autor
class OrdenadorPorAutor implements Comparator<Libro> {

    @Override
    public int compare(Libro objeto1, Libro objeto2) {
        return objeto1.getAutor().compareTo(objeto2.getAutor());
    }
}

//Comparamos y ordenamos por Genero
class OrdenadorPorGenero implements Comparator<Libro> {

    @Override
    public int compare(Libro objeto1, Libro objeto2) {
        return objeto1.getGenero().compareTo(objeto2.getGenero());
    }
}

//Comparamos y ordenamos por titulo
class OrdenadorPorTitulo implements Comparator<Libro> {

    @Override
    public int compare(Libro objeto1, Libro objeto2) {
        return objeto1.getTitulo().compareTo(objeto2.getTitulo());
    }
}

//Comparamos y ordenamos por ISBN
class OrdenadorPorISBN implements Comparator<Libro> {

    @Override
    public int compare(Libro objeto1, Libro objeto2) {
        return Integer.compare(objeto1.getISBN(), objeto2.getISBN());
    }
}

//Comparamos y ordenamos por NumeroPaginas
class OrdenadorPorNumeroPagina implements Comparator<Libro> {

    @Override
    public int compare(Libro objeto1, Libro objeto2) {
        return Integer.compare(objeto1.getNumeroPaginas(), objeto2.getNumeroPaginas());
    }
}

public class Libros {

    //Mostramos un libro
    public static void mostrarEnPantalla(Libro dto) {

        System.out.println("Libro nro: " + dto.getISBN());
        System.out.println("Titulo : " + dto.getTitulo());
        System.out.println("Cantidad de paginas: " + dto.getNumeroPaginas());
        System.out.println("Autor: " + dto.getAutor());
        System.out.println("Genero: " + dto.getGenero());

    }

    public static void mostrarListaLibros(List<Libro> lista) {
        for (Libro libroIndividual : lista) {
            mostrarEnPantalla(libroIndividual);
        }
    }

    //Ordenador Univeral
    public static List<Libro> ordenarLibros(List<Libro> lista, boolean ordenarPorPaginas, boolean ordenarPorISBN,
            boolean ordenarPorTitulo, boolean ordenarPorAutor, boolean ordenarPorGenero) {

        List<Libro> aux = lista;

        if (ordenarPorAutor) {
            Collections.sort(lista, new OrdenadorPorAutor());
        }
        if (ordenarPorGenero) {
            Collections.sort(lista, new OrdenadorPorGenero());
        }
        if (ordenarPorTitulo) {
            Collections.sort(lista, new OrdenadorPorTitulo());
        }
        if (ordenarPorISBN) {
            Collections.sort(lista, new OrdenadorPorISBN());
        }
        if (ordenarPorPaginas) {
            Collections.sort(lista, new OrdenadorPorNumeroPagina());
        }

         mostrarListaLibros(aux);
         
         return aux;
    }
    
    public static void mostrarConsolaAmigable(){
        System.out.println("_________________________________________________________________");
    }

    public static void main(String[] args) {

        Libro libroMatematicas = new Libro(98, "Matematicas 1", "James Stewart", 678, "Aritmetica");
        Libro libroCiencias = new Libro(103, "Ciencias 1", "Santillana", 214, "Ciencias Naturales");
        Libro libroIngles = new Libro(98, "Ingles 1", "Gail Brenner", 459, "Lenguajes");

        List<Libro> lista = new ArrayList<>();
        lista.add(libroIngles);
        lista.add(libroCiencias);
        lista.add(libroMatematicas);

        System.out.println("Sin ordenar");
        //Acomadamos La Consola Amigable
        mostrarConsolaAmigable();
        //Mostramos la lista desordenada
        mostrarListaLibros(lista);
        //Acomadamos La Consola 
        mostrarConsolaAmigable();
        System.out.println("Ordenados por Paginas");
        //Acomadamos La Consola 
        mostrarConsolaAmigable();
        //Ordenamos por paginas y Mostramos la lista ordenada por paginas ascendetes
        List<Libro> librosordenados = ordenarLibros(lista, true, false, false, false, false);
        mostrarConsolaAmigable();
        mostrarConsolaAmigable();
        System.out.println("El libro con mas es: ");
        Libro LibroConMasPaginas = librosordenados.get(librosordenados.size() - 1 );
        mostrarEnPantalla(LibroConMasPaginas);
       
    }

}
