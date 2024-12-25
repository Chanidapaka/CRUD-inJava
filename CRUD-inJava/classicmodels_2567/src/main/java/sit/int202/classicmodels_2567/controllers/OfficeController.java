package sit.int202.classicmodels_2567.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sit.int202.classicmodels_2567.entities.Office;
import sit.int202.classicmodels_2567.repositories.OfficeRepository;

import java.util.List;

@Controller()
@RequestMapping("/offices")
public class OfficeController {
    @Autowired
    private OfficeRepository officeRepository;

    @GetMapping("")
    public String students(Model model) {
        model.addAttribute("offices", officeRepository.findAll());
        model.addAttribute("page", "offices");
        return "office_list";
    }
    @GetMapping("/json")
    public ResponseEntity<List<Office>> students_list() {
        return ResponseEntity.ok(officeRepository.findAll());
    }

    @PostMapping("")
    public String createOffice(Office office, ModelMap modelMap) {
        modelMap.addAttribute("office", office);
        return "office_detail";
    }
    @GetMapping("/form")
    public  String getOfficeForm(Office office) {
        return "office_form";
    }
}
