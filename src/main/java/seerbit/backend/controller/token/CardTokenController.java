package seerbit.backend.controller.token;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seerbit.backend.models.dto.SeerbitResponse;
import seerbit.backend.models.token.CardTokenRequest;
import seerbit.backend.services.token.CardTokenService;

@RestController
@RequestMapping("/api/v2/payments")
public class CardTokenController {
    private final CardTokenService cardTokenService;

    public CardTokenController(CardTokenService cardTokenService) {
        this.cardTokenService = cardTokenService;
    }

    @PostMapping("/create-token")
    public ResponseEntity<SeerbitResponse> createCardToken(@RequestBody CardTokenRequest request) {
        SeerbitResponse response = cardTokenService.createCardToken(request);
        return ResponseEntity.ok(response);
    }
}