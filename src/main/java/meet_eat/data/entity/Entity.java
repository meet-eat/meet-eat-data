package meet_eat.data.entity;

public abstract class Entity {
    
    private final String identifier;

    protected Entity() {
        this.identifier = null;
    }

    protected Entity(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}