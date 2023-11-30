package com.example.journaljournalservice.persistance.entity;

import jakarta.persistence.*;
import com.example.journaljournalservice.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staff")
public class StaffDB {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @Column
    private Role role;

}
