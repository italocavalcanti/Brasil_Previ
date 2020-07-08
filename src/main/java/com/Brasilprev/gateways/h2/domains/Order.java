package com.Brasilprev.gateways.h2.domains;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "PURCHASE_ORDER")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Order {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idOrder;

	@NotNull
	@Column(name = "total_value")
	private BigDecimal totalValue;

	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name = "id_client", referencedColumnName = "id")
	private Client client;

	@NotNull
	@OneToMany(cascade = CascadeType.DETACH)
	@JoinColumn(name = "id_order", referencedColumnName = "id")
	private Set<Product> products;

}