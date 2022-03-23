package uz.doston.taskmitrasoft.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.taskmitrasoft.entity.Message;
import uz.doston.taskmitrasoft.repository.base.BaseRepository;

import java.time.LocalDate;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>, BaseRepository {

    List<Message> findAllByCreatedAt(LocalDate createdAt, Pageable pageable);

}
