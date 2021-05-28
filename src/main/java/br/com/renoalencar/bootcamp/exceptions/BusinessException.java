package br.com.renoalencar.bootcamp.exceptions;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException
{

	public BusinessException(String message) {
		super(message);
	}

}
