package invoicekeeper.dtos;

import invoicekeeper.validators.ValidAccountNumber;
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
    @Schema(description = "The new bank account number.", example = "11112222-00000000-99999999")
    @ValidAccountNumber
    private String bankAccountNumber;
}
