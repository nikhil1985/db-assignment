package com.db.assignment.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="TRADES")
public class TradeEntity {

	
    private int version;
    private String counterParty;
    
    private String bookId;
    
    @Id
	private String tradeId;
    private LocalDate maturityDate;
    private LocalDate createdDate;
    private String expiryFlag;
}