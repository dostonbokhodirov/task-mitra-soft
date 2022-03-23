package uz.doston.taskmitrasoft.service.base;

import uz.doston.taskmitrasoft.criteria.base.BaseCriteria;
import uz.doston.taskmitrasoft.dto.base.BaseDto;
import uz.doston.taskmitrasoft.dto.base.GenericDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;

/**
 * @param <D> -> DTO
 * @param <CD> -> Create DTO
 * @param <UD> -> Update DTO
 * @param <C> -> Criteria
 */

public interface GenericCrudService<
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto,
        C extends BaseCriteria> extends GenericService<D, C> {

    ResponseEntity<DataDto<Long>> create(CD dto);

    ResponseEntity<DataDto<Long>> update(UD dto);

    ResponseEntity<DataDto<Boolean>> delete(Long id);

}
