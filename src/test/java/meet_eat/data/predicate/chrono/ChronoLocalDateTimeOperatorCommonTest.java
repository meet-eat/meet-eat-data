package meet_eat.data.predicate.chrono;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChronoLocalDateTimeOperatorCommonTest {

    private class ChronoLocalDateTimeOperatorMock extends ChronoLocalDateTimeOperator {

        public ChronoLocalDateTimeOperatorMock(ChronoLocalDateTimeOperation operation, ChronoLocalDateTime<?> referenceValue) {
            super(operation, referenceValue);
        }
    }

    @Test
    public void testBefore() {
        // Test data
        ChronoLocalDateTimeOperation before = ChronoLocalDateTimeOperation.BEFORE;
        LocalDateTime reference = LocalDateTime.of(2020, Month.AUGUST, 6, 15, 30);
        LocalDateTime dateTimeOne = LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0);
        LocalDateTime dateTimeTwo = LocalDateTime.of(2020, Month.AUGUST, 6, 15, 30);
        LocalDateTime dateTimeThree = LocalDateTime.of(2100, Month.OCTOBER, 16, 12, 0);

        // Execution
        ChronoLocalDateTimeOperatorMock operator = new ChronoLocalDateTimeOperatorMock(before, reference);

        // Assertions
        assertTrue(operator.operate(dateTimeOne));
        assertFalse(operator.operate(dateTimeTwo));
        assertFalse(operator.operate(dateTimeThree));
    }

    @Test
    public void testEqual() {
        // Test data
        ChronoLocalDateTimeOperation equal = ChronoLocalDateTimeOperation.EQUAL;
        LocalDateTime reference = LocalDateTime.of(2020, Month.AUGUST, 6, 15, 30);
        LocalDateTime dateTimeOne = LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0);
        LocalDateTime dateTimeTwo = LocalDateTime.of(2020, Month.AUGUST, 6, 15, 30);
        LocalDateTime dateTimeThree = LocalDateTime.of(2100, Month.OCTOBER, 16, 12, 0);

        // Execution
        ChronoLocalDateTimeOperatorMock operator = new ChronoLocalDateTimeOperatorMock(equal, reference);

        // Assertions
        assertFalse(operator.operate(dateTimeOne));
        assertTrue(operator.operate(dateTimeTwo));
        assertFalse(operator.operate(dateTimeThree));
    }

    @Test
    public void testAfter() {
        // Test data
        ChronoLocalDateTimeOperation after = ChronoLocalDateTimeOperation.AFTER;
        LocalDateTime reference = LocalDateTime.of(2020, Month.AUGUST, 6, 15, 30);
        LocalDateTime dateTimeOne = LocalDateTime.of(2020, Month.JANUARY, 1, 12, 0);
        LocalDateTime dateTimeTwo = LocalDateTime.of(2020, Month.AUGUST, 6, 15, 30);
        LocalDateTime dateTimeThree = LocalDateTime.of(2100, Month.OCTOBER, 16, 12, 0);

        // Execution
        ChronoLocalDateTimeOperatorMock operator = new ChronoLocalDateTimeOperatorMock(after, reference);

        // Assertions
        assertFalse(operator.operate(dateTimeOne));
        assertFalse(operator.operate(dateTimeTwo));
        assertTrue(operator.operate(dateTimeThree));
    }
}
