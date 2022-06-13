package com.pharmacy.pharmacyweb.pharmacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MedicineController {
    @Autowired private MedicineService service;

    @GetMapping("/medicines")
    public String showMedicineList(Model model , @Param("keyword") String keyword) {
        List<Medicine> listMedicine = service.listAll(keyword);
        model.addAttribute("listMedicine", listMedicine);
        model.addAttribute("keyword", keyword);

        return "medicines";
    }

    @GetMapping("/medicines/add")
    public String showNewForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "add";
    }

    @PostMapping("/medicines/save")
    public String saveMedicine(Medicine medicine, RedirectAttributes ra) {
        service.save(medicine);
        ra.addFlashAttribute("message", "The medicine has been saved successfully.");
        return "redirect:/medicines";
    }

    @GetMapping("/medicines/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Medicine medicine = service.get(id);
            model.addAttribute("medicine", medicine);
            return "edit";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("error_message", "Could not find the details.");
            return "redirect:/medicines";
        }
    }

    @GetMapping("/medicines/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Successfully Deleted.");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("error_message", e.getMessage());
        }

        return "redirect:/medicines";
    }

}
