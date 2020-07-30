package meet_eat.data.entity.user.contact;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class ContactData {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REQUEST = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "request");
    private static final String ERROR_MESSAGE_NULL_CONTACT_TYPE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "contactType");
    private static final String ERROR_MESSAGE_NULL_VALUE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "value");

    @JsonProperty
    private final Map<ContactType, String> contacts;
    @JsonProperty
    private final ContactRequest request;

    public ContactData(ContactRequest request) {
        contacts = new EnumMap<>(ContactType.class);
        this.request = Objects.requireNonNull(request, ERROR_MESSAGE_NULL_REQUEST);
    }

    @JsonCreator
    public ContactData(@JsonProperty("request") ContactRequest request, @JsonProperty("contacts") Map<ContactType, String> contacts) {
        this.contacts = Objects.requireNonNull(contacts);
        this.request = Objects.requireNonNull(request, ERROR_MESSAGE_NULL_REQUEST);
    }

    @JsonGetter
    public Map<ContactType, String> getContacts() {
        return Collections.unmodifiableMap(contacts);
    }

    @JsonIgnore
    public String getContactByType(ContactType type){
        return contacts.get(type);
    }

    @JsonGetter
    public ContactRequest getRequest() {
        return request;
    }

    public void putContact(ContactType type, String value) {
        Objects.requireNonNull(type, ERROR_MESSAGE_NULL_CONTACT_TYPE);
        Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
        contacts.put(type, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(contacts, that.contacts) && Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, request);
    }
}