public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        
        // We use Composition to add the 20-char constraint to PDF!
        Exporter pdf = new ConstrainedExporter(new PdfExporter(), 20, "PDF");
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();
        Exporter html = new HtmlExporter(); // Stretch goal

        System.out.println("PDF: " + safe(pdf, req));
        System.out.println("CSV: " + safe(csv, req));
        System.out.println("JSON: " + safe(json, req));
        System.out.println("HTML: " + safe(html, req)); // Stretch goal output
    }

    private static String safe(Exporter e, ExportRequest r) {
        try {
            ExportResult out = e.export(r);
            return "OK bytes=" + out.bytes.length;
        } catch (RuntimeException ex) {
            return "ERROR: " + ex.getMessage();
        }
    }
}