package invoicekeeper.dtos;

import invoicekeeper.model.InvoiceItem;
import invoicekeeper.model.PaymentStatus;
import invoicekeeper.validators.ValidAccountNumber;
import invoicekeeper.validators.ValidVatFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Invoice number.", example = "THI9545")
    private String invoiceNumber;

    @NotNull
    @PastOrPresent
    @Schema(description = "Date of issue.", example = "2022-05-20")
    private LocalDate issueDate;

    @NotNull
    @Schema(description = "The latest date when the invoice has to be payed.", example = "2022-06-10")
    private LocalDate dueDate;

    @NotNull
    @Schema(description = "The payment status of the invoice.", example = "UNPAYED")
    private PaymentStatus paymentStatus;

    @NotNull
    @Schema(description = "The items purchased on the invoice. Attributes needed: item name, pieces of items, cost of items")
    private List<InvoiceItem> items;

    @Min(1)
    @Schema(description = "The cost of the items in total.", example = "12000")
    private int amount;

    @NotBlank
    @Schema(description = "The company name on the invoice.", example = "Obsidian")
    private String companyName;

    @NotBlank
    @ValidVatFormat
    @Schema(description = "VAT number of company.", example = "55544333-1-10")
    private String vatNumber;

    @NotBlank
    @ValidAccountNumber
    @Schema(description = "Bank account number of company.", example = "11700300-44445555-88887777")
    private String bankAccountNumber;
}
