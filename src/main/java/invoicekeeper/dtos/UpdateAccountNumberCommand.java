package invoicekeeper.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateAccountNumberCommand {
    private String bankAccountNumber;
}
