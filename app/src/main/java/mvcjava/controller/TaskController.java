package mvcjava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mvcjava.model.Task;
import mvcjava.repository.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    // Home task board with filter and quick-add
    @GetMapping
    public String list(
            @RequestParam(name = "filter", defaultValue = "all") String filter,
            Model model) {

        List<Task> tasks = switch (filter) {
            case "completed" -> repo.findByCompletedTrue();
            case "uncompleted" -> repo.findByCompletedFalse();
            default -> repo.findAll();
        };

        model.addAttribute("tasks", tasks);
        model.addAttribute("filter", filter);
        model.addAttribute("task", new Task());
        return "tasks";
    }

    // Save task (inline form)
    @PostMapping
    public String save(@ModelAttribute Task task, RedirectAttributes ra) {
        repo.save(task);
        ra.addFlashAttribute("msg", "Task added!");
        return "redirect:/tasks";
    }

    // Toggle task completion
    @PostMapping("/{id}/complete")
    public String toggle(@PathVariable Long id) {
        repo.findById(id).ifPresent(t -> {
            t.setCompleted(!t.isCompleted());
            repo.save(t);
        });
        return "redirect:/tasks";
    }

    // OPTIONAL: Dedicated form view if needed
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("task", new Task());
        return "task_form";
    }
}
