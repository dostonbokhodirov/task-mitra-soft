package uz.doston.taskmitrasoft.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * @param <T> -> Data
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto<T> implements Serializable {

    private T data;

    private AppErrorDto error;

    private boolean success;

    private Long totalCount;

    public DataDto(T data) {
        this(data, 1L);
    }

    public DataDto(AppErrorDto error) {
        this.error = error;
        this.success = false;
    }

    public DataDto(T data, Long totalCount) {
        this.data = data;
        this.success = true;
        this.totalCount = totalCount;
    }

}
