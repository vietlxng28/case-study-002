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

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder extends BaseModel {

  @Column(name = "ORDER_CODE")
  private String orderCode;

  @Column(name = "CUSTOMER_NAME")
  private String customerName;

  @Column(name = "ORDER_DATE")
  private LocalDate orderDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MANAGER_EMP_ID")
  private Employee managerEmployee;
}
