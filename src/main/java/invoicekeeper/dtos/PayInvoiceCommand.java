package invoicekeeper.dtos;

import invoicekeeper.validators.ValidAccountNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayInvoiceCommand {
    @NotBlank
    @Schema(description = "Invoice number.", example = "123456AB")
    private String invoiceNumber;

    @Min(1)
    @Schema(description = "The cost of the items in total.", example = "1200")
    private int amount;

    @NotBlank
    @ValidAccountNumber
    @Schema(description = "The bank account number to transfer the money to.", example = "12345876-86496452-11111111")
    private String bankAccountNumber;
}
