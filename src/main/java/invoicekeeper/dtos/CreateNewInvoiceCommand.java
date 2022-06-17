package invoicekeeper.dtos;

import invoicekeeper.model.Company;
import invoicekeeper.model.InvoiceItem;
import invoicekeeper.model.PaymentStatus;
import invoicekeeper.validators.ValidAccountNumber;
import invoicekeeper.validators.ValidVatFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateNewInvoiceCommand {
    @NotBlank
    private String invoiceNumber;

    @PastOrPresent
    private LocalDate issueDate;

    private LocalDate dueDate;

    @NotNull
    private PaymentStatus paymentStatus;

    private List<InvoiceItem> items;

    @Min(1)
    private int amount;

    @NotBlank
    private String companyName;

    @NotBlank
    @ValidVatFormat
    private String vatNumber;

    @NotBlank
    @ValidAccountNumber
    private String bankAccountNumber;
}
