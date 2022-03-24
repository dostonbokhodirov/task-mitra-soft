package uz.doston.taskmitrasoft.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.doston.taskmitrasoft.controller.base.AbstractController;
import uz.doston.taskmitrasoft.controller.base.GenericController;
import uz.doston.taskmitrasoft.criteria.MessageCriteria;
import uz.doston.taskmitrasoft.dto.message.MessageDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;
import uz.doston.taskmitrasoft.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/message")
public class ServerController extends AbstractController<MessageService>
        implements GenericController<MessageDto, MessageCriteria> {

    public ServerController(MessageService service) {
        super(service);
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
