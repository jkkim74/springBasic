package hello.spring.repository;

import hello.spring.domain.Employee;
import hello.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryEmployeeRepository implements EmployeeRepository{
    private static Map<Long, Employee> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Employee save(Employee employee) {
        employee.setId(sequence++);
        return store.put(employee.getId(),employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return store.values().stream()
                .filter(employee -> employee.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(store.values());
    }
}
