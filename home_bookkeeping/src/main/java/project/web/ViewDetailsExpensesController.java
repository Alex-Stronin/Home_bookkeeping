package project.web;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ViewDetailsExpensesController {

    @Autowired
    CalculationAllExpensesFamily calculationExpensesFamily;
    @Autowired
    CalculationAllExpensesUser calculationExpensesUser;

    @GetMapping("/details/expenses")
    @PreAuthorize("authenticated")
    public String viewDetailsExpenses() {
        return "details-expense";
    }

    @PostMapping("/details/expenses")
    @PreAuthorize("authenticated")
    public ModelAndView viewDetailsExpenses(@RequestParam("checkbox") String checkbox,
                                            @RequestParam("startDate") LocalDate startDate,
                                            @RequestParam("endDate") LocalDate endDate) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User("sisadmin", "admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        ModelAndView mv = new ModelAndView("details-expense");

        if (checkbox.equals("family")) {
            mv.addObject("detailsListExpensesFamily", calculationExpensesFamily.calculationExpensesFamilyByCategory(startDate, endDate));
            mv.addObject("forWhomCalculation", checkbox);
        } else {
            if (checkbox.equals("user")) {
                mv.addObject("detailsListExpensesUser", calculationExpensesUser.calculationExpensesUserByCategory(user.getId(), startDate, endDate));
                mv.addObject("forWhomCalculation", checkbox);
            }
        }
        return mv;
    }
}
