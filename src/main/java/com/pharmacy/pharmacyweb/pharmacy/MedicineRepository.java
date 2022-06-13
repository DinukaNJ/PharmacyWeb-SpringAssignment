package com.pharmacy.pharmacyweb.pharmacy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicineRepository extends CrudRepository<Medicine, Integer> {
    public Long countById(Integer id);

    @Query("SELECT m FROM Medicine m WHERE m.name LIKE %?1%"
            + " OR m.brand LIKE %?1%"
            + " OR CONCAT(m.price, '') LIKE %?1%")
    public List<Medicine> search(String keyword);
}

