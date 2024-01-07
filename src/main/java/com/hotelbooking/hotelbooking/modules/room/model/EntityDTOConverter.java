package com.hotelbooking.hotelbooking.modules.room.model;
import org.springframework.beans.BeanUtils;

public class EntityDTOConverter {

    public static <E, D> D convertToDTO(E entity, Class<D> dtoClass) {
        D dto = null;
        try {
            dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
        } catch (Exception e) {
            // Handle exception, e.g., logging or throwing a runtime exception
            e.printStackTrace();
        }
        return dto;
    }

    public static <E, D> E convertToEntity(D dto, Class<E> entityClass) {
        E entity = null;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
        } catch (Exception e) {
            // Handle exception, e.g., logging or throwing a runtime exception
            e.printStackTrace();
        }
        return entity;
    }
}
