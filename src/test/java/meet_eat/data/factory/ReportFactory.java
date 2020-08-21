package meet_eat.data.factory;

import meet_eat.data.entity.relation.Report;
import meet_eat.data.entity.user.User;

@Deprecated
public class ReportFactory extends ObjectFactory<Report> {

    private final UserFactory userFactory;

    public ReportFactory() {
        userFactory = new UserFactory();
    }

    @Override
    protected Report createObject() {
        User reporter = userFactory.getValidObject();
        User reportedUser = userFactory.getValidObject();
        String message = "This is report number" + objectCounter + "!";
        return new Report(reporter, reportedUser, message);
    }
}
