package com.example.library.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatronDTO {
    private long Id;
    @NotNull
    @Size(min = 1,max = 100,message = "The First Name must be between 1 and 100 character")
    private String firstName;
    @NotNull
    @Size(min = 1,max = 100,message = "The Last Name must be between 1 and 100 character")
    private String lastName;
    @NotNull(message = "Phone number is required")
    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
            message = "Invalid phone number format. It should be a 10-digit number or include an optional country code (e.g., +1)"
    )
    private String contactNumber;
    @Email(message = "Invalid email format")
    private String email;
}
