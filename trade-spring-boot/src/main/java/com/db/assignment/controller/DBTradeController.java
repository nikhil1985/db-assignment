package com.db.assignment.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.assignment.Mapper.TradeMapper;
import com.db.assignment.dao.TradeDao;
import com.db.assignment.entity.TradeEntity;
import com.db.assignment.model.TradeModel;
import com.db.assignment.service.TradingService;

@RestController
@CrossOrigin("localhost")
public class DBTradeController {
	
	@Autowired
	TradeDao tradeDeo;
	
	@Autowired
	TradingService tradeService;

	
	
	@PostMapping("/saveTrade")
	public ResponseEntity<String> tradeValidateStore(@RequestBody TradeModel tradeModel) {
		TradeMapper tradeMapper = TradeMapper._MAPPER;
		boolean isValid = tradeService.isValidTrade(tradeModel);
		if(isValid) {
			TradeEntity tradeEntity = tradeMapper.toTradeEntity(tradeModel);
			tradeEntity.setCreatedDate(LocalDate.now());
			tradeDeo.save(tradeEntity);
		}else {
			return new ResponseEntity<String>("Invalid Trade ", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Trade Saved ", HttpStatus.CREATED);
	}

	@GetMapping("/trades")
	public List<TradeModel> findAllTrades() {
		TradeMapper tradeMapper = TradeMapper._MAPPER;
		return tradeDeo.findAll().parallelStream().map(tradeEn -> tradeMapper.toTradeModel(tradeEn)).collect(Collectors.toList());
		
	}
}
