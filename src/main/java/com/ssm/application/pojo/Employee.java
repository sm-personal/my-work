package com.ssm.application.pojo;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@Entity
@Table(name = "employee")
@JsonInclude(NON_NULL)
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long employeeId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private BigDecimal salary;

	public Employee() {
		super();
	}

	public Employee(final Long employeeId, final String name, final String address, final BigDecimal salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + ((employeeId == null) ? 0 : employeeId.hashCode());
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Employee other = (Employee)obj;
		if (employeeId == null) {
			if (other.employeeId != null) {
				return false;
			}
		}
		else if (!employeeId.equals(other.employeeId)) {
			return false;
		}
		return true;
	}

}
