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
import uz.doston.taskmitrasoft.mapper.MessageMapper;
import uz.doston.taskmitrasoft.repository.MessageRepository;
import uz.doston.taskmitrasoft.response.AppErrorDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;
import uz.doston.taskmitrasoft.service.base.AbstractService;
import uz.doston.taskmitrasoft.service.base.GenericCrudService;
import uz.doston.taskmitrasoft.validator.MessageValidator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MessageService
        extends AbstractService<MessageMapper, MessageValidator, MessageRepository>
        implements GenericCrudService<MessageDto, MessageCreateDto, MessageUpdateDto, MessageCriteria> {

    public MessageService(MessageMapper mapper, MessageValidator validator, MessageRepository repository) {
        super(mapper, validator, repository);
    }


    @Override
    public ResponseEntity<DataDto<Long>> create(MessageCreateDto dto) {
        try {
            validator.validOnCreate(dto);
            Message message = mapper.fromCreateDto(dto);
            Message save = repository.save(message);
            return new ResponseEntity<>(new DataDto<>(save.getId()));
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
            Optional<Message> optional = repository.findById(dto.getId());
            if (optional.isPresent()) {
                Message message = mapper.fromUpdateDto(dto, optional.get());
                Message save = repository.save(message);
                return new ResponseEntity<>(new DataDto<>(save.getId()));
            } else return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Message is not found")));
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            validator.validOnId(id);
            if (repository.existsById(id)) repository.deleteById(id);
            else return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Message is not found")));
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        }
        return new ResponseEntity<>(new DataDto<>(true));
    }

    @Override
    public ResponseEntity<DataDto<MessageDto>> get(Long id) {
        try {
            validator.validOnId(id);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
        Optional<Message> message = repository.findById(id);
        if (message.isPresent()) {
            MessageDto messageDto = mapper.toDto(message.get());
            return new ResponseEntity<>(new DataDto<>(messageDto));
        } else {
            return new ResponseEntity<>(new DataDto<>
                    (new AppErrorDto(HttpStatus.NOT_FOUND, "Message is not found")));
        }
    }

    @Override
    public ResponseEntity<DataDto<List<MessageDto>>> getAll(MessageCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by("createdAt").descending());
        List<Message> messageList;
        if (Objects.isNull(criteria.getCreatedAt())) messageList = repository.findAll(pageable).getContent();
        else try {
            LocalDate createdAt = LocalDate.parse(criteria.getCreatedAt());
            messageList = repository.findAllByCreatedAt(createdAt, pageable);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
        List<MessageDto> messageDtoList = mapper.toDto(messageList);
        return new ResponseEntity<>(new DataDto<>(messageDtoList, (long) messageDtoList.size()));
    }
}
