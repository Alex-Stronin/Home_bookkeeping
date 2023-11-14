package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.model.ExpenseCategory;
import project.model.IncomeCategory;
import project.service.RedactRecordsInDb;

import java.util.Collections;
import java.util.List;

@Controller
public class RemoveCategoriesController {

    private final RedactRecordsInDb redactRecordsInDb;

    @Autowired
    public RemoveCategoriesController(RedactRecordsInDb redactRecordsInDb) {
        this.redactRecordsInDb = redactRecordsInDb;
    }

    @GetMapping("/recordsDb/remove")
    @PreAuthorize("authenticated")
    public ModelAndView viewPageRemoveRecords() {
        ModelAndView mv = new ModelAndView("removeRecords");
        List<ExpenseCategory> listExpenseCategory = redactRecordsInDb.getExpenseCategoryList();
        List<IncomeCategory> listIncomesSource = redactRecordsInDb.getIncomeSourceList();
        mv.addObject("allCategoriesExpenses", listExpenseCategory);
        mv.addObject("allSourceIncomes", listIncomesSource);
        return mv;
    }

    @PostMapping("/recordsDb/remove")
    @PreAuthorize("authenticated")
    public ModelAndView removeRecordsFromDb(@RequestParam("whatRemove") String whatRemove,
                                            @RequestParam("nameRemoveCategory") String nameRemoveCategory) {

        ModelAndView mv = new ModelAndView("removeRecords");
        List<ExpenseCategory> listExpenseCategory = Collections.EMPTY_LIST;
        List<IncomeCategory> listIncomesSource = Collections.EMPTY_LIST;
        if (whatRemove.equals("расходы")) {
            int idAnotherCategory = 7;
            listExpenseCategory = redactRecordsInDb.removeExpenseCategory(nameRemoveCategory, idAnotherCategory);
            listIncomesSource = redactRecordsInDb.getIncomeSourceList();
        }
        if (whatRemove.equals("доходы")) {
            int idAnotherCategory = 6;
            listIncomesSource = redactRecordsInDb.removeIncomeSource(nameRemoveCategory, idAnotherCategory);
            listExpenseCategory = redactRecordsInDb.getExpenseCategoryList();
        }
        mv.addObject("allCategoriesExpenses", listExpenseCategory);
        mv.addObject("allSourceIncomes", listIncomesSource);
        mv.addObject("message", "Удаление прошло успешно!");
        return mv;
    }
}
