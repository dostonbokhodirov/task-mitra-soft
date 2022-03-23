package uz.doston.taskmitrasoft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import uz.doston.taskmitrasoft.response.AppErrorDto;
import uz.doston.taskmitrasoft.response.DataDto;
import uz.doston.taskmitrasoft.response.ResponseEntity;

@ControllerAdvice("uz.doston")
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handle500(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), webRequest)));
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handle400(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handle404(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
    }

}
