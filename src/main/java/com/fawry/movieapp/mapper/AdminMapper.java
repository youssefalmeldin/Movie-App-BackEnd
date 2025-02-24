package com.fawry.movieapp.mapper;

import com.fawry.movieapp.dal.model.Admin;
import com.fawry.movieapp.dto.AdminDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminDTO toDTO(Admin admin);

    Admin toEntity(AdminDTO adminDTO);
}
