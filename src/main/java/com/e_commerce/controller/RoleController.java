package com.e_commerce.controller;

import com.e_commerce.dto.ResponseDto;
import com.e_commerce.model.Role;
import com.e_commerce.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping("/find-role-by-fc")
    public ResponseEntity<?> getRoleByFiscalCode (@RequestParam String fiscalCode) {

        List<Role> roles = userRoleRepository.findRoleByFiscalCode(fiscalCode);

        return new ResponseEntity<>(new ResponseDto<>(roles, true), HttpStatus.OK);
    }
}
