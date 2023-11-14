package project.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "incomes")


public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Integer id;

    @Column(name = "income_amount")
    private int incomeAmount;

    @Column(name = "date_income")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "income_category_id")
    private IncomeCategory incomeCategory;

    public Income() {
    }

    public Income(User user, IncomeCategory incomeCategory, int incomeAmount) {
        this.user = user;
        this.incomeCategory = incomeCategory;
        this.incomeAmount = incomeAmount;
        this.date = LocalDate.now();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIncomeAmount() {
        return incomeAmount;
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
