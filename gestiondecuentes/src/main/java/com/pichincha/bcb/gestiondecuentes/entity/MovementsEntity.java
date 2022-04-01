package com.pichincha.bcb.gestiondecuentes.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pichincha.bcb.gestiondecuentes.dto.MovementsDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_MOVIMIENTOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVEMENT_ID")
	private Integer movementId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "SOURCE_ACCOUNT", nullable = false)
	private AccountEntity sourceAccountDetalles;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "DESTINATION_ACCOUNT", nullable = false)
	private AccountEntity destinationAccountDetalles;
	
	@Column(name = "MOVEMENT_DESCRIPTION")
	private String movementDescription;

	@Column(name = "MOVEMENT_AMOUNT")
	private Double movementAmount;
	
	@JsonIgnore
	public MovementsDto getAsTo() {
		return new MovementsDto(
				this.movementId,
				this.sourceAccountDetalles, 
				this.destinationAccountDetalles, 
				this.movementDescription, 
				this.movementAmount 
				);
	}


}
