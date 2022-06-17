package invoicekeeper.dtos;

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
    private String invoiceNumber;

    @Min(1)
    private int amount;

    @NotBlank
    private String bankAccountNumber;
}
