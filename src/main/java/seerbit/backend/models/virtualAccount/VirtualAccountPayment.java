package seerbit.backend.models.virtualAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirtualAccountPayment {

    private String reference;

    private String walletName;

    private String bankName;

    private String accountNumber;
}
