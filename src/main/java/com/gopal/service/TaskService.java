package com.gopal.service;

import java.util.UUID;
import java.util.concurrent.Future;

import com.gopal.exception.TaskException;
import com.gopal.model.Input;
import com.gopal.model.Output;

/**
 * 
 * @author Gopal
 *
 */

public interface TaskService {
	
	Future<Output> generateNumber(Input input, UUID uuID) throws TaskException;

	Output getGeneratedNumber(UUID uuID) throws TaskException;

}
