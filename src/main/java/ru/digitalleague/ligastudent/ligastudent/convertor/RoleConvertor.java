package ru.digitalleague.ligastudent.ligastudent.convertor;

import ru.digitalleague.ligastudent.ligastudent.dto.RoleDTO;
import ru.digitalleague.ligastudent.ligastudent.model.Role;

public class RoleConvertor {

    public static RoleDTO fromRole(Role role){
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
}
