package com.seven.demo.payroll;

import com.seven.demo.payroll.Employee;
import com.seven.demo.payroll.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Init database: " + repository.save(new Employee("Danil Semirazov", "admin")));
            log.info("Init database: " + repository.save(new Employee("John Week", "user")));
        };
    }
}
