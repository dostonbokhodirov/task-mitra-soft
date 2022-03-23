package uz.doston.taskmitrasoft.validator;

import org.springframework.stereotype.Component;
import uz.doston.taskmitrasoft.dto.message.MessageCreateDto;
import uz.doston.taskmitrasoft.dto.message.MessageUpdateDto;
import uz.doston.taskmitrasoft.exceptions.BadRequestException;
import uz.doston.taskmitrasoft.validator.base.GenericValidator;

import java.util.Objects;

@Component
public class MessageValidator
        implements GenericValidator<MessageCreateDto, MessageUpdateDto, Long> {

    @Override
    public void validOnUpdate(MessageUpdateDto dto) {
        validOnId(dto.getId());
        validOnText(dto.getText());
    }

    @Override
    public void validOnCreate(MessageCreateDto dto) {
        validOnText(dto.getText());
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new BadRequestException("Message id must not be null");
        }
    }

    private void validOnText(String text) {
        if (Objects.isNull(text)) {
            throw new BadRequestException("Message text must not be null");
        }
    }
}
