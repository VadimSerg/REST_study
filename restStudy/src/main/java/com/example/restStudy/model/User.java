package com.example.restStudy.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @Column(name="username")
    @NotBlank(message = "Don't forget to input name")
    private String username;


    @Column(name= "surname")
    @NotBlank(message = "Dont forget to input surname")
    private String surname;

    @Column(name="age")
    @Min(0) @Max(120) @NotNull(message ="Age should be greater than 0")
    private Integer age;


    @Column(name="city")
    @NotBlank(message = "city shouldn't be empty")
    private  String city;

    @Column(name ="password")
    @NotBlank(message = "password shouldn't be empty")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(name ="user_roles" ,
            joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();



    public User() {
    }


    public long getId() {
         return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return username ;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User(@NotBlank(message = "Don't forget to input name") String username, @NotBlank(message = "Dont forget to input surname") String surname, @Min(0) @Max(120) @NotNull(message = "Age should be greater than 0") Integer age, @NotBlank(message = "city shouldn't be empty") String city, @NotBlank(message = "password shouldn't be empty") String password, Set<Role> roles) {
        this.username = username;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, @NotBlank(message = "Don't forget to input name") String username, @NotBlank(message = "Dont forget to input surname") String surname, @Min(0) @Max(120) @NotNull(message = "Age should be greater than 0") Integer age, @NotBlank(message = "city shouldn't be empty") String city, @NotBlank(message = "password shouldn't be empty") String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String surname, Integer age, String city, Set<Role> roles) {
        this.username = username;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.roles = roles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        if (!id.equals(user.id)) return false;
        if (!username.equals(user.username)) return false;
        if (!surname.equals(user.surname)) return false;
        if (!age.equals(user.age)) return false;
        if (!city.equals(user.city)) return false;
        if (!password.equals(user.password)) return false;
        return roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", city=" + city + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role:roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }


        return grantedAuthorities;
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


}
