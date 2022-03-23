package uz.doston.taskmitrasoft.mapper.base;

import org.mapstruct.MappingTarget;
import uz.doston.taskmitrasoft.dto.base.BaseDto;
import uz.doston.taskmitrasoft.dto.base.GenericDto;
import uz.doston.taskmitrasoft.entity.base.BaseEntity;

import java.util.List;

/**
 * @param <E> -> Entity
 * @param <D> -> DTO
 * @param <CD> -> Create DTO
 * @param <UD> -> Update DTO
 */

public interface GenericMapper<
        E extends BaseEntity,
        D extends BaseDto,
        CD extends BaseDto,
        UD extends GenericDto> extends BaseMapper {

    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E fromUpdateDto(UD dto, @MappingTarget E entity);

    E fromCreateDto(CD dto);

}
