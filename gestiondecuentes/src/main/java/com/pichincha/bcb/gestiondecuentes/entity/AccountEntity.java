package com.pichincha.bcb.gestiondecuentes.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pichincha.bcb.gestiondecuentes.dto.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private Integer accountId;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "ACCOUNT_BANK")
	private String accountBank;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sourceAccountDetalles", orphanRemoval = true)
	private List<MovementsEntity> allMovementsWhereSource;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "destinationAccountDetalles", orphanRemoval = true)
	private List<MovementsEntity> allMovementsWhereDestination;
	
	@JsonIgnore
	public AccountDto getAsTo() {
		return new AccountDto(
				this.accountId,
				this.accountNumber, 
				this.accountType, 
				this.accountBank, 
				this.allMovementsWhereSource,
				this.allMovementsWhereDestination 
				);
	}
}
