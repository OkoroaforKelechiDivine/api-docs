package seerbit.backend.models.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private String transactionReference;

    private String currency;

    private BigDecimal amount;

    private String email;

    private String fullName;
}