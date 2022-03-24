package uz.doston.taskmitrasoft.controller;

import org.springframework.web.bind.annotation.*;
import uz.doston.taskmitrasoft.controller.base.AbstractController;
import uz.doston.taskmitrasoft.controller.base.GenericCrudController;
import uz.doston.taskmitrasoft.dto.message.MessageCreateDto;
import uz.doston.taskmitrasoft.dto.message.MessageUpdateDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;
import uz.doston.taskmitrasoft.service.MessageService;

@RestController
@RequestMapping("/message")
public class ClientController
        extends AbstractController<MessageService>
        implements GenericCrudController<MessageCreateDto, MessageUpdateDto> {

    public ClientController(MessageService service) {
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

}
