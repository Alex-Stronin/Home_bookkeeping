package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.model.Role;
import project.model.User;
import project.service.CalculationAllExpensesFamily;
import project.service.CalculationAllExpensesUser;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class CalculationExpensesController {

    @Autowired
    CalculationAllExpensesFamily calculationExpensesFamily;
    @Autowired
    CalculationAllExpensesUser calculationExpensesUser;


    @GetMapping("/expense/calculation")
    @PreAuthorize("authenticated")
    public String viewPageExpense() {
        return "expense";
    }

    @PostMapping("/expense/calculation")
    @PreAuthorize("authenticated")
    public ModelAndView calculationExpenses(@RequestParam("checkbox") String checkbox,
                                            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User("sisadmin", "admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        ModelAndView mv = new ModelAndView("expense");

        if (checkbox.equals("family")) {
            mv.addObject("totalExpenseFamily", calculationExpensesFamily.calculationExpensesFamily(startDate, endDate));
        } else {
            if (checkbox.equals("user")) {
                mv.addObject("totalExpenseUser", calculationExpensesUser.calculationExpensesUser(user.getId(), startDate, endDate));
            }
        }
        mv.addObject("checkbox", checkbox);
        return mv;
    }
}
