package com.example.library.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDTO {
    private long Id;
    @NotNull
    @Size(min = 1,max = 100,message = "The Author name must be between 1 and 100 character ")
    private String author;
    @NotNull(message = "Publication Year is Required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    @NotNull
    @Pattern(regexp = "^(\\d{13})$", message = "ISBN must be a 13-digit number")
    private String ISBN;
    @NotNull
    @Size(min =1,max = 100,message = "The Title must be between 1 and 100 character ")
    private String title;
}
