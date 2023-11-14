package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ExpenseCategoryDbDao;
import project.dao.ExpenseDbDao;
import project.dao.IncomeCategoryDbDao;
import project.dao.IncomeDbDao;
import project.model.ExpenseCategory;
import project.model.IncomeCategory;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedactRecordsInDb {

    private final ExpenseDbDao expenseDbDao;
    private final IncomeDbDao incomeDbDao;
    private final ExpenseCategoryDbDao expenseCategoryDbDao;
    private final IncomeCategoryDbDao incomeCategoryDbDao;

    @Autowired
    public RedactRecordsInDb(ExpenseDbDao expenseDbDao, IncomeDbDao incomeDbDao,
                             ExpenseCategoryDbDao expenseCategoryDbDao, IncomeCategoryDbDao incomeCategoryDbDao) {
        this.expenseDbDao = expenseDbDao;
        this.incomeDbDao = incomeDbDao;
        this.expenseCategoryDbDao = expenseCategoryDbDao;
        this.incomeCategoryDbDao = incomeCategoryDbDao;
    }

    @Transactional
    public void editingCategoryNamesExpense(String currentName, String newName) {
        ExpenseCategory editableCategory = expenseCategoryDbDao.findByNameExpenseCategory(currentName);
        editableCategory.setNameExpenseCategory(newName);
        expenseCategoryDbDao.save(editableCategory);
    }

    @Transactional
    public void editingCategoryNamesIncome(String currentName, String newName) {
        IncomeCategory editableCategory = incomeCategoryDbDao.findBySourceIncomeCategory(currentName);
        editableCategory.setSourceIncomeCategory(newName);
        incomeCategoryDbDao.save(editableCategory);
    }

    @Transactional
    public List<ExpenseCategory> removeExpenseCategory(String nameRemoveCategory, int idAnotherCategory) {
        int idRemoveCategory = expenseCategoryDbDao.findByNameExpenseCategory(nameRemoveCategory).getId();
        expenseDbDao.rewritingExpenseId(idRemoveCategory, idAnotherCategory);
        expenseCategoryDbDao.deleteByNameExpenseCategory(nameRemoveCategory);
        return getExpenseCategoryList();
    }

    @Transactional
    public List<IncomeCategory> removeIncomeSource(String nameRemoveCategory, int idAnotherCategory) {
        int idRemoveCategory = incomeCategoryDbDao.findBySourceIncomeCategory(nameRemoveCategory).getId();
        incomeDbDao.rewritingIncomeId(idRemoveCategory, idAnotherCategory);
        incomeCategoryDbDao.deleteBySourceIncomeCategory(nameRemoveCategory);
        return getIncomeSourceList();
    }

    public List<ExpenseCategory> getExpenseCategoryList() {
        List<ExpenseCategory> expenseCategoryList = new ArrayList<>();
        expenseCategoryDbDao.findAll().iterator().forEachRemaining(expenseCategoryList::add);
        return expenseCategoryList;
    }

    public List<IncomeCategory> getIncomeSourceList() {
        List<IncomeCategory> incomeCategoryList = new ArrayList<>();
        incomeCategoryDbDao.findAll().iterator().forEachRemaining(incomeCategoryList::add);
        return incomeCategoryList;
    }
}
