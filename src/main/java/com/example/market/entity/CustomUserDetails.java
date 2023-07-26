package com.example.market.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
// UserDto 와 같은 역할을 하는 것
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String userId;
    private String password;
    private String phone;
    private String email;
    private String address;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getUserId();
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

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public static CustomUserDetails fromEntity(UserEntity entity) {
        CustomUserDetails details = new CustomUserDetails();
        details.id = entity.getId();
        details.password = entity.getPassword();
        details.email = entity.getEmail();
        details.phone = entity.getPhone();
        details.address = entity.getAddress();
        return details;
    }
    public UserEntity newEntity(){
        UserEntity entity = new UserEntity();
        entity.setUserId(userId);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setAddress(address);
        return entity;
    }
}
