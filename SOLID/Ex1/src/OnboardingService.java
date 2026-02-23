import java.util.List;

public class OnboardingService {
    private final StudentParser parser;
    private final StudentValidator validator;
    private final StudentRepository repository;
    private final ConsolePrinter printer;

    public OnboardingService(StudentParser parser, StudentValidator validator, 
                             StudentRepository repository, ConsolePrinter printer) {
        this.parser = parser;
        this.validator = validator;
        this.repository = repository;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        RawStudentData data = parser.parse(raw);
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, data.name, data.email, data.phone, data.program);

        repository.save(rec);
        printer.printSuccess(rec, repository.count());
    }
}