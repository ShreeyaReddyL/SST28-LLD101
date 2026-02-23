import java.nio.charset.StandardCharsets;

public class PdfExporter implements Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req == null) throw new IllegalArgumentException("Request cannot be null");
        
        String fakePdf = "PDF(" + req.title + "):" + (req.body == null ? "" : req.body);
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}