package invoicekeeper.controllers;

import invoicekeeper.dtos.CreateNewInvoiceCommand;
import invoicekeeper.dtos.InvoiceDto;
import invoicekeeper.dtos.PayInvoiceCommand;
import invoicekeeper.service.InvoicingService;
import invoicekeeper.validators.Violation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
public class InvoiceController {
    private InvoicingService service;

    @PostMapping
    public InvoiceDto saveNewInvoice(@Valid @RequestBody CreateNewInvoiceCommand command) {
        return service.saveNewInvoice(command);
    }

    @GetMapping("/{id}")
    public InvoiceDto getInvoiceById(@PathVariable("id") long id) {
        return service.getInvoiceById(id);
    }

    @GetMapping()
    public List<InvoiceDto> getAllInvoices(@RequestParam Optional<String> companyName, @RequestParam Optional<String> vatNumber,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> issuedAfter,
                                           @RequestParam Optional<String> isOverDue) {
        return service.getAllInvoices(companyName, vatNumber, issuedAfter, isOverDue);
    }

    @PutMapping("/payment")
    public InvoiceDto payInvoice(@Valid @RequestBody PayInvoiceCommand command) {
        return service.payInvoice(command);
    }

    @DeleteMapping("/{id}")
    public boolean deleteInvoiceById(@PathVariable("id") long id) {
        return service.deleteInvoiceById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidationError(MethodArgumentNotValidException exception) {
        List<Violation> violations =
                exception.getBindingResult().getFieldErrors().stream()
                        .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(URI.create("invoices/validation-error"))
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
