package com.Brasilprev.gateways.h2.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idClient;
	
	@NotNull
	private String nameClient;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private String numberContact;

}
