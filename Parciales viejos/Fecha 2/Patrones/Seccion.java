
public class Seccion implements MarkdownComponent {

    private String titulo;
    private List<MarkdownComponent> elementos;

    public Seccion(String titulo, List<MarkdownComponent> elementos) {
        this.titulo = titulo;
        this.elementos = elementos;
    }

    public String toString() {
        String result = "### " + titulo + "\n";
        for (MarkdownComponent elemento : elementos) {
            result += elemento.toString();
        }
        return result;
    }

    public boolean buscar(String texto) {
        if (titulo.contains(texto)) {
            return true;
        }
        return elementos.stream().anyMatch(elemento -> elemento.buscar(texto));
    }

    public MarkdownComponent traducir() {
        List<MarkdownComponent> elementosTraducidos = new ArrayList<MarkdownComponent>();
        elementos.stream().forEach(elemento -> elementosTraducidos.append(elemento.traducir()));
        return new Seccion(Translator.translate(this.titulo), elementosTraducidos);
    }


}