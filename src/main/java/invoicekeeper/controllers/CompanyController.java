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
    public CompanyDto addNewCompany(@RequestBody AddNewCompanyCommand command) {
        return service.addNewCompany(command);
    }

    @PostMapping("/{id}")
    public InvoiceDto addInvoiceToCompany(@PathVariable("id") long id, @RequestBody AddNewInvoiceCommand command) {
        return service.addNewInvoiceToCompany(id, command);
    }

    @GetMapping("/{id}")
    public CompanyDto findCompanyById(@PathVariable("id") long id) {
        return service.getCompanyById(id);
    }

    @GetMapping("/vatNumber/{vat}")
    public CompanyDto findCompanyByVatNumber(@PathVariable("vat") String vatNumber) {
        return service.getCompanyByVatNumber(vatNumber);
    }

    @GetMapping()
    public List<CompanyDto> findAllCompanies(@RequestParam Optional<String> textInCompanyName) {
        return service.findAllCompanies(textInCompanyName);
    }

    @PutMapping("/{id}")
    public CompanyDto changeCompanyAccountNumber(@PathVariable("id") long id, @RequestBody UpdateAccountNumberCommand command) {
        return service.updateAccountNumber(id, command);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCompanyById(@PathVariable("id") long id) {
        return service.deleteCompanyById(id);
    }
}
