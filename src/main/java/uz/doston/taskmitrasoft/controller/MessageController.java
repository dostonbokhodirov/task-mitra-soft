package uz.doston.taskmitrasoft.controller;

import org.springframework.web.bind.annotation.*;
import uz.doston.taskmitrasoft.controller.base.AbstractController;
import uz.doston.taskmitrasoft.controller.base.GenericController;
import uz.doston.taskmitrasoft.controller.base.GenericCrudController;
import uz.doston.taskmitrasoft.criteria.MessageCriteria;
import uz.doston.taskmitrasoft.dto.message.MessageCreateDto;
import uz.doston.taskmitrasoft.dto.message.MessageDto;
import uz.doston.taskmitrasoft.dto.message.MessageUpdateDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;
import uz.doston.taskmitrasoft.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController
        extends AbstractController<MessageService>
        implements GenericCrudController<MessageDto, MessageCreateDto, MessageUpdateDto, MessageCriteria> {

    public MessageController(MessageService service) {
        super(service);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(MessageCreateDto dto) {
        return service.create(dto);
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDto<Long>> update(MessageUpdateDto dto) {
        return service.update(dto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<MessageDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<DataDto<List<MessageDto>>> getAll(MessageCriteria criteria) {
        return service.getAll(criteria);
    }

}
