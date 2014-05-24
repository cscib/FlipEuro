package mt.com.casinoeuro.flipeuro.data.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Role entity.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/14 11.03
 */
@Entity
public class Role {
    private Long roleId;
    private String roleName;
    private Collection<User> usersByRoleId;

    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID", nullable = false, insertable = true, updatable = true)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "ROLE_NAME", nullable = true, insertable = true, updatable = true, length = 10)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<User> getUsersByRoleId() {
        return usersByRoleId;
    }

    public void setUsersByRoleId(Collection<User> usersByRoleId) {
        this.usersByRoleId = usersByRoleId;
    }
}
