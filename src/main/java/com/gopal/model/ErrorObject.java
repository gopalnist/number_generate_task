package com.gopal.model;


import java.util.UUID;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Gopal
 *
 */

public class ErrorObject {
    private final UUID uuID;
    private final String result;
    private final String  error;

    public ErrorObject(UUID uuID, String result, String error) {
        this.uuID = uuID;
        this.result = result;
        this.error = error;
    }

	public UUID getUuID() {
		return uuID;
	}

	public String getResult() {
		return result;
	}

	public String getError() {
		return error;
	}

	
}
