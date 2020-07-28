package meet_eat.data.factory;

public abstract class ObjectFactory<T> {

    protected int objectCounter;

    public T getValidObject() {
        T object = createObject();
        objectCounter++;
        return object;
    }

    protected abstract T createObject();
}
