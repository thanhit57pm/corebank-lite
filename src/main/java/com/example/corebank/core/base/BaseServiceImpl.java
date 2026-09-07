package com.example.corebank.core.base;

import com.example.corebank.common.exception.BusinessException;
import com.example.corebank.common.exception.ErrorCode;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl<T extends BaseEntity, ID> implements BaseService<T, ID> {

    protected final JpaRepository<T, ID> repository;
    private final String entityName;

    protected BaseServiceImpl(JpaRepository<T, ID> repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND,
                        "%s không tồn tại với id: %s".formatted(entityName, id)));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND,
                    "%s không tồn tại với id: %s".formatted(entityName, id));
        }
        repository.deleteById(id);
    }
}
