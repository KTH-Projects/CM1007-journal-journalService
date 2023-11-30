package com.example.journaljournalservice.core.entity;

import com.example.journaljournalservice.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    private String id;
    private Role role;

}
