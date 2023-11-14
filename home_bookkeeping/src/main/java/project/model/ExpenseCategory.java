package project.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "expenses_category")

public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_category_id")
    private Integer id;

    @Column(name = "name_expense_category")
    private String nameExpenseCategory;

    @OneToMany(mappedBy = "expenseCategory", fetch = FetchType.EAGER)
    private Set<Expense> expenses;

    private ExpenseCategory() {
    }

    public ExpenseCategory(String nameExpenseCategory) {
        this.nameExpenseCategory = nameExpenseCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameExpenseCategory() {
        return nameExpenseCategory;
    }

    public void setNameExpenseCategory(String nameExpenseCategory) {
        this.nameExpenseCategory = nameExpenseCategory;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }
}
