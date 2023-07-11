package seerbit.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seerbit.backend.models.virtualAccount.VirtualAccountPayment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirtualAccountData {

    private String code;

    private VirtualAccountPayment payments;

    private String message;
}
