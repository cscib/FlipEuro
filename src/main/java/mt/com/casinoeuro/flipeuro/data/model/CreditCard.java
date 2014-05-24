package mt.com.casinoeuro.flipeuro.data.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Credit card entiry.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/14 11.03
 */
@Entity
@Table(name = "CREDIT_CARD", schema = "PUBLIC", catalog = "PUBLIC")
public class CreditCard {
    private long creditCardId;
    private long userId;
    private String cardNumber;
    private String cardHoldersName;
    private Byte expiryMonth;
    private Byte expiryYear;
    private User userByUserId;
    private Collection<Deposit> depositsByCreditCardId;

    @Id
    @Column(name = "CREDIT_CARD_ID", nullable = false, insertable = true, updatable = true)
    public long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(long creditCardId) {
        this.creditCardId = creditCardId;
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
    @Column(name = "CARD_NUMBER", nullable = true, insertable = true, updatable = true, length = 20)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "CARD_HOLDERS_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCardHoldersName() {
        return cardHoldersName;
    }

    public void setCardHoldersName(String cardHoldersName) {
        this.cardHoldersName = cardHoldersName;
    }

    @Basic
    @Column(name = "EXPIRY_MONTH", nullable = true, insertable = true, updatable = true)
    public Byte getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Byte expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    @Basic
    @Column(name = "EXPIRY_YEAR", nullable = true, insertable = true, updatable = true)
    public Byte getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Byte expiryYear) {
        this.expiryYear = expiryYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (creditCardId != that.creditCardId) return false;
        if (userId != that.userId) return false;
        if (cardHoldersName != null ? !cardHoldersName.equals(that.cardHoldersName) : that.cardHoldersName != null)
            return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (expiryMonth != null ? !expiryMonth.equals(that.expiryMonth) : that.expiryMonth != null) return false;
        if (expiryYear != null ? !expiryYear.equals(that.expiryYear) : that.expiryYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (creditCardId ^ (creditCardId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (cardHoldersName != null ? cardHoldersName.hashCode() : 0);
        result = 31 * result + (expiryMonth != null ? expiryMonth.hashCode() : 0);
        result = 31 * result + (expiryYear != null ? expiryYear.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "creditCardByCreditCardId")
    public Collection<Deposit> getDepositsByCreditCardId() {
        return depositsByCreditCardId;
    }

    public void setDepositsByCreditCardId(Collection<Deposit> depositsByCreditCardId) {
        this.depositsByCreditCardId = depositsByCreditCardId;
    }
}
