package com.example.library.controller;

import com.example.library.DTO.PatronDTO;
import com.example.library.exception.PatronNotFoundException;
import com.example.library.model.Patron;
import com.example.library.service.PatronService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping("")
    private ResponseEntity<List<PatronDTO>> getAllPatrons(){
        return ResponseEntity.status(HttpStatus.OK).body(patronService.getAllPatrons());
    }

    @GetMapping("/{id}")
    private ResponseEntity<PatronDTO> getPatron(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(patronService.getPatronById(id));
    }

    @PostMapping("")
    private ResponseEntity<String> handleAddingPatron(@Valid @RequestBody PatronDTO patronDTO){
        patronService.addPatron(patronDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patron added successfully");
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> handleUpdatingPatron(@Valid @RequestBody PatronDTO patronDTO, @PathVariable("id") long id){
        patronService.updatePatron(patronDTO,id);
        return ResponseEntity.status(HttpStatus.OK).body("Patron Updated Successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> handleDeletePatron(@PathVariable("id") long id){
        patronService.deletePatron(id);
        return ResponseEntity.status(HttpStatus.OK).body("Patron Deleted successfully");
    }

    @ExceptionHandler(PatronNotFoundException.class)
    public ResponseEntity<String> handleBookNotFound(PatronNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
