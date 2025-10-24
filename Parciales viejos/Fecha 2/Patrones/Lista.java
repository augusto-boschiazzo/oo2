
public class Lista implements MarkdownComponent {

    private List<String> items;

    public Lista(List<String> items) {
        this.items = items
    }

    public String toString() {
        String result = ""
        int index = 1;
        for (String item : items) {
            result += index++ + ". " + item + "\n"
        }
        return result;
    }

    public boolean buscar(String texto) {
        return items.stream().anyMatch(item -> item.contains(texto));
    }

    public MarkdownComponent traducir() {
        List<string> itemsTraducidos = new ArrayList<String>();
        for (String item : items) {
            itemsTraducidos.append(Translator.translate(item));
        }
        return new Lista(itemsTraducidos);
    }


} 