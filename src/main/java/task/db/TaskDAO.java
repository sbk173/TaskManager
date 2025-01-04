package task.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import task.core.Task;

import java.util.List;
import java.util.Optional;

public class TaskDAO extends AbstractDAO<Task> {
    public TaskDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Optional<Task> findById(Long id){
        return Optional.ofNullable(get(id));
    }

    public Task save(Task task){
        return persist(task);
    }

    public List<Task> findAll(){
        return list((Query<Task>) namedQuery("Task.findAll"));
    }

    public boolean deleteTaskById(Long id){
        Task task = get(id);
        if(task != null){
            currentSession().remove(task);
            return true;
        }
        return false;

    }

    public void update(Task task){
        currentSession().merge(task);
    }
}
