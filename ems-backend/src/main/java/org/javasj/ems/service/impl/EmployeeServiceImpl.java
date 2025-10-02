package org.javasj.ems.service.impl;

import lombok.AllArgsConstructor;
import org.javasj.ems.dto.EmployeeDto;
import org.javasj.ems.entity.Employee;
import org.javasj.ems.exception.ResourceNotFoundException;
import org.javasj.ems.mapper.EmployeeMapper;
import org.javasj.ems.repository.EmployeeRepository;
import org.javasj.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);


        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee is not exists with given id:"+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
         List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
      Employee employee=  employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee is not Exists with given id:"+employeeId)
        );

      employee.setFirstName(updateEmployee.getFirstName());
      employee.setLastName(updateEmployee.getLastName());
      employee.setEmail(updateEmployee.getEmail());

       Employee updateEmployeeObj=employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=  employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee is not Exists with given id:"+employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }
}
