package seerbit.backend.services.token;

import org.springframework.stereotype.Service;
import seerbit.backend.models.dto.SeerBitData;
import seerbit.backend.models.dto.SeerbitResponse;
import seerbit.backend.models.payments.Payments;
import seerbit.backend.models.token.CardTokenRequest;

@Service
public class CardTokenService {

    public SeerbitResponse createCardToken(CardTokenRequest request) {
        SeerbitResponse response = new SeerbitResponse();
        response.setStatus("SUCCESS");

        SeerBitData data = new SeerBitData();
        data.setCode("S20");
        data.setMessage("Transaction is pending");

        Payments paymentInfo = new Payments();
        paymentInfo.setPaymentReference(request.getPaymentReference());
        paymentInfo.setLinkingReference("SEERBIT60416746746373661414266005");
        paymentInfo.setRedirectUrl("https://seerbitapi.com/50E84E82C25D");

        data.setPayments(paymentInfo);
        response.setData(data);
        return response;
    }
}
