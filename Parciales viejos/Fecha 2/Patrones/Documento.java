
public class Documento implements MarkdownComponent {

    private String titulo;
    private String autor;
    private Seccion raiz;

    public Documento(String titulo, String autor, Seccion raiz) {
        this.titulo = titulo;
        this.autor = autor;
        this.raiz = raiz;
    }

    public String toString() {
        return this.titulo + " - " + this.autor "\n" + raiz.toString();
    }

    public boolean buscar(String texto) {
        return raiz.buscar(texto);
    }

    public MarkdownComponent traducir() {
        return new Documento(Translator.translate(this.titulo), this.autor, raiz.traducir());
    }


}