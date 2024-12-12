package task;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import task.core.Task;
import task.db.TaskDAO;
import task.resources.TaskResource;

public class TaskTrackerApplication extends Application<TaskTrackerConfiguration> {

    private final HibernateBundle<TaskTrackerConfiguration> hibernate = new HibernateBundle<TaskTrackerConfiguration>(Task.class){
        @Override
        public DataSourceFactory getDataSourceFactory(TaskTrackerConfiguration configuration){
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new TaskTrackerApplication().run(args);
    }

    @Override
    public String getName() {
        return "TaskTracker";
    }

    @Override
    public void initialize(final Bootstrap<TaskTrackerConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final TaskTrackerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final TaskDAO taskDAO = new TaskDAO(hibernate.getSessionFactory());
        environment.jersey().register(new TaskResource(taskDAO));
    }

}
