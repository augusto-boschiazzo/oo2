
public class Parrafo implements MarkdownComponent {

    private String texto;

    public Parrafo(String texto) {
        this.texto = texto; 
    }

    public String toString() {
        return this.texto + "\n"
    }

    public boolean buscar(String texto) {
        return this.texto.contains(texto);
    }

    public MarkdownComponent traducir() {
        return new Parrafo(Translator.translate(this.texto));
    }

    
}