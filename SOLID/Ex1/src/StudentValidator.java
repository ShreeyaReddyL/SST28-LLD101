import java.util.*;

public class StudentValidator {
    private final Set<String> allowedPrograms;

    public StudentValidator(Set<String> allowedPrograms) {
        this.allowedPrograms = allowedPrograms;
    }

    public List<String> validate(RawStudentData data) {
        List<String> errors = new ArrayList<>();
        if (data.name.isBlank()) errors.add("name is required");
        if (data.email.isBlank() || !data.email.contains("@")) errors.add("email is invalid");
        if (data.phone.isBlank() || !data.phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!allowedPrograms.contains(data.program)) errors.add("program is invalid");
        
        return errors;
    }
}