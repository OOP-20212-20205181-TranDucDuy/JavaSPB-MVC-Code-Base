package com.example.javacodebase.model;

import com.example.javacodebase.model.enums.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Role {
    @Enumerated(EnumType.STRING)
    private RoleType name;
}
