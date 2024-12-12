package task.core;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="tasks")
@NamedQueries({
        @NamedQuery(
                name = "Task.findAll",
                query = "FROM Task"
        )
})
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String Description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate startDate;

    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public enum Status{
        TODO,
        WIP,
        DONE
    }

}
