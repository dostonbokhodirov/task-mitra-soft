package uz.doston.taskmitrasoft.criteria.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCriteria implements BaseCriteria {
    protected Integer size;
    protected Integer page;
}
