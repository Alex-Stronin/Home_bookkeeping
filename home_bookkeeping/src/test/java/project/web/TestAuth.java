package project.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import project.model.Role;
import project.model.User;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class TestAuth implements Authentication {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(Role.USER);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        User user = new User("user", "user", "password", Role.USER, BigDecimal.ZERO);
        user.setId(1);
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return "user";
    }
}
