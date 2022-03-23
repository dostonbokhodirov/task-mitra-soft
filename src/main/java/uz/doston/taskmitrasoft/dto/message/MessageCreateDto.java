package uz.doston.taskmitrasoft.dto.message;

import lombok.*;
import uz.doston.taskmitrasoft.dto.base.BaseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreateDto implements BaseDto {
    private String text;
}
