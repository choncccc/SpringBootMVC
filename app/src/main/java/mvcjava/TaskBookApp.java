package mvcjava;


import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mvcjava.model.Task;
import mvcjava.repository.TaskRepository;

@SpringBootApplication
public class TaskBookApp {
    public static void main(String[] args) {
        SpringApplication.run(TaskBookApp.class, args);
    }


    @Bean
    CommandLineRunner seed(TaskRepository repo) {
    return args -> {
        if (repo.count() == 0) {
            repo.save(new Task(null, "Learn Spring MVC", "Follow the guide", LocalDate.now().plusDays(1), false));
            repo.save(new Task(null, "Push to Supabase", "Check DB", LocalDate.now().plusDays(2), false));
        }
    };
}
}
