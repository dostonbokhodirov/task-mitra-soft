package uz.doston.taskmitrasoft.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.doston.taskmitrasoft.criteria.MessageCriteria;
import uz.doston.taskmitrasoft.dto.message.MessageCreateDto;
import uz.doston.taskmitrasoft.dto.message.MessageDto;
import uz.doston.taskmitrasoft.dto.message.MessageUpdateDto;
import uz.doston.taskmitrasoft.entity.Message;
import uz.doston.taskmitrasoft.exceptions.BadRequestException;
import uz.doston.taskmitrasoft.exceptions.NotFoundException;
import uz.doston.taskmitrasoft.mapper.MessageMapper;
import uz.doston.taskmitrasoft.response.AppErrorDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;
import uz.doston.taskmitrasoft.service.base.AbstractService;
import uz.doston.taskmitrasoft.service.base.GenericCrudService;
import uz.doston.taskmitrasoft.validator.MessageValidator;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;


@Service
public class MessageService
        extends AbstractService<MessageMapper, MessageValidator, ServerService>
        implements GenericCrudService<MessageDto, MessageCreateDto, MessageUpdateDto, MessageCriteria> {

    public MessageService(MessageMapper mapper, MessageValidator validator, ServerService service) {
        super(mapper, validator, service);
    }


    @Override
    public ResponseEntity<DataDto<Long>> create(MessageCreateDto dto) {
        try {
            validator.validOnCreate(dto);
            Message message = mapper.fromCreateDto(dto);
            Long id = service.save(message);
            return new ResponseEntity<>(new DataDto<>(id));
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(MessageUpdateDto dto) {
        try {
            validator.validOnUpdate(dto);
            Message foundMessage = service.findById(dto.getId());
            Message message = mapper.fromUpdateDto(dto, foundMessage);
            Long id = service.save(message);
            return new ResponseEntity<>(new DataDto<>(id));
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        } catch (NotFoundException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            validator.validOnId(id);
            service.deleteById(id);
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        } catch (NotFoundException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage())));
        }
        return new ResponseEntity<>(new DataDto<>(true));
    }

    @Override
    public ResponseEntity<DataDto<MessageDto>> get(Long id) {
        try {
            validator.validOnId(id);
            Message message = service.findById(id);
            MessageDto messageDto = mapper.toDto(message);
            return new ResponseEntity<>(new DataDto<>(messageDto));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new DataDto<>
                    (new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<List<MessageDto>>> getAll(MessageCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by("createdAt").descending());
        List<Message> messageList;
        if (Objects.isNull(criteria.getCreatedAt())) messageList = service.findAll(pageable);
        else try {
            messageList = service.findAllByCreatedAt(criteria.getCreatedAt(), pageable);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
        List<MessageDto> messageDtoList = mapper.toDto(messageList);
        return new ResponseEntity<>(new DataDto<>(messageDtoList, (long) messageDtoList.size()));
    }
}
