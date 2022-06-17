package invoicekeeper.dtos;

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
    private String vatNumber;

    @NotBlank
    private String bankAccountNumber;
}
