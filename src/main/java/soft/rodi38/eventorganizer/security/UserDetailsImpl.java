package soft.rodi38.eventorganizer.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import soft.rodi38.eventorganizer.model.entity.Attendee;
import soft.rodi38.eventorganizer.model.entity.Organizer;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(UUID id,String name,  String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Attendee attendee) {
        GrantedAuthority authority = new SimpleGrantedAuthority(attendee.getRole().getName().name());
        List<GrantedAuthority> authorities = Collections.singletonList(authority);


        return new UserDetailsImpl(
                attendee.getId(),
                attendee.getName(),
                attendee.getUsername(),
                attendee.getEmail(),
                attendee.getPassword(),
                authorities);
    }

    public static UserDetailsImpl build(Organizer organizer) {

        GrantedAuthority authority = new SimpleGrantedAuthority(organizer.getRole().getName().name());
        List<GrantedAuthority> authorities = Collections.singletonList(authority);


        return new UserDetailsImpl(
                organizer.getId(),
                organizer.getName(),
                organizer.getUsername(),
                organizer.getEmail(),
                organizer.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UUID getId() {
        return id;
    }

    public String getName(){
        return name;
    }


    public String getEmail() {
        return email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

}
