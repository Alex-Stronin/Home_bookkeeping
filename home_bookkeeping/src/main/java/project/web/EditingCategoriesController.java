package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.dao.ExpenseCategoryDbDao;
import project.dao.IncomeCategoryDbDao;
import project.model.ExpenseCategory;
import project.model.IncomeCategory;
import project.model.Role;
import project.model.User;
import project.service.RedactRecordsInDb;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class EditingCategoriesController {
    private final RedactRecordsInDb redactRecordsInDb;

    @Autowired
    public EditingCategoriesController(RedactRecordsInDb redactRecordsInDb, ExpenseCategoryDbDao expenseCategoryDao, IncomeCategoryDbDao incomeCategoryDao) {
        this.redactRecordsInDb = redactRecordsInDb;
    }

    @GetMapping("/recordsDb/update")
    @PreAuthorize("authenticated")
    public ModelAndView viewPageUpdateRecordsDb() {
        return getModelAndView();
    }

    @PostMapping("/recordsDb/update")
    @PreAuthorize("authenticated")
    public ModelAndView editingCategories(@RequestParam("whatToAdd") String whatToAdd,
                                          @RequestParam("altNameCategory") String altNameCategory,
                                          @RequestParam("newNameCategory") String newNameCategory) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User("sisadmin", "admin", "ldfgjdff89", Role.ADMIN, new BigDecimal(0));

        if (whatToAdd.equals("расходы")) {
            redactRecordsInDb.editingCategoryNamesExpense(altNameCategory, newNameCategory);
        }
        if (whatToAdd.equals("доходы")) {
            redactRecordsInDb.editingCategoryNamesIncome(altNameCategory, newNameCategory);
        }
        ModelAndView mv = getModelAndView();
        mv.addObject("message", "ok");
        return mv;
    }

    private ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView("changeCategory");
        List<ExpenseCategory> listCategoryExpenses = redactRecordsInDb.getExpenseCategoryList();
        List<IncomeCategory> listCategoryIncomes = redactRecordsInDb.getIncomeSourceList();
        mv.addObject("allCategoriesExpenses", listCategoryExpenses);
        mv.addObject("allCategoriesIncomes", listCategoryIncomes);
        return mv;
    }
}
