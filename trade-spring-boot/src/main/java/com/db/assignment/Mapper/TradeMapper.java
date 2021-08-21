package com.db.assignment.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.db.assignment.entity.TradeEntity;
import com.db.assignment.model.TradeModel;

@Mapper
@Service
public interface TradeMapper {
   TradeMapper _MAPPER = Mappers.getMapper(TradeMapper.class);
   
   @Mapping(target = "version", source = "model.version")
   @Mapping(target = "counterParty", source = "model.counterParty")
   @Mapping(target = "bookId", source = "model.bookId")
   @Mapping(target = "maturityDate", source = "model.maturityDate")
   @Mapping(target = "expiryFlag", source = "model.expiryFlag")
   @Mapping(target = "tradeId", source = "model.tradeId")
   TradeEntity toTradeEntity(TradeModel model);
   
   
   @Mapping(target = "version", source = "entity.version")
   @Mapping(target = "counterParty", source = "entity.counterParty")
   @Mapping(target = "bookId", source = "entity.bookId")
   @Mapping(target = "maturityDate", source = "entity.maturityDate")
   @Mapping(target = "tradeId", source = "entity.tradeId")
   TradeModel toTradeModel(TradeEntity entity);
}
