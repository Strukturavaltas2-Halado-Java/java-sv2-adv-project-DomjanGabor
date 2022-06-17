package invoicekeeper.dtos;

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
    private String vatNumber;

    @NotBlank
    private String bankAccountNumber;
}
