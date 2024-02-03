package antifraud.security.Dao;

import antifraud.security.Dao.Role;
import jakarta.persistence.*;

@Entity
//@DataAmount
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private Role role;

    private Boolean locked = false;

    private Boolean enabled = true;
    private Boolean expired = false;

//    @ElementCollection
//    private ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>(){{
//        add(new SimpleGrantedAuthority("USER"));
//        add(new SimpleGrantedAuthority("ROLE_USER"));
//    }};

    public Long getId() {
        return id;
    }


    public User(String name, String username, String password, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public User() {
        
    }

    //    @OneToMany(fetch = FetchType.EAGER)
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    public ArrayList<SimpleGrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(ArrayList<SimpleGrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
}
