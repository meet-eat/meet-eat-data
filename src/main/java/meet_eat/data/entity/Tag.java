package meet_eat.data.entity;

public class Tag extends Entity {
    
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(String identifier, String name) {
        super(identifier);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}