package com.example.recipe_rest_api.exception;

public class ResourceNotFound extends RuntimeException{
	public ResourceNotFound(String msg) {
		super(msg);
	}
}
