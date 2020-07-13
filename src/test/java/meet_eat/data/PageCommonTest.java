package meet_eat.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PageCommonTest {

    @Test
    public void testConstructor() {   
        // Test data
        int index = Page.INDEX_LOWER_BOUND;
        int size = Page.SIZE_LOWER_BOUND;

        // Execution
        Page page = new Page(index, size);

        // Assertions
        assertEquals(index, page.getIndex());
        assertEquals(size, page.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndexOutOfBounds() {
        // Test data
        int index = Page.LOWER_BOUND_INDEX - 1;
        int size = Page.LOWER_BOUND_SIZE;
        
        // Execution
        Page page = new Page(index, size);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSizeOutOfBounds() {
        // Test data
        int index = Page.LOWER_BOUND_INDEX;
        int size = Page.LOWER_BOUND_SIZE - 1;
        
        // Execution
        Page page = new Page(index, size);
    }

}