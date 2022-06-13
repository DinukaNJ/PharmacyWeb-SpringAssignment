package com.pharmacy.pharmacyweb.pharmacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    @Autowired private MedicineRepository repo;

    public List<Medicine> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return (List<Medicine>) repo.findAll();
    }

    public void save(Medicine medicine) {
        repo.save(medicine);
    }

    public Medicine get(Integer id) throws UserNotFoundException {

        Optional<Medicine> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any medicine.");
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any medicine !");
        }

        repo.deleteById(id);
    }
}
