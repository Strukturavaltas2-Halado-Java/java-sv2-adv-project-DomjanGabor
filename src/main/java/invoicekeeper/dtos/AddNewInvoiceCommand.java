package invoicekeeper.dtos;

import invoicekeeper.model.InvoiceItem;
import invoicekeeper.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AddNewInvoiceCommand {
    @NotBlank
    private String invoiceNumber;

    @PastOrPresent
    private LocalDate issueDate;

    private LocalDate dueDate;

    @NotNull
    private PaymentStatus paymentStatus;

    private List<InvoiceItem> items;
    private int amount;
}
