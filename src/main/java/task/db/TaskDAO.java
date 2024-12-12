package task.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import task.core.Task;

import java.util.List;

public class TaskDAO extends AbstractDAO<Task> {
    public TaskDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Task findById(Long id){
        return get(id);
    }

    public Task save(Task task){
        return persist(task);
    }

    public List<Task> findAll(){
        return list((Query<Task>) namedQuery("Task.findAll"));
    }
}
