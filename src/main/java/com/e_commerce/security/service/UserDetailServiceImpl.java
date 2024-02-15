package com.e_commerce.security.service;

import com.e_commerce.model.Role;
import com.e_commerce.model.UserData;
import com.e_commerce.repository.UserDataRepository;
import com.e_commerce.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData userData = userDataRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        List<Role> roles =userRoleRepository.findRoleByFiscalCode(userData.getFiscalCode());
        return new User(userData.getUsername(), userData.getPassword(), mapRolesToAuthorities(roles));
    }

    /**
     * Metodo di utility che mi permette di convertire le role in GRANTED AUTHORITIES (autorità concesse)
     * perchè nel metodo loadUserByUsername lo User ha come parametri del costruttore
     * User(String username, String password, Collection<GrantedAuthority> authorities)
     * quindi queste role andranno inserite nella Collection
     *
     * @param roles
     * @return Collection<GrantedAuthority>
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
