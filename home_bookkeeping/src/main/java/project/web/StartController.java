package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import project.dao.FamilyBalance;

import java.time.LocalDate;

@Controller
public class StartController {
    @Autowired
    FamilyBalance balance;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/menu")
    public ModelAndView menuDisplay() {
        ModelAndView mv = new ModelAndView("menu");

        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 9, 30);
        int balanceFamily = balance.getBalance(startPeriod, endPeriod);

        mv.addObject("balanceFamily", balanceFamily);
        return mv;
    }

    @GetMapping("/comeIn")
    public String comeIn() {
        return "comeIn";
    }
}
