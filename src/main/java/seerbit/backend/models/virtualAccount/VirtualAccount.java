package seerbit.backend.models.virtualAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirtualAccount {

    private String publicKey;

    private String fullName;

    private String bankVerificationNumber;

    private String currency;

    private String country;

    private String reference;

    private String email;
}