package by.dzmitry.test_task.service.user;

import by.dzmitry.test_task.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsEntity implements UserDetails {

    private String userName;

    private String password;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public UserDetailsEntity() {
    }

    public UserDetailsEntity toUserDetails(User user) {
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.userName = user.getUsername();
        userDetailsEntity.password = user.getPassword();
        userDetailsEntity.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
        return userDetailsEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}