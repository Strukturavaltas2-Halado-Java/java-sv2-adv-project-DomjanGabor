package invoicekeeper.controllers;

import invoicekeeper.dtos.*;
import invoicekeeper.service.InvoicingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyController {
    private InvoicingService service;

    @PostMapping
    public CompanyDto addNewCompany(AddNewCompanyCommand command) {
        return service.addNewCompany(command);
    }

    @PostMapping("/{id}")
    public InvoiceDto addInvoiceToCompany(@PathVariable("id") long id, AddNewInvoiceCommand command) {
        return service.addNewInvoiceToCompany(id, command);
    }

    @GetMapping("/{id}")
    public CompanyDto findCompanyById(@PathVariable("id") long id) {
        return service.getCompanyById(id);
    }

    @GetMapping()
    public List<CompanyDto> findAllCompanies(@RequestParam Optional<String> companyName, @RequestParam Optional<String> vatNumber) {
        return service.findAllCompanies(companyName, vatNumber);
    }

    @PutMapping("/{id}")
    public CompanyDto changeCompanyAccountNumber(@PathVariable("id") long id, UpdateAccountNumberCommand command) {
        return service.updateAccountNumber(id, command);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCompanyById(@PathVariable("id") long id) {
        return service.deleteCompanyById(id);
    }



}
