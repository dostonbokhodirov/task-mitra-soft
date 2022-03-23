package uz.doston.taskmitrasoft.dto.message;

import lombok.*;
import uz.doston.taskmitrasoft.dto.base.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageUpdateDto extends GenericDto {
    private String text;
}
