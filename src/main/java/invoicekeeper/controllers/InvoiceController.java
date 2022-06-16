package invoicekeeper.controllers;

import invoicekeeper.dtos.CreateNewInvoiceCommand;
import invoicekeeper.dtos.InvoiceDto;
import invoicekeeper.dtos.PayInvoiceCommand;
import invoicekeeper.service.InvoicingService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
public class InvoiceController {
    private InvoicingService service;

    @PostMapping
    public InvoiceDto saveNewInvoice(@RequestBody CreateNewInvoiceCommand command) {
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
    public InvoiceDto payInvoice(@RequestBody PayInvoiceCommand command) {
        return service.payInvoice(command);
    }

    @DeleteMapping("/{id}")
    public boolean deleteInvoiceById(@PathVariable("id") long id) {
        return service.deleteInvoiceById(id);
    }
}
