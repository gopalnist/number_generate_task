package com.gopal.model;

import java.util.UUID;

/**
 * 
 * @author Gopal
 *
 */
 public class ResponseObject {
	 
    private final UUID uuId;
    private final String result;
    private final String url;

    public ResponseObject(UUID uuId, String result, String url) {
        this.uuId = uuId;
        this.result = result;
        this.url = url;
    }

	public UUID getUuId() {
		return uuId;
	}

	public String getResult() {
		return result;
	}

	public String getUrl() {
		return url;
	}

}
