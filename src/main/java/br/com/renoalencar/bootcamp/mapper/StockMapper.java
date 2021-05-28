package br.com.renoalencar.bootcamp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import br.com.renoalencar.bootcamp.model.Stock;
import br.com.renoalencar.bootcamp.model.dto.StockDTO;

@Component
public class StockMapper
{

	public Stock toEntity(@Valid StockDTO dto)
	{
		Stock stock = new Stock();
		
		stock.setId(dto.getId());
		stock.setName(dto.getName());
		stock.setPrice(dto.getPrice());
		stock.setVariation(dto.getVariation());
		stock.setDate(dto.getDate());
		
		return stock;
	}

	public StockDTO toDto(Stock stock)
	{
		StockDTO dto = new StockDTO();
		
		dto.setId(stock.getId());
		dto.setName(stock.getName());
		dto.setPrice(stock.getPrice());
		dto.setVariation(stock.getVariation());
		dto.setDate(stock.getDate());
		
		return dto;
	}
	
	public List<StockDTO> toDto(List<Stock> listStock){
		return listStock.stream().map(this::toDto).collect(Collectors.toList());
	}

}
