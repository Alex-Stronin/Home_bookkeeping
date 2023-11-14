package project.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.dao.ExpenseCategoryDbDao;
import project.dao.ExpenseDbDao;
import project.dto.ExpenseDto;
import project.model.Expense;
import project.model.ExpenseCategory;
import project.model.Role;
import project.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddExpenseController {

    @Autowired
    ExpenseCategoryDbDao expenseCategoryDbDao;

    @Autowired
    ExpenseDbDao expenseDbDao;

    @ModelAttribute("allCategories")
    List<ExpenseCategory> getCategories() {
        List<ExpenseCategory> list = new ArrayList<>();
        expenseCategoryDbDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("/expenses/add")
    @PreAuthorize("authenticated")
    public String getNewExpenseView(Model model) {
        model.addAttribute("expenseDto", new ExpenseDto("default category", 0));
        return "addExpense";
    }

    @PostMapping("/expenses/add")
    @PreAuthorize("authenticated")
    public String addExpense(@Valid @ModelAttribute ExpenseDto expenseDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "addExpense";
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User("sisadmin", "admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        ExpenseCategory expenseCategory = expenseCategoryDbDao.findByNameExpenseCategory(expenseDto.categoryName());

        Expense toBeSaved = new Expense(user, expenseCategory, expenseDto.amount());
        expenseDbDao.save(toBeSaved);

        return "addExpense";

    }
}
