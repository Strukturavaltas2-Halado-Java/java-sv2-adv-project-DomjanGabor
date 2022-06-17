package invoicekeeper.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountNumberCommand {
    @NotBlank
    @Schema(description = "The VAT number of the company whose bank account number will be changed.", example = "84512648-1-45")
    private String vatNumber;

    @NotBlank
    @Schema(description = "The new bank account number.", example = "11112222-00000000-99999999")
    private String bankAccountNumber;
}
