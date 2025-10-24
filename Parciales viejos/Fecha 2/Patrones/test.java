
public class text {

    private Documento documento;

    public static void main(String[] args) {
        documento = new Documento("Plan de estudio", "Pedro", new Seccion("Orientación a objetos", new ArrayList<MarkdownComponent>([new Parrafo("Temas de la materia"), new Lista(new ArrayList<String>(["Patrones de diseño", "Refactoring de código"]), new Seccion("Arquitectura de servicios", new ArrayList<MarkdownComponent>([new Parrafo("Arranca el semestre que viene")])))])))
        System.out.print(documento.toString());
    }


}