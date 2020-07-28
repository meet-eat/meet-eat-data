package meet_eat.data.factory;

import meet_eat.data.entity.Tag;

public class TagFactory extends ObjectFactory<Tag> {

    @Override
    protected Tag createObject() {
        return new Tag("TestTag" + Integer.toString(objectCounter));
    }
}
