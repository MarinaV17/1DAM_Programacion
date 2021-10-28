package modelo;

public class Documentary extends Producto{

  private MovieFormat formato;
  private String director;
  private String duracion;

  public Documentary(String titulo, int cantidad, String genero, MovieFormat formato, String director, String duracion) {
    super(titulo, cantidad, genero);
    this.formato = formato;
    this.director = director;
    this.duracion = duracion;
  }

  public MovieFormat getFormato() {
    return formato;
  }

  public void setFormato(MovieFormat formato) {
   this.formato = formato;
  }

  @Override
  public String toString() {
    return "Documentary: (" + super.toString() +
            ", formato=" + formato +
            ", director='" + director +
            ", duracion='" + duracion + ")";
  }
}
