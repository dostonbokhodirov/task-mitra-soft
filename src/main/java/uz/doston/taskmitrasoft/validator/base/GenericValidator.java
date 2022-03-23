package uz.doston.taskmitrasoft.validator.base;

import uz.doston.taskmitrasoft.dto.base.BaseDto;
import uz.doston.taskmitrasoft.dto.base.GenericDto;

import java.io.Serializable;

public interface GenericValidator<
        CD extends BaseDto,
        UD extends GenericDto,
        I extends Serializable> extends BaseValidator {

    void validOnUpdate(UD ud);

    void validOnCreate(CD cd);

    void validOnId(I id);

}
