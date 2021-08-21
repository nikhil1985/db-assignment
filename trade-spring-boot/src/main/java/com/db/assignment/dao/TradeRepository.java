package com.db.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.assignment.entity.TradeEntity;

public interface TradeRepository extends JpaRepository<TradeEntity, String> {

}
