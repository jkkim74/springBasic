package hello.spring.repository;

import hello.spring.domain.Employee;
import hello.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);
    Optional<Employee> findById(Long id);
    Optional<Employee> findByName(String name);
    List<Employee> findAll();
}
