package meet_eat.data.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntityCommonTest {

    private class ConcreteEntity extends Entity<String> {

        public ConcreteEntity() {
            super();
        }

        public ConcreteEntity(String identifier) {
            super(identifier);
        }
    }

    @Test
    public void testConstructorEmpty() {   
        // Execution
        ConcreteEntity entity = new ConcreteEntity();

        // Assertions
        assertNotNull(entity);
    }

    @Test
    public void testConstructorIdentifier() {   
        // Test data
        String identifier = "IdentifierTest1234!";

        // Execution
        ConcreteEntity entity = new ConcreteEntity(identifier);

        // Assertions
        assertEquals(identifier, entity.getIdentifier());
    }

    @Test
    public void testEquals() {
        // Execution
        ConcreteEntity entity = new ConcreteEntity("Identifier");
        ConcreteEntity entityCopy = new ConcreteEntity(entity.getIdentifier());

        // Assertions
        assertTrue(entity.equals(entity));
        assertFalse(entity.equals(null));
        assertFalse(entity.equals(new Object()));
        assertTrue(entity.equals(entityCopy));
        assertEquals(entity.hashCode(), entityCopy.hashCode());
    }
}