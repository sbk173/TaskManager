package task.core;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq_gen")
    @SequenceGenerator(name = "tasks_seq_gen", sequenceName = "tasks_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Description is Mandatory")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.valueOf("TODO");

    @NotNull(message = "Start Date is Mandatory")
    private LocalDate startDate;

    @NotNull(message = "End Date is Mandatory")
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
