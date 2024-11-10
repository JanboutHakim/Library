package com.example.library.service;

import com.example.library.DTO.PatronDTO;
import com.example.library.exception.PatronNotFoundException;
import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;
    private ModelMapper modelMapper;
    public List<PatronDTO> getAllPatrons(){
        List<PatronDTO> patronDTOList =new ArrayList<>();
        for (Patron patron :patronRepository.findAll())
            patronDTOList.add(modelMapper.map(patron,PatronDTO.class));
        return patronDTOList;
    }

    @Cacheable(value = "patrons" , key = "#id",condition = "#result != null")
    public PatronDTO getPatronById(long id){

        Patron patron = patronRepository.findById(id).orElse(null);
        if(patron!=null)
            return modelMapper.map(patron,PatronDTO.class);
        else
            throw new PatronNotFoundException("The Patron with"+ id + "Not found");
    }

    @Transactional
    public void addPatron(PatronDTO patronDTO){
        patronRepository.save(convertToEntity(patronDTO));

    }

    @Transactional
    @CachePut(value = "patrons" , key = "#id",condition = "#result != null")
    public void updatePatron(PatronDTO patronDTO,long id){
        // Retrieve the existing patron from the repository
        Optional<Patron> existingPatron = patronRepository.findById(id);
        if(existingPatron.isPresent()) {
            Patron patron = existingPatron.get();

            patron.setFirstName(patronDTO.getFirstName());
            patron.setLastName(patronDTO.getLastName());
            patron.setContactNumber(patronDTO.getContactNumber());
            //check the email if provided because the email is nullable
            patron.setEmail(patronDTO.getEmail() != null ? patronDTO.getEmail() : "");

            patronRepository.save(patron);

        }else{
            throw new PatronNotFoundException("The Patron with"+ id + "Not found");
        }

    }
    @CacheEvict(value = "patrons" , key = "#id")
    public void deletePatron(long id){
        // Retrieve the existing patron from the repository
        Optional<Patron> existingPatron = patronRepository.findById(id);
        if(existingPatron.isPresent())
            patronRepository.deleteById(id);
        else
            throw new PatronNotFoundException("The Patron with"+ id + "Not found");
    }

    //convert the PatronDTO to the original Patron class
    private Patron convertToEntity(PatronDTO patronDTO){
        return modelMapper.map(patronDTO,Patron.class);
    }
}
