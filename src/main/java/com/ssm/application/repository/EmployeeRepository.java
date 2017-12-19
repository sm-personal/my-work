package com.ssm.application.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ssm.application.pojo.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Serializable>, JpaSpecificationExecutor<Employee>  {
}
