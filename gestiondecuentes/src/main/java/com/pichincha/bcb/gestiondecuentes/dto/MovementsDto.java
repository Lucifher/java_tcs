package com.pichincha.bcb.gestiondecuentes.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pichincha.bcb.gestiondecuentes.entity.AccountEntity;
import com.pichincha.bcb.gestiondecuentes.entity.MovementsEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementsDto {
	
	private Integer _movementId;

	private AccountEntity _sourceAccountDetalles;

	private AccountEntity _destinationAccountDetalles;
	
	private String _movementDescription;

	private Double _movementAmount;
	
	@JsonIgnore
	public MovementsEntity getAsEntity() {
		return new MovementsEntity(
				this._movementId,
				this._sourceAccountDetalles, 
				this._destinationAccountDetalles, 
				this._movementDescription, 
				this._movementAmount 
				);
	}

}