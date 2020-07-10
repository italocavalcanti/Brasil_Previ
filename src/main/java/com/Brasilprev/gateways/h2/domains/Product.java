package com.Brasilprev.gateways.h2.domains;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idProduct;

	@NotNull
	@Column(name = "name_product")
	private String nameProduct;

	@NotNull
	@Column(name = "description_product")
	private String descriptionProduct;
	
	@NotNull
	@Column(name = "value")
	private BigDecimal value;
	
	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order")
	private Order Order;

}