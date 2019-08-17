package com.idbsa.system.persistence.jpa;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idbsa.system.persistence.jpa.BaseEntity.BaseEntity;
import com.idbsa.system.security.constants.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class User extends BaseEntity implements UserDetails {

    @Column(name ="username")
    private String username;

    @Column(name ="password")
    private String password;


    private boolean isExpired;

    private boolean isLocked;

    private boolean enabled;


    @JoinColumn(name ="group_id")
    @ManyToOne
    @JsonBackReference
    private Group group;

    @ElementCollection(targetClass = UserAuthority.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name = "user_authority")
    @Column(name = "authority")
    private List<UserAuthority> authorities;


    @Override
    public List<UserAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public boolean isNew() {
        return this.getId() == null;
    }

    public Group getGroup() {
        return group;
    }
}
