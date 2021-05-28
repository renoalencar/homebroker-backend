package br.com.renoalencar.bootcamp.exceptions;

import br.com.renoalencar.bootcamp.util.MessageUtils;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException
{
	
	public NotFoundException(){
		super(MessageUtils.NO_RECORDS_FOUND);
	}

}
