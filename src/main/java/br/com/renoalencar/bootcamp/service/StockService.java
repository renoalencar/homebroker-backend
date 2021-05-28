package br.com.renoalencar.bootcamp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.renoalencar.bootcamp.exceptions.BusinessException;
import br.com.renoalencar.bootcamp.exceptions.NotFoundException;
import br.com.renoalencar.bootcamp.mapper.StockMapper;
import br.com.renoalencar.bootcamp.model.Stock;
import br.com.renoalencar.bootcamp.model.dto.StockDTO;
import br.com.renoalencar.bootcamp.repository.StockRepository;
import br.com.renoalencar.bootcamp.util.MessageUtils;

@Service
public class StockService
{

	@Autowired
	private StockRepository repository;
	
	@Autowired
	private StockMapper mapper;
	
	
	@Transactional
	public StockDTO save(@Valid StockDTO dto)
	{
		Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(),
				dto.getDate());
		
		if(optionalStock.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}
		
		Stock stock = mapper.toEntity(dto);
		repository.save(stock);
//		dto.setId(stock.getId());
		return mapper.toDto(stock);
	}


	@Transactional
	public StockDTO update(StockDTO dto)
	{
		Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(),
				dto.getDate(), dto.getId());
		
		if(optionalStock.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}
		
		Stock stock = mapper.toEntity(dto);
		repository.save(stock);
		return mapper.toDto(stock);
	}
	
	@Transactional
	public StockDTO delete(Long id)
	{
		StockDTO dto = this.findById(id);
		repository.deleteById(dto.getId());
		return dto;
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findAll()
	{
		return mapper.toDto(repository.findAll());
	}


	@Transactional(readOnly = true)
	public StockDTO findById(Long id)
	{
		return repository
				.findById(id)
				.map(mapper::toDto)
				.orElseThrow(NotFoundException::new);
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findByToday()
	{
		return repository
				.findByToday(LocalDate.now())
				.map(mapper::toDto)
				.orElseThrow(NotFoundException::new);
	}

}
