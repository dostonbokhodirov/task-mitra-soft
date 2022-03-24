package uz.doston.taskmitrasoft.service.base;

import lombok.RequiredArgsConstructor;
import uz.doston.taskmitrasoft.repository.base.BaseRepository;

/**
 * @param <R> -> Repository
 */

@RequiredArgsConstructor
public abstract class AbstractServerService<R extends BaseRepository> {

    protected final R repository;

}
