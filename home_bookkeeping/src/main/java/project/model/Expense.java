package project.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")

public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Integer id;

    @Column(name = "expense_amount")
    private int expenseAmount;

    @Column(name = "date_expense")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;

    private Expense() {
    }


    public Expense(User user, ExpenseCategory expenseCategory, int expenseAmount) {
        this.expenseAmount = expenseAmount;
        this.date = LocalDate.now();
        this.user = user;
        this.expenseCategory = expenseCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
