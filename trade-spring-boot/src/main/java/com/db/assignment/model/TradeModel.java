package com.db.assignment.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TradeModel {

	@JsonProperty("version")
	private int version;
	
	@JsonProperty("tradeId")
	private String tradeId;
	
	@JsonProperty("counterParty")
	private String counterParty;
	
	@JsonProperty("bookId")
	private String bookId;
	
	@JsonProperty("maturityDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate maturityDate;
	
	@JsonProperty("expiryFlag")
	private String expiryFlag;
}
