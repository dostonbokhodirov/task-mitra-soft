package uz.doston.taskmitrasoft.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.doston.taskmitrasoft.entity.Message;
import uz.doston.taskmitrasoft.exceptions.NotFoundException;
import uz.doston.taskmitrasoft.repository.MessageRepository;
import uz.doston.taskmitrasoft.service.base.AbstractServerService;
import uz.doston.taskmitrasoft.service.base.BaseService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServerService extends AbstractServerService<MessageRepository> implements BaseService {

    public ServerService(MessageRepository repository) {
        super(repository);
    }

    public Long save(Message message) {
        Message save = repository.save(message);
        return save.getId();
    }

    public Message findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Message not found"));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) repository.deleteById(id);
        else throw new NotFoundException("Message not found");
    }

    public List<Message> findAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public List<Message> findAllByCreatedAt(String createdAt, Pageable pageable) {
        LocalDate date = LocalDate.parse(createdAt);
        return repository.findAllByCreatedAt(date, pageable);
    }
}
