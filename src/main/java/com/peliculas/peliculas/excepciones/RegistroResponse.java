package com.peliculas.peliculas.excepciones;


public class RegistroResponse {
    	private String message;

	public RegistroResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
