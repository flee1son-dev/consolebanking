package sorokin.java.course.account;


import jakarta.persistence.*;
import sorokin.java.course.user.User;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "money_amount", nullable = false)
    private int moneyAmount;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Account() {
    }

    public Account(User user, int moneyAmount) {
        this.user = user;
        this.moneyAmount = moneyAmount;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        if (moneyAmount < 0) {
            throw new IllegalArgumentException("Attempted to set moneyAmount less than 0");
        }
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
