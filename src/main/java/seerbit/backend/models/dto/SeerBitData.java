package seerbit.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seerbit.backend.models.payments.Payments;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeerBitData {

    private String code;

    private Payments payments;

    private EncryptedSecKey EncryptedSecKey;
}
