package mt.com.casinoeuro.flipeuro.data.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Deposit entity.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/14 11.03
 */
@Entity
public class Deposit {
    private long depositId;
    private long userId;
    private long creditCardId;
    private Date depositDate;
    private Double depositAmount;
    private CreditCard creditCardByCreditCardId;
    private User userByUserId;

    @Id
    @Column(name = "DEPOSIT_ID", nullable = false, insertable = true, updatable = true)
    public long getDepositId() {
        return depositId;
    }

    public void setDepositId(long depositId) {
        this.depositId = depositId;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, insertable = true, updatable = true)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CREDIT_CARD_ID", nullable = false, insertable = true, updatable = true)
    public long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(long creditCardId) {
        this.creditCardId = creditCardId;
    }

    @Basic
    @Column(name = "DEPOSIT_DATE", nullable = true, insertable = true, updatable = true)
    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    @Basic
    @Column(name = "DEPOSIT_AMOUNT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (creditCardId != deposit.creditCardId) return false;
        if (depositId != deposit.depositId) return false;
        if (userId != deposit.userId) return false;
        if (depositAmount != null ? !depositAmount.equals(deposit.depositAmount) : deposit.depositAmount != null)
            return false;
        if (depositDate != null ? !depositDate.equals(deposit.depositDate) : deposit.depositDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (depositId ^ (depositId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (creditCardId ^ (creditCardId >>> 32));
        result = 31 * result + (depositDate != null ? depositDate.hashCode() : 0);
        result = 31 * result + (depositAmount != null ? depositAmount.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CREDIT_CARD_ID", referencedColumnName = "CREDIT_CARD_ID", nullable = false)
    public CreditCard getCreditCardByCreditCardId() {
        return creditCardByCreditCardId;
    }

    public void setCreditCardByCreditCardId(CreditCard creditCardByCreditCardId) {
        this.creditCardByCreditCardId = creditCardByCreditCardId;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
