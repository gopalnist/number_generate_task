package com.gopal.model;

import java.util.UUID;

public class Output {
	
	private UUID task;
	
	private String result;
	
	public Output() {
		
	}
	
	public Output(UUID task) {
		this.task = task;
	}
	
	public Output(String result) {
		this.result = result;
	}
	
	public Output(UUID task, String result) {
		this.task= task;
		this.result = result;
	}

	public UUID getTask() {
		return task;
	}

	public void setTask(UUID task) {
		this.task = task;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
