package com.wf.dcs.app.model;

import com.wf.dcs.app.model.base.BaseModel;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Collection;

/**
 * @author ddevera
 */
@Entity(name = "USERS")
public class User extends BaseModel implements UserDetails {

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED", nullable = false)
    @Type(type = "yes_no")
    private boolean enabled = true;

    @Column(name = "ACCOUNT_NON_LOCKED", nullable = false)
    @Type(type = "yes_no")
    private boolean accountNonLocked = true;

    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = false)
    @Type(type = "yes_no")
    private boolean accountNonExpired = true;

    @Column(name = "CREDENTIALS_NON_EXPIRED", nullable = false)
    @Type(type = "yes_no")
    private boolean credentialsNonExpired = true;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
