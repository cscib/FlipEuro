package mt.com.casinoeuro.flipeuro.data.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * User entity.
 *
 * @author cscib
 * @version 1.0.0
 * @since 24/05/14 11.03
 */
@Entity
@NamedQuery(name = "User.getUserByUsername", query = "from User u where u.username = ?1")
public class User {
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String address;
    private Double balance;
    private Long roleId;
    private Collection<CoinFlip> coinFlipsByUserId;
    private Collection<CreditCard> creditCardsByUserId;
    private Collection<Deposit> depositsByUserId;
    private Role roleByRoleId;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false, insertable = true, updatable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "FIRSTNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "USERNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "DATE_OF_BIRTH", nullable = true, insertable = true, updatable = true)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "ADDRESS", nullable = true, insertable = true, updatable = true, length = 128)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "BALANCE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (roleId != null ? !roleId.equals(user.roleId) : user.roleId != null) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (balance != null ? !balance.equals(user.balance) : user.balance != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(user.dateOfBirth) : user.dateOfBirth != null) return false;
        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);

        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<CoinFlip> getCoinFlipsByUserId() {
        return coinFlipsByUserId;
    }

    public void setCoinFlipsByUserId(Collection<CoinFlip> coinFlipsByUserId) {
        this.coinFlipsByUserId = coinFlipsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<CreditCard> getCreditCardsByUserId() {
        return creditCardsByUserId;
    }

    public void setCreditCardsByUserId(Collection<CreditCard> creditCardsByUserId) {
        this.creditCardsByUserId = creditCardsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Deposit> getDepositsByUserId() {
        return depositsByUserId;
    }

    public void setDepositsByUserId(Collection<Deposit> depositsByUserId) {
        this.depositsByUserId = depositsByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", nullable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
