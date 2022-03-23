package uz.doston.taskmitrasoft.controller.base;

import lombok.RequiredArgsConstructor;
import uz.doston.taskmitrasoft.service.base.BaseService;

/**
 * @param <S> -> Service
 */

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> implements BaseController {

    protected final S service;

}
