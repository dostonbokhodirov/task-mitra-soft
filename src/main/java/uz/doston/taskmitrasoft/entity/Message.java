package uz.doston.taskmitrasoft.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import uz.doston.taskmitrasoft.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(schema = "message", name = "message")
public class Message implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP default NOW()")
    private LocalDate createdAt;

    @Column(nullable = false)
    private String text;

}
