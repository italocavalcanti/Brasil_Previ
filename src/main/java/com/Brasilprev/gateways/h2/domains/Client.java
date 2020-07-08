package com.Brasilprev.gateways.h2.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CLIENT")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idClient;

	@NotNull
	@Column(name = "name_client")
	private String nameClient;

	@NotNull
	@Column(name = "cpf")
	private String cpf;

	@NotNull
	@Column(name = "number_contact")
	private String numberContact;

	@OneToOne(mappedBy = "client",cascade = CascadeType.ALL)
	private Order order;

}
