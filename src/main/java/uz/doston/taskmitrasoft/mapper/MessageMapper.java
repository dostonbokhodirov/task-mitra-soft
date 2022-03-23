package uz.doston.taskmitrasoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import uz.doston.taskmitrasoft.dto.message.MessageCreateDto;
import uz.doston.taskmitrasoft.dto.message.MessageDto;
import uz.doston.taskmitrasoft.dto.message.MessageUpdateDto;
import uz.doston.taskmitrasoft.entity.Message;
import uz.doston.taskmitrasoft.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper extends GenericMapper<Message, MessageDto, MessageCreateDto, MessageUpdateDto> {

    MessageDto toDto(Message message);

    List<MessageDto> toDto(List<Message> messages);

    Message fromUpdateDto(MessageUpdateDto dto, @MappingTarget Message message);

    Message fromCreateDto(MessageCreateDto dto);

}
