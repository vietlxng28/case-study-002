package com.vietlong.sandbox.service.base;

import com.vietlong.sandbox.dto.base.BaseDto;
import com.vietlong.sandbox.exception.AppException;
import com.vietlong.sandbox.exception.ErrorCode;
import com.vietlong.sandbox.model.base.BaseModel;
import com.vietlong.sandbox.repository.base.BaseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

@Transactional(readOnly = true)
public abstract class BaseService<T extends BaseModel, D extends BaseDto<T, D>> {

    protected abstract BaseRepository<T> getRepository();

    protected abstract D getDtoHelper();

    @Transactional
    public D create(D dto) {
        T entity = dto.toEntity();
        entity.setId(null);
        entity.setIsDeleted(false);
        T saved = getRepository().save(entity);
        return getDtoHelper().fromEntity(saved);
    }

    @Transactional
    public D update(Long id, D dto) {
        T existing = getRepository().findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECORD_NOT_FOUND, id));

        T entity = dto.toEntity();
        BeanUtils.copyProperties(entity, existing, getNullPropertyNames(entity));
        existing.setId(id);
        T saved = getRepository().save(existing);
        return getDtoHelper().fromEntity(saved);
    }

    @Transactional
    public void delete(Long id) {
        T existing = getRepository().findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECORD_NOT_FOUND, id));

        existing.setIsDeleted(true);
        getRepository().save(existing);
    }

    public D getById(Long id) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECORD_NOT_FOUND, id));
        return getDtoHelper().fromEntity(entity);
    }

    public Page<D> search(Specification<T> spec, Pageable pageable) {
        return getRepository().findAll(spec, pageable).map(entity -> getDtoHelper().fromEntity(entity));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }
}
