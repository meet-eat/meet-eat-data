package meet_eat.data.entity.user.contact;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class ContactData {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REQUEST = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "request");
    private static final String ERROR_MESSAGE_NULL_CONTACT_TYPE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "contactType");
    private static final String ERROR_MESSAGE_NULL_VALUE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "value");

    private final Map<ContactType, String> contacts;
    private final ContactRequest request;

    public ContactData(ContactRequest request) {
        contacts = new EnumMap<>(ContactType.class);
        this.request = Objects.requireNonNull(request, ERROR_MESSAGE_NULL_REQUEST);;
    }

    public Map<ContactType, String> getContacts() {
        return Collections.unmodifiableMap(contacts);
    }

    public String getContactByType(ContactType type){
        return contacts.get(type);
    }

    public ContactRequest getRequest() {
        return request;
    }

    public void putContact(ContactType type, String value) {
        Objects.requireNonNull(type, ERROR_MESSAGE_NULL_CONTACT_TYPE);
        Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
        contacts.put(type, value);
    }
}