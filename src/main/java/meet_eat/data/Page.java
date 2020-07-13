package meet_eat.data;

public class Page {
    
    public static final int INDEX_LOWER_BOUND = 0;
    public static final int SIZE_LOWER_BOUND = 1;
    private static final String ERROR_MESSAGE_ILLEGAL_INDEX = "Page index must not be less than zero.";
    private static final String ERROR_MESSAGE_ILLEGAL_SIZE = "Page size must not be less than one.";

    private final int index;
    private final int size;

    public Page(int index, int size) {
        if (index < INDEX_LOWER_BOUND) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_INDEX);
        } else if (size < SIZE_LOWER_BOUND) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_SIZE);
        }
        
        this.index = index;
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return size;
    }

}