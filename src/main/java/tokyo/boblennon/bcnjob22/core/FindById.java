package tokyo.boblennon.bcnjob22.core;

import java.util.Optional;

public interface FindById<T, ID> {
    public Optional<T> findById(ID id);
}