package invoicekeeper.controllers;

import invoicekeeper.dtos.*;
import invoicekeeper.service.InvoicingService;
import invoicekeeper.validators.Violation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyController {
    private InvoicingService service;

    @PostMapping
    public CompanyDto addNewCompany(@Valid @RequestBody AddNewCompanyCommand command) {
        return service.addNewCompany(command);
    }

    @PostMapping("/{id}")
    public CompanyDto addInvoiceToCompany(@PathVariable("id") long id, @Valid @RequestBody AddNewInvoiceCommand command) {
        return service.addNewInvoiceToCompany(id, command);
    }

    @GetMapping("/{id}")
    public CompanyDto findCompanyById(@PathVariable("id") long id) {
        return service.getCompanyById(id);
    }

    @GetMapping("/vat-number/{vat}")
    public CompanyDto findCompanyByVatNumber(@PathVariable("vat") String vatNumber) {
        return service.getCompanyByVatNumber(vatNumber);
    }

    @GetMapping()
    public List<CompanyDto> findAllCompanies(@RequestParam Optional<String> textInCompanyName) {
        return service.findAllCompanies(textInCompanyName);
    }

    @PutMapping()
    public CompanyDto changeCompanyAccountNumber(@Valid @RequestBody UpdateAccountNumberCommand command) {
        return service.updateAccountNumber(command);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCompanyById(@PathVariable("id") long id) {
        return service.deleteCompanyById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidationError(MethodArgumentNotValidException exception) {
        List<Violation> violations =
                exception.getBindingResult().getFieldErrors().stream()
                        .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(URI.create("companies/validation-error"))
                .withTitle("Validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(exception.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
