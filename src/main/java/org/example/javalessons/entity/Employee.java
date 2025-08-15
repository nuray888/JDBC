package org.example.javalessons.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Employee {
private Long id;
private String name;
private String position;
private BigDecimal salary;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
