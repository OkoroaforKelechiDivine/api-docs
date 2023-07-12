package seerbit.backend.controller.virutalAccount;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import seerbit.backend.models.dto.VirtualAccountResponse;
import seerbit.backend.models.virtualAccount.VirtualAccount;
import seerbit.backend.services.virtualAccount.VirtualAccountService;

@Controller
public class VirtualAccountController {
    private final VirtualAccountService virtualAccountService;

    public VirtualAccountController(VirtualAccountService virtualAccountService) {
        this.virtualAccountService = virtualAccountService;
    }

    @PostMapping("/createVirtualAccount")
    public String createVirtualAccount(@ModelAttribute VirtualAccount request, Model model) {
        VirtualAccountResponse response = virtualAccountService.createVirtualAccount(request);
        model.addAttribute("response", response);
        return "virtualAccountResult";
    }

    @GetMapping("/{paymentreference}")
    public ResponseEntity<VirtualAccountResponse> getVirtualAccount(@PathVariable String paymentreference) {
        VirtualAccountResponse accountResponse = virtualAccountService.getVirtualAccount(paymentreference);
        if (accountResponse != null) {
            return ResponseEntity.ok(accountResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}




