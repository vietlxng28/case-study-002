package com.vietlong.sandbox.model;

import com.vietlong.sandbox.model.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseModel {

  @Column(name = "EMP_ID", length = 50)
  private String empId;

  @Column(name = "F_NAME")
  private String firstName;

  @Column(name = "L_NAME")
  private String lastName;

  @Column(name = "AGE")
  private Integer age;

  @Column(name = "ADRESS")
  private String adress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SUPERIOR_EMP_ID")
  private Employee superiorEmployee;
}
