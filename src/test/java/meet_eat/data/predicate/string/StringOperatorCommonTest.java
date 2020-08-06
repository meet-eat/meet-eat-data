package meet_eat.data.predicate.string;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringOperatorCommonTest {

    private class StringOperatorMock extends StringOperator {

        protected StringOperatorMock(StringOperation operation, String referenceValue) {
            super(operation, referenceValue);
        }
    }

    @Test
    public void testContain() {
        // Test data
        StringOperation contain = StringOperation.CONTAIN;
        String reference = "This is my reference.";
        String valueOne = "reference";
        String valueTwo = "my reference";
        String valueThree = "Hello";

        // Execution
        StringOperatorMock operator = new StringOperatorMock(contain, reference);

        // Assertions
        assertTrue(operator.operate(valueOne));
        assertTrue(operator.operate(valueTwo));
        assertFalse(operator.operate(valueThree));
    }

    @Test
    public void testNotContain() {
        // Test data
        StringOperation notContain = StringOperation.NOT_CONTAIN;
        String reference = "This is my reference.";
        String valueOne = "XYZ";
        String valueTwo = "TEST";
        String valueThree = "This";

        // Execution
        StringOperatorMock operator = new StringOperatorMock(notContain, reference);

        // Assertions
        assertTrue(operator.operate(valueOne));
        assertTrue(operator.operate(valueTwo));
        assertFalse(operator.operate(valueThree));
    }

    @Test
    public void testEqual() {
        // Test data
        StringOperation equal = StringOperation.EQUAL;
        String reference = "This is my reference.";
        String valueOne = "This is my reference.";
        String valueTwo = "This is my";

        // Execution
        StringOperatorMock operator = new StringOperatorMock(equal, reference);

        // Assertions
        assertTrue(operator.operate(reference));
        assertTrue(operator.operate(valueOne));
        assertFalse(operator.operate(valueTwo));
    }

    @Test
    public void testStartsWith() {
        // Test data
        StringOperation startsWith = StringOperation.STARTS_WITH;
        String reference = "This is my reference.";
        String valueOne = "Thi";
        String valueTwo = "That shouldn't work.";

        // Execution
        StringOperatorMock operator = new StringOperatorMock(startsWith, reference);

        // Assertions
        assertTrue(operator.operate(reference));
        assertTrue(operator.operate(valueOne));
        assertFalse(operator.operate(valueTwo));
    }

    @Test
    public void testEndsWith() {
        // Test data
        StringOperation endsWith = StringOperation.ENDS_WITH;
        String reference = "This is my reference.";
        String valueOne = "e.";
        String valueTwo = "That shouldn't work.";

        // Execution
        StringOperatorMock operator = new StringOperatorMock(endsWith, reference);

        // Assertions
        assertTrue(operator.operate(reference));
        assertTrue(operator.operate(valueOne));
        assertFalse(operator.operate(valueTwo));
    }
}
