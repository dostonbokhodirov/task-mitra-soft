package uz.doston.taskmitrasoft.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.doston.taskmitrasoft.criteria.base.AbstractCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageCriteria extends AbstractCriteria {
    private String createdAt;
}
