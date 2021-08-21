package com.db.assignment.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.assignment.dao.TradeDao;
import com.db.assignment.dao.TradeRepository;
import com.db.assignment.entity.TradeEntity;

@Component
public class TradeDeoImpl implements TradeDao{
	
	@Autowired
	TradeRepository tradeRepository;

	public void save(TradeEntity tradeEntity) {
		tradeRepository.save(tradeEntity);
		
	}

	public List<TradeEntity> findAll() {
		return tradeRepository.findAll();
	}

	public TradeEntity findTrade(String tradeId) {
		Optional<TradeEntity> tradeEntity = tradeRepository.findById(tradeId);
		return tradeEntity.isPresent() ? tradeEntity.get() : null;
	}

}
