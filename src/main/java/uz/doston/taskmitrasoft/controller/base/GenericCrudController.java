package uz.doston.taskmitrasoft.controller.base;


import uz.doston.taskmitrasoft.criteria.base.BaseCriteria;
import uz.doston.taskmitrasoft.dto.base.BaseDto;
import uz.doston.taskmitrasoft.dto.base.GenericDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;

/**
 * @param <CD> -> Create DTO
 * @param <UD> -> Update DTO
 */

public interface GenericCrudController<
        CD extends BaseDto,
        UD extends GenericDto> {

    ResponseEntity<DataDto<Long>> create(CD dto);

    ResponseEntity<DataDto<Long>> update(UD dto);

    ResponseEntity<DataDto<Boolean>> delete(Long id);

}
