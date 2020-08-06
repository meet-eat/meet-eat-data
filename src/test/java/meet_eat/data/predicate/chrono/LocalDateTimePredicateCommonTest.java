package meet_eat.data.predicate.chrono;

import meet_eat.data.entity.Offer;
import meet_eat.data.factory.OfferFactory;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class LocalDateTimePredicateCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        ChronoLocalDateTimeOperation operation = ChronoLocalDateTimeOperation.BEFORE;
        LocalDateTime reference = LocalDateTime.of(2020, Month.AUGUST, 6, 22, 05);

        // Execution
        LocalDateTimePredicate localDateTimePredicate = new LocalDateTimePredicate(operation, reference);

        // Assertions
        assertNotNull(localDateTimePredicate);
        assertNotNull(localDateTimePredicate.getOperation());
        assertNotNull(localDateTimePredicate.getReferenceValue());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullOperation() {
        // Test data
        LocalDateTime reference = LocalDateTime.of(2020, Month.AUGUST, 6, 22, 05);

        // Execution
        LocalDateTimePredicate localDateTimePredicate = new LocalDateTimePredicate(null, reference);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullReferenceValue() {
        // Test data
        ChronoLocalDateTimeOperation operation = ChronoLocalDateTimeOperation.BEFORE;

        // Execution
        LocalDateTimePredicate localDateTimePredicate = new LocalDateTimePredicate(operation, null);
    }

    @Test
    public void testOperate() {
        // Test data
        ChronoLocalDateTimeOperation operation = ChronoLocalDateTimeOperation.BEFORE;
        LocalDateTime reference = LocalDateTime.of(2020, Month.AUGUST, 6, 22, 05);
        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        offerOne.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0));
        Offer offerTwo = offerFactory.getValidObject();
        offerTwo.setDateTime(LocalDateTime.of(2020, Month.DECEMBER, 1, 12, 0));

        // Execution
        LocalDateTimePredicate localDateTimePredicate = new LocalDateTimePredicate(operation, reference);

        // Assertions
        assertTrue(localDateTimePredicate.test(offerOne));
        assertFalse(localDateTimePredicate.test(offerTwo));
    }
}
