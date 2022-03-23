package uz.doston.taskmitrasoft.dto.message;

import lombok.*;
import uz.doston.taskmitrasoft.dto.base.GenericDto;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto extends GenericDto {
    private LocalDate createdAt;
    private String text;
}
