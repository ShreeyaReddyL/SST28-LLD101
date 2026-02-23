import java.nio.charset.StandardCharsets;

public class CsvExporter implements Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req == null) throw new IllegalArgumentException("Request cannot be null");
        
        String body = req.body == null ? "" : req.body;
        // LSP Fix: Lossless conversion via standard CSV quoting
        if (body.contains(",") || body.contains("\n") || body.contains("\"")) {
            body = "\"" + body.replace("\"", "\"\"") + "\"";
        }
        
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}