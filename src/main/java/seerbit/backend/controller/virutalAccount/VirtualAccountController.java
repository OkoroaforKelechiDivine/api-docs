package seerbit.backend.controller.virutalAccount;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}




