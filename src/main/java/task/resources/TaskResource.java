package task.resources;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import task.core.Task;
import task.db.TaskDAO;

import java.util.List;
import java.util.Optional;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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

    @POST
    @UnitOfWork
    public Response addTask(Task task){
        Task createdTask = taskDAO.save(task);

        return Response.status(Response.Status.CREATED)
                .entity(createdTask)
                .build();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Response getTaskById(@PathParam("id") Long id){
        Optional<Task> task = taskDAO.findById(id);
        if(task.isPresent()){
            return Response.ok(task.get()).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Task with id "+id+" not found.")
                    .build();
        }

    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response deleteTaskById(@PathParam("id") Long id){
        boolean taskDeleted = taskDAO.deleteTaskById(id);
        if(taskDeleted){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Task with id "+id+" not found")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Response updateTask(@PathParam("id") Long id, Task updatedTask){
        Optional<Task> task = taskDAO.findById(id);
        if(task.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No task found with id "+id)
                    .build();
        }

        Task existingTask = task.get();
        if(updatedTask.getDescription() != null){
            existingTask.setDescription(updatedTask.getDescription());
        }
        if(updatedTask.getStartDate() != null){
            existingTask.setStartDate(updatedTask.getStartDate());
        }
        if(updatedTask.getEndDate() != null){
            existingTask.setEndDate(updatedTask.getEndDate());
        }
        if(updatedTask.getStatus() != null){
            existingTask.setStatus(updatedTask.getStatus());
        }

        taskDAO.update(existingTask);

        return Response.ok(existingTask).build();
    }
}
