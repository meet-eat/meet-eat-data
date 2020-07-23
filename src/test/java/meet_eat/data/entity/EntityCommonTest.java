package meet_eat.data.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

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
}