# Refactoring - Parcial Fecha 1 - 2025

1. Code smells:

    - Long Method: Líneas 6-24 y 25-44
    - Switch Statement: Líneas 6 y 25
    - Repeated code: Líneas 8-14 y 27-33, 24 y 43

2. Refactorings:

    - Switch Statement -> Replace Conditional with Polymorphism:

        1. Crear la jerarquía de clases: En este caso, transformo la clase ReportGenerator a abstracta.
        2. Crear una subclase por cada condicional: Vamos a crear las clases PDF y XLS, que extienden a ReportGenerator.
        3. Copiar el código del condicional correspondiente a la subclase: Copio las líneas 8 a 24 a la clase PDF, y las 27 a 43 a la clase XLS.
        4. Compilar y testear.
        5. Eliminar la parte del código copiada del método original.
        6. Compilar y testear.

    - Long Method -> Form Template Method:

        1. Extract Method: Divido el método de las subclases en métodos más pequeños. Para esto se necesitan varios cambios: docFile pasa a ser una instancia de clase (protected, para que las subclases puedan accederlo), y en el constructor de la superclase se inicializa el docFile. En este caso, se crean los métodos createFile, configureFile, setFileContent y serializeDocument.
        2. Pull Up Method: Se hace un pull up de los métodos createFile y setFileContent, ya que son idénticos.
        3. Defino los métodos abstractos configureFile y serializeDocument.
        4. Compilo y testeo.

    - Código final

    ```java
    public abstract class ReportGenerator {

        protected DocumentFile docFile;

        public ReportGenerator() {
            this.docFile = new DocumentFile();
        }

        public void generateReport(Document document) {
            createFile(document);

            configureFile();

            setFileContent();

            this.saveExportedFile(docFile);
        }

        private void createFile(Document document) {
            docFile.setTitle(document.getTitle());
            String authors = "";
            for (String author : document.getAuthors()) {
                authors += ", " + author;
            }
            docFile.setAuthor(authors);
        }

        private void setFileContent(Document document) {
            byte[] content = serializeDocument(document);
            docFile.setContent(content);
        }

        protected abstract void configureFile();
        protected abstract void serializeDocument(Document document);


    }

    public class PDFGenerator extends ReportGenerator {

        public void configureFile() {
            docFile.contentType("application/pdf");
            docFile.setPageSize("A4");
        }

        public void serializeDocument() {
            PDFExporter exporter = new PDFExporter();
            return exporter.generatePDFFile(document);
        }

    }

    public class XLSGenerator extends ReportGenerator {

        public void configureFile() {
            docFile.contentType("application/vnd.ms-excel");
            docFile.setSheetName(document.getSubtitle());
        }

        public void serializeDocument() {
            ExcelWriter writer = new ExcelWriter();
            return writer.generateExcelFile(document);
        }

    }

    class ReportGeneratorTest {
        ReportGenerator generatorPDF;
        ReportGenerator generatorXLS;
        Document document;

        @BeforeEach
        void setUp() {
            document = new Document("Informe");
            document.addAuthor("Carlos");
            document.addAuthor("Ana");

            generatorPDF = new PDFGenerator();
        }

        @Test
        void testPDF() {
            generatorPDF.generateReport(document);
        }

        @void testXLS() {
            generatorXLS.generateReport(document);
        }


    }
    ```
