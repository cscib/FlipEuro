package mt.com.casinoeuro.flipeuro.data.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Coin flip entiry.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/14 11.03
 */

@Entity
@Table(name = "COIN_FLIP", schema = "PUBLIC", catalog = "PUBLIC")
public class CoinFlip {
    private long coinFlipId;
    private long userId;
    private long coinId;
    private Date coinFlipDate;
    private String bet;
    private String outcome;
    private Coin coinByCoinId;
    private User userByUserId;

    @Id
    @Column(name = "COIN_FLIP_ID", nullable = false, insertable = true, updatable = true)
    public long getCoinFlipId() {
        return coinFlipId;
    }

    public void setCoinFlipId(long coinFlipId) {
        this.coinFlipId = coinFlipId;
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
    @Column(name = "COIN_ID", nullable = false, insertable = true, updatable = true)
    public long getCoinId() {
        return coinId;
    }

    public void setCoinId(long coinId) {
        this.coinId = coinId;
    }

    @Basic
    @Column(name = "COIN_FLIP_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCoinFlipDate() {
        return coinFlipDate;
    }

    public void setCoinFlipDate(Date coinFlipDate) {
        this.coinFlipDate = coinFlipDate;
    }

    @Basic
    @Column(name = "BET", nullable = true, insertable = true, updatable = true, length = 5)
    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    @Basic
    @Column(name = "OUTCOME", nullable = true, insertable = true, updatable = true, length = 5)
    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoinFlip coinFlip = (CoinFlip) o;

        if (coinFlipId != coinFlip.coinFlipId) return false;
        if (coinId != coinFlip.coinId) return false;
        if (userId != coinFlip.userId) return false;
        if (bet != null ? !bet.equals(coinFlip.bet) : coinFlip.bet != null) return false;
        if (coinFlipDate != null ? !coinFlipDate.equals(coinFlip.coinFlipDate) : coinFlip.coinFlipDate != null)
            return false;
        if (outcome != null ? !outcome.equals(coinFlip.outcome) : coinFlip.outcome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (coinFlipId ^ (coinFlipId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (coinId ^ (coinId >>> 32));
        result = 31 * result + (coinFlipDate != null ? coinFlipDate.hashCode() : 0);
        result = 31 * result + (bet != null ? bet.hashCode() : 0);
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "COIN_ID", referencedColumnName = "COIN_ID", nullable = false)
    public Coin getCoinByCoinId() {
        return coinByCoinId;
    }

    public void setCoinByCoinId(Coin coinByCoinId) {
        this.coinByCoinId = coinByCoinId;
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
