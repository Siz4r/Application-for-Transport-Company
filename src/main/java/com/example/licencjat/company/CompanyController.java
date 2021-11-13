package com.example.licencjat.company;

import com.example.licencjat.company.models.CompanyDto;
import com.example.licencjat.company.models.CompanyListDto;
import com.example.licencjat.company.models.CompanyServiceCommand;
import com.example.licencjat.company.models.CompanyWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/companies/")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    public void addCompany(@Valid @RequestBody CompanyWebInput webInput) {
        companyService.addCompany(CompanyServiceCommand.builder().webInput(webInput).build());
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CompanyListDto> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto getCompanyById(@PathVariable("id") String id) {
        return companyService.getCompanyById(CompanyServiceCommand.builder().id(id).build());
    }

//    @PutMapping("{id}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource updated successfully")
//    public void updateAStuff(@PathVariable("id") String id, @Valid @RequestBody StuffUpdateCommand updateCommand) {
//        companyService.editStuff(StuffServiceCommand.builder()
//                .updateCommand(updateCommand)
//                .id(id).build());
//    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    public void deleteStuff(@PathVariable("id") String id) {
        companyService.deleteCompany(CompanyServiceCommand.builder().id(id).build());
    }
}
