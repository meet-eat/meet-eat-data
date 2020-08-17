package meet_eat.data.factory;

import meet_eat.data.entity.relation.Report;
import meet_eat.data.entity.user.User;

public class ReportFactory extends ObjectFactory<Report> {

    private UserFactory userFactory;

    public ReportFactory() {
        super();
        userFactory = new UserFactory();
    }

    @Override
    protected Report createObject() {
        User reporter = userFactory.getValidObject();
        String message = "This is report number" + objectCounter + "!";
        return new Report(reporter, message);
    }
}
