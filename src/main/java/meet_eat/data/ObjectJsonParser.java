package meet_eat.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;

import java.util.Objects;

public class ObjectJsonParser {

    private static final String ERROR_MESSAGE_STRING_NOT_PARSABLE = "Given json string must be parsable, but was not.";
    private static final String ERROR_MESSAGE_OBJECT_NOT_PARSABLE = "Given object must be parsable, but was not.";

    private ObjectMapper objectMapper;

    public ObjectJsonParser() {
        objectMapper = new ObjectMapper();

        // Activate type/class meta-properties of elements generically typed within collections for example.
        objectMapper.activateDefaultTypingAsProperty(new DefaultBaseTypeLimitingValidator(),
                ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT,
                JsonTypeInfo.Id.CLASS.getDefaultPropertyName());
    }

    public ObjectJsonParser(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    public <T> T parseJsonStringToObject(String jsonString, Class<T> type) {
        try {
            return objectMapper.readValue(jsonString, type);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE_STRING_NOT_PARSABLE, exception);
        }
    }

    public <T> T parseJsonStringToObject(String jsonString, JavaType type) {
        try {
            return objectMapper.readValue(jsonString, type);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE_STRING_NOT_PARSABLE, exception);
        }
    }

    public String parseObjectToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE_OBJECT_NOT_PARSABLE, exception);
        }
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }
}
