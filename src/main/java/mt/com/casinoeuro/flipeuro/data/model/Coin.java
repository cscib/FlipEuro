package mt.com.casinoeuro.flipeuro.data.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Coin entity.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/14 11.03
 */
@Entity
public class Coin {
    private Long coinId;
    private String name;
    private Double denomination;
    private String currency;
    private Collection<CoinFlip> coinFlipsByCoinId;

    @Id
    @Column(name = "COIN_ID", nullable = false, insertable = true, updatable = true)
    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    @Basic
    @Column(name = "NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DENOMINATION", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getDenomination() {
        return denomination;
    }

    public void setDenomination(Double denomination) {
        this.denomination = denomination;
    }

    @Basic
    @Column(name = "CURRENCY", nullable = true, insertable = true, updatable = true, length = 3)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        if (coinId != null ? !coinId.equals(coin.coinId) : coin.coinId != null) return false;
        if (currency != null ? !currency.equals(coin.currency) : coin.currency != null) return false;
        if (denomination != null ? !denomination.equals(coin.denomination) : coin.denomination != null) return false;
        if (name != null ? !name.equals(coin.name) : coin.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coinId != null ? coinId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "coinByCoinId")
    public Collection<CoinFlip> getCoinFlipsByCoinId() {
        return coinFlipsByCoinId;
    }

    public void setCoinFlipsByCoinId(Collection<CoinFlip> coinFlipsByCoinId) {
        this.coinFlipsByCoinId = coinFlipsByCoinId;
    }
}
