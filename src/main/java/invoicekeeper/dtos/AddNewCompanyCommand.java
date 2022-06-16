package invoicekeeper.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddNewCompanyCommand {
    private String companyName;
    private String vatNumber;
    private String bankAccountNumber;
}
