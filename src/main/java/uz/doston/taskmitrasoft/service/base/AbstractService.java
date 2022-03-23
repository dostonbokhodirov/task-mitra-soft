package uz.doston.taskmitrasoft.service.base;


import lombok.RequiredArgsConstructor;
import uz.doston.taskmitrasoft.mapper.base.BaseMapper;
import uz.doston.taskmitrasoft.repository.base.BaseRepository;
import uz.doston.taskmitrasoft.validator.base.BaseValidator;

/**
 * @param <M> -> Mapper
 * @param <V> -> Validator
 * @param <R> -> Repository
 */

@RequiredArgsConstructor
public abstract class AbstractService<
        M extends BaseMapper,
        V extends BaseValidator,
        R extends BaseRepository> implements BaseService {

    protected final M mapper;

    protected final V validator;

    protected final R repository;

}
