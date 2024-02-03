package antifraud.security.Dao;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MyUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final Role role;

    private final Boolean locked;

    private final Boolean enabled;
    private final Boolean expired;
    public MyUserDetails(User user){
        username = user.getUsername();
        password = user.getPassword();
        locked = user.getLocked();
        enabled = user.getEnabled();
        expired = user.getExpired();
        role = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> roles = new ArrayList<>();

        SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("ROLE_"+role);
        roles.add(role1);
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
