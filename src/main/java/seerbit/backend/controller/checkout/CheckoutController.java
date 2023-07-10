package seerbit.backend.controller.checkout;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String showCheckoutPage(Model model) {
        model.addAttribute("transactionReference", System.currentTimeMillis());
        model.addAttribute("customerEmail", "test@emaildomain.com");
        return "checkout";
    }
}
