package org.javasj.ems.repository;

import org.javasj.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository <Employee,Long>{


}
