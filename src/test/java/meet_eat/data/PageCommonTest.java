package meet_eat.data;

import org.junit.Test;

import static org.junit.Assert.*;

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
        int index = Page.INDEX_LOWER_BOUND - 1;
        int size = Page.SIZE_LOWER_BOUND;
        
        // Execution
        Page page = new Page(index, size);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSizeOutOfBounds() {
        // Test data
        int index = Page.INDEX_LOWER_BOUND;
        int size = Page.SIZE_LOWER_BOUND - 1;
        
        // Execution
        Page page = new Page(index, size);
    }

    @Test
    public void testEquals() {
        // Execution
        Page page = new Page(0, 50);
        Page pageCopy = new Page(page.getIndex(), page.getSize());

        // Assertions
        assertEquals(page, page);
        assertNotEquals(null, page);
        assertNotEquals(page, new Object());
        assertEquals(page, pageCopy);
        assertEquals(page.hashCode(), pageCopy.hashCode());
    }
}