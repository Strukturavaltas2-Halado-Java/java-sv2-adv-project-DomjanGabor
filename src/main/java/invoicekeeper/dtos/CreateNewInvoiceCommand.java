package invoicekeeper.dtos;

import invoicekeeper.model.Company;
import invoicekeeper.model.InvoiceItem;
import invoicekeeper.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateNewInvoiceCommand {
    private String invoiceNumber;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private PaymentStatus paymentStatus;
    private List<InvoiceItem> items;
    private int amount;
    private Company company;
}
