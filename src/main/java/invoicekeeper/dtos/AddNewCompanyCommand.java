package invoicekeeper.dtos;

import invoicekeeper.validators.ValidAccountNumber;
import invoicekeeper.validators.ValidVatFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class AddNewCompanyCommand {
    @NotBlank
    private String companyName;

    @NotBlank
    @ValidVatFormat
    private String vatNumber;

    @NotBlank
    @ValidAccountNumber
    private String bankAccountNumber;
}
