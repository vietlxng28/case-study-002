package com.vietlong.sandbox.controller.base;

import com.vietlong.sandbox.dto.base.ApiResponse;
import com.vietlong.sandbox.dto.base.BaseDto;
import com.vietlong.sandbox.dto.keysearch.specification.FilterQuery;
import com.vietlong.sandbox.model.base.BaseModel;
import com.vietlong.sandbox.service.base.BaseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<T extends BaseModel, D extends BaseDto<T, D>, F extends FilterQuery<T>> {

    protected abstract BaseService<T, D> getService();

    @PostMapping
    public ResponseEntity<ApiResponse<D>> create(@Valid @RequestBody D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(getService().create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<D>> update(@PathVariable Long id, @Valid @RequestBody D dto) {
        return ResponseEntity.ok(ApiResponse.success(getService().update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.ok(ApiResponse.empty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<D>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(getService().getById(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<D>>> search(F filter) {
        return ResponseEntity.ok(ApiResponse.success(getService().search(filter.toSpecification(), filter.toPageable())));
    }
}
