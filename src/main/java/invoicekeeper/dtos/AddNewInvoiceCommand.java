package invoicekeeper.dtos;

import invoicekeeper.model.InvoiceItem;
import invoicekeeper.model.PaymentStatus;
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
public class AddNewInvoiceCommand {
    @NotBlank
    @Schema(description = "Invoice number.", example = "RS5566")
    private String invoiceNumber;

    @NotNull
    @PastOrPresent
    @Schema(description = "Date of issue.", example = "2022-06-15")
    private LocalDate issueDate;

    @NotNull
    @Schema(description = "The latest date when the invoice has to be payed.", example = "2022-07-30")
    private LocalDate dueDate;

    @NotNull
    @Schema(description = "The payment status of the invoice.", example = "UNPAYED")
    private PaymentStatus paymentStatus;

    @NotNull
    @Schema(description = "The items purchased on the invoice. Attributes needed: item name, pieces of items, cost of items")
    private List<InvoiceItem> items;

    @Min(1)
    @Schema(description = "The cost of the items in total.", example = "1000")
    private int amount;
}
