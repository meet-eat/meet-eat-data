package meet_eat.data.entity.user.contact;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class ContactData {
    
    private static final String ERROR_MESSAGE_TEMPLATE = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REQUEST = String.format(ERROR_MESSAGE_TEMPLATE, "request");

    private final Map<ContactType, String> contacts;
    private final ContactRequest request;

    public ContactData(ContactRequest request) {

        Objects.requireNonNull(request, ERROR_MESSAGE_NULL_REQUEST);

        contacts = new EnumMap<>(ContactType.class);
        this.request = request;
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

        contacts.put(type, value);
    }
}