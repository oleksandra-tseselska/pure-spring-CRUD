package repository.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    private int employeeId;
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "[a-z]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String name;
    @Pattern(regexp = "[a-z]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotBlank(message = "Role is mandatory")
    private String role;
    private int dateOfJoining;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
