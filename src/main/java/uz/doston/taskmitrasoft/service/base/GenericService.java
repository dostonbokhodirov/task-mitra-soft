package uz.doston.taskmitrasoft.service.base;

import uz.doston.taskmitrasoft.criteria.base.BaseCriteria;
import uz.doston.taskmitrasoft.dto.base.GenericDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;

import java.util.List;

/**
 * @param <D> -> DTO
 * @param <C> -> Criteria
 */

public interface GenericService<D extends GenericDto, C extends BaseCriteria> {

    ResponseEntity<DataDto<D>> get(Long id);

    ResponseEntity<DataDto<List<D>>> getAll(C criteria);

}
