package com.vietlong.sandbox.dto.base;

public interface BaseDto<T, D> {
    T toEntity();
    D fromEntity(T entity);
}
