package com.example.journaljournalservice.view.entity;

import com.example.journaljournalservice.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    private String name;
    private int age;
    private String sex;
    private String role;
}
