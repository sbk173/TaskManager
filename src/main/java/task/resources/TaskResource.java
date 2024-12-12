package task.resources;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import task.core.Task;
import task.db.TaskDAO;

import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
    private final TaskDAO taskDAO;

    public TaskResource(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
    }

    @GET
    @UnitOfWork
    public Response getAllTasks(){
        List<Task> tasks = taskDAO.findAll();
        return Response.ok(tasks).build();
    }
}
