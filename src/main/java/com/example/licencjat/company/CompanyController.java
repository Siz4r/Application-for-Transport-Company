package com.example.licencjat.company;

import com.example.licencjat.UI.Annotations.PreAuthorizeAdmin;
import com.example.licencjat.UI.Annotations.PreAuthorizeAdminAndClient;
import com.example.licencjat.company.models.CompanyDto;
import com.example.licencjat.company.models.CompanyListDto;
import com.example.licencjat.company.models.CompanyServiceCommand;
import com.example.licencjat.company.models.CompanyWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/companies/")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @PreAuthorizeAdmin
    public ResponseEntity<String> addCompany(@Valid @RequestBody CompanyWebInput webInput) {
        return new ResponseEntity<String>(
                companyService.addCompany(CompanyServiceCommand.builder().webInput(webInput).build()).toString(),
                HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorizeAdminAndClient
    public List<CompanyListDto> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorizeAdminAndClient
    public CompanyDto getCompanyById(@PathVariable("id") UUID id) {
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
    @PreAuthorizeAdmin
    public void deleteStuff(@PathVariable("id") UUID id) {
        companyService.deleteCompany(CompanyServiceCommand.builder().id(id).build());
    }
}
