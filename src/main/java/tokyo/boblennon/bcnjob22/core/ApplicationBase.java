package tokyo.boblennon.bcnjob22.core;

import tokyo.boblennon.bcnjob22.core.execptions.NotFoundException;

public abstract class ApplicationBase<T, ID> {
    private FindById<T, ID> findById;

    protected T findById(ID id) {
        T t = this.findById.findById(id).orElseThrow(() -> {
            throw new NotFoundException();
        });
        return t;
    }

    protected ApplicationBase(FindById<T, ID> findById) {
        this.findById = findById;
    }

    protected String serializeObject(T t, String msg) {
        return String.format("%s has been %s without incidents.", t.toString(), msg);
    }
}