package com.db.assignment.dao;

import java.util.List;

import com.db.assignment.entity.TradeEntity;

public interface TradeDao {
	public void save(TradeEntity tradeEntity);

	List<TradeEntity> findAll();

	TradeEntity findTrade(String tradeId);
}
