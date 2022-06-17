package invoicekeeper.dtos;

import invoicekeeper.validators.ValidAccountNumber;
import invoicekeeper.validators.ValidVatFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Tag(name = "Input data for adding new company.")
public class AddNewCompanyCommand {
    @NotBlank
    @Schema(description = "Name of company.", example = "One Bit Studio")
    private String companyName;

    @NotBlank
    @ValidVatFormat
    @Schema(description = "VAT number of company.", example = "11223344-5-10")
    private String vatNumber;

    @NotBlank
    @ValidAccountNumber
    @Schema(description = "Bank account number of company.", example = "11111111-22222222-33333333")
    private String bankAccountNumber;
}
