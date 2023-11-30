package com.example.journaljournalservice.view.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.journaljournalservice.core.entity.Staff;
import com.example.journaljournalservice.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffView {
    private String id;
    private Role role;

}
