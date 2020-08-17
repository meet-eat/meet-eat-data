package meet_eat.data.entity.relation;

import meet_eat.data.entity.Offer;
import meet_eat.data.entity.ReportableEntity;
import meet_eat.data.entity.user.User;
import meet_eat.data.factory.OfferFactory;
import meet_eat.data.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EntityRelationCommonTest {

    private class ConcreteEntityRelation extends EntityRelation<User, ReportableEntity<String>, String> {

        public ConcreteEntityRelation(User source, ReportableEntity<String> target) {
            super(source, target);
        }

        protected ConcreteEntityRelation(String identifier, User source, ReportableEntity<String> target) {
            super(identifier, source, target);
        }
    }

    @Test
    public void testConstructorReportableUser() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User source = userFactory.getValidObject();
        User target = userFactory.getValidObject();

        // Execution
        ConcreteEntityRelation entityRelation = new ConcreteEntityRelation(source, target);

        // Assertions
        assertNotNull(entityRelation);
        assertNotNull(entityRelation.getSource());
        assertNotNull(entityRelation.getTarget());
        assertEquals(source, entityRelation.getSource());
        assertEquals(target, entityRelation.getTarget());
    }

    @Test
    public void testConstructorReportableOffer() {
        // Test data
        User source = new UserFactory().getValidObject();
        Offer target = new OfferFactory().getValidObject();

        // Execution
        ConcreteEntityRelation entityRelation = new ConcreteEntityRelation(source, target);

        // Assertions
        assertNotNull(entityRelation);
        assertNotNull(entityRelation.getSource());
        assertNotNull(entityRelation.getTarget());
        assertEquals(source, entityRelation.getSource());
        assertEquals(target, entityRelation.getTarget());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullSource() {
        // Test data
        User target = new UserFactory().getValidObject();

        // Execution
        ConcreteEntityRelation entityRelation = new ConcreteEntityRelation(null, target);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullTarget() {
        // Test data
        User source = new UserFactory().getValidObject();

        // Execution
        ConcreteEntityRelation entityRelation = new ConcreteEntityRelation(source, null);
    }

    @Test
    public void testJsonConstructor() {
        // Test data
        String identifier = "sdf0432023rf";
        UserFactory userFactory = new UserFactory();
        User source = userFactory.getValidObject();
        User target = userFactory.getValidObject();

        // Execution
        ConcreteEntityRelation entityRelation = new ConcreteEntityRelation(identifier, source, target);

        // Assertions
        assertNotNull(entityRelation);
        assertNotNull(entityRelation.getIdentifier());
        assertNotNull(entityRelation.getSource());
        assertNotNull(entityRelation.getTarget());
        assertEquals(identifier, entityRelation.getIdentifier());
        assertEquals(source, entityRelation.getSource());
        assertEquals(target, entityRelation.getTarget());
    }
}
