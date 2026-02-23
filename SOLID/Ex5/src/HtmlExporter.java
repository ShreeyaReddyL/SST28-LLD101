import java.nio.charset.StandardCharsets;

public class HtmlExporter implements Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req == null) throw new IllegalArgumentException("Request cannot be null");
        String html = "<html><body><h1>" + req.title + "</h1><p>" + req.body + "</p></body></html>";
        return new ExportResult("text/html", html.getBytes(StandardCharsets.UTF_8));
    }
}