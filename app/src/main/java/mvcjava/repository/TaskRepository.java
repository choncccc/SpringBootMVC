package mvcjava.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mvcjava.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();
    List<Task> findByDueDateBefore(LocalDate date);
    List<Task> findByDueDateOrderByDueDateAsc(LocalDate date);

}
