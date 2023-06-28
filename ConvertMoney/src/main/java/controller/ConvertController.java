package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/convert")
public class ConvertController {
    @GetMapping
    public String convert() {
        return "convert";
    }

    @PostMapping
    public String convertMoney(@RequestParam("gate") String gate,
                               @RequestParam("vnd") String vnd,
                               @RequestParam("usd") String usd,
                               Model model) {
        double gateMoney = 0.0;
        double vndValue = 0.0;
        double usdValue = 0.0;
        if (!gate.isEmpty()) {
            gateMoney = Double.parseDouble(gate);
        }
        if (!vnd.isEmpty()) {
            vndValue = Double.parseDouble(vnd);
        }
        if (!usd.isEmpty()) {
            usdValue = Double.parseDouble(usd);
        }
        double vndToUsd = vndValue / gateMoney;
        double usdToVnd = usdValue * gateMoney;
        model.addAttribute("vnd", usdToVnd);
        model.addAttribute("usd", vndToUsd);
        return "convert";
    }
}
