# Refactoring - Parcial Fecha 1 - 2025

1. Code smells:

    - Long Method: Líneas 6-24 y 25-44
    - Switch Statement: Líneas 6 y 25
    - Repeated code: Líneas 8-14 y 27-33, 24 y 43

2. Refactorings:

    - Switch Statement -> Replace Conditional with Polymorphism:

        1. Crear la jerarquía de clases: En este caso, creo una clase abstracta DocumentExporter. Copiar el método generateReport a la superclase.
        2. Crear una subclase por cada condicional: Vamos a crear primero la clase PDF y la clase XLS, que extienden a DocumentExporter.
        3. Copiar el código del condicional correspondiente a la subclase:

    - Long Method -> Form Template Method:

        1. Extract Method
        2. Pull Up Method

    - Código final

    ```java
    public abstract class ReportGenerator {

        private DocumentFile docFile;

        public ReportGenerator() {
            this.docFile = new DocumentFile();
        }

        public void constructFile(Document document) {
            createFile(document);

            configureFile();

            setFileContent();

            this.saveExportedFile(docFile);
        }

        public void createFile(Document document) {
            docFile.setTitle(document.getTitle());
            String authors = "";
            for (String author : document.getAuthors()) {
                authors += ", " + author;
            }
            docFile.setAuthor(authors);
        }

        public void setFileContent(Document document) {
            byte[] content = serializeDocument(document);
            docFile.setContent(content);
        }

        public abstract void configureFile();
        public abstract void serializeDocument(Document document);


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
