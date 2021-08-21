package com.db.assignment;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.db.assignment.controller.DBTradeController;
import com.db.assignment.model.TradeModel;

@SpringBootTest
public class TradeTest {

	@Autowired
	DBTradeController dbTradeController;
	
	@org.junit.Before
	public void setUp() {
	    
	}
	
	@After
	public void tearDown() {
	}
	
	
	
	public TradeModel createModel() {
		TradeModel model = new TradeModel();
		model.setTradeId("T-1");
		model.setVersion(1);
		model.setBookId("B-1");
		model.setCounterParty("CP-2");
		model.setMaturityDate(LocalDate.now().plusDays(5));
		model.setExpiryFlag("N");
		
		return model;
	}
	
	@Test
	void saveTradeTestFirstTime() {
		ResponseEntity<String> responseEntity = dbTradeController.tradeValidateStore(createModel());
		Assertions.assertEquals(new ResponseEntity<String>("Trade Saved ", HttpStatus.CREATED),responseEntity);
		List<TradeModel> tradeList =dbTradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T-1",tradeList.get(0).getTradeId());
	}
	
	@Test
	void saveTradeTestSecondTime() {
		ResponseEntity<String> responseEntity = dbTradeController.tradeValidateStore(createModel());
		responseEntity = dbTradeController.tradeValidateStore(createModel());
		Assertions.assertEquals(new ResponseEntity<String>("Invalid Trade ", HttpStatus.OK),responseEntity);
		List<TradeModel> tradeList =dbTradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T-1",tradeList.get(0).getTradeId());
	}
	
	@Test
	void saveTradeTestIncreaseVersion() {
		TradeModel model = createModel();
		ResponseEntity<String> responseEntity = dbTradeController.tradeValidateStore(model);
		model.setVersion(2);
		responseEntity = dbTradeController.tradeValidateStore(model);
		Assertions.assertEquals(new ResponseEntity<String>("Trade Saved ", HttpStatus.CREATED),responseEntity);
		List<TradeModel> tradeList =dbTradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T-1",tradeList.get(0).getTradeId());
		Assertions.assertEquals(2,tradeList.get(0).getVersion());
	}
	
}
