package meet_eat.data.entity.user.contact;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class ContactData {
    
    private final Map<ContactType, String> contacts;
    private final ContactRequest request;

    public ContactData(ContactRequest request) {

        this.contacts = new EnumMap<>(ContactType.class);
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