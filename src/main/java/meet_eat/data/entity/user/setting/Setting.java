package meet_eat.data.entity.user.setting;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DisplaySetting.class),
        @JsonSubTypes.Type(value = NotificationSetting.class)
})
public interface Setting {
    
    public void apply();
}