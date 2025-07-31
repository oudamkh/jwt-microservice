package com.wingbank.acccount.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // Only users with USER or ADMIN role can access
    public String getProducts(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName(); // Get username from Principal
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .reduce((a, b) -> a + ", " + b)
                .orElse("No roles");
        return "Product List for " + username + " (Roles: " + roles + "): Product A, Product B, Product C";
    }

    @GetMapping("/admin-only")
    @PreAuthorize("hasRole('ADMIN')") // Only users with ADMIN role can access
    public String getAdminProducts() {
        return "Admin-only product details: Sensitive data.";
    }
}