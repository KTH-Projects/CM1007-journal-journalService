package com.example.journaljournalservice.view.entity;

import com.example.journaljournalservice.core.entity.Diagnosis;
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
    private String name;
    private int age;
    private String sex;
    private Role role;

    public static StaffView convert(Staff staff){
        StaffView s = new StaffView();
        s.setId(staff.getId());
        s.setAge(staff.getAge());
        s.setRole(staff.getRole());
        s.setSex(staff.getSex());
        s.setName(staff.getName());
        return s;
    }

}
