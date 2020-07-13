package meet_eat.data;

public class Page {
    
    private static final String ERROR_MESSAGE_ILLEGAL_INDEX = "Page index must not be less than zero.";
    private static final String ERROR_MESSAGE_ILLEGAL_SIZE = "Page size must not be less than one.";

    private final int index;
    private final int size;

    public Page(int index, int size) {
        if (index < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_INDEX);
        } else if (size < 1) {
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