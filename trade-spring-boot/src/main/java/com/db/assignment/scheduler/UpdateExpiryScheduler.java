package com.db.assignment.scheduler;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.assignment.dao.TradeDao;
import com.db.assignment.entity.TradeEntity;

@Component
public class UpdateExpiryScheduler {

	@Value("${db.update.maturity.frequency}")
	String rateOfScheduler;

	@Autowired
	TradeDao tradeDeo;

	// at 12:00 AM every day
	@Scheduled(cron = "0 0 0 * * ?")
	public void reportCurrentTime() {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}

		List<TradeEntity> unMaturedTrades = tradeDeo.findAll().parallelStream()
				.filter(tradeEntity -> tradeEntity.getMaturityDate().isBefore(LocalDate.now()))
				.collect(Collectors.toList());
		
		unMaturedTrades.forEach(tradeEn -> {
			tradeEn.setExpiryFlag("Y");
			tradeDeo.save(tradeEn);
		});

	}
}
