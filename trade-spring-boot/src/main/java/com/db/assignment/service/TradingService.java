package com.db.assignment.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.assignment.dao.TradeDao;
import com.db.assignment.entity.TradeEntity;
import com.db.assignment.model.TradeModel;

@Service
public class TradingService {
	
	@Autowired
	TradeDao tradeDao;
	
	public boolean isValidTrade(TradeModel tradeModel) {
		if(validateMaturity(tradeModel)) {
			TradeEntity tradeEn = tradeDao.findTrade(tradeModel.getTradeId());
			if(tradeEn != null) {
				return validateVersion(tradeModel,tradeEn);
			}else {
				return true; // first time entry
			}
		}
		return false;
	}

	private boolean validateVersion(TradeModel tradeModel, TradeEntity tradeEn) {
		return tradeModel.getVersion() > tradeEn.getVersion();
	}

	private boolean validateMaturity(TradeModel tradeModel) {
		return tradeModel.getMaturityDate().isAfter(LocalDate.now());
	}

}
