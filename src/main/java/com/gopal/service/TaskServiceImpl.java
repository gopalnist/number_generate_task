package com.gopal.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.gopal.constant.Constants;
import com.gopal.exception.TaskException;
import com.gopal.model.Input;
import com.gopal.model.Output;

/**
 * Service implementation class for generation of file , task status , and get generated number 
 * @author gopal
 *
 */

@Service
public class TaskServiceImpl implements TaskService{
	
	private Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Value("${spring.file.path}")
	private String path;
	
	/**
	 * generate number in decreasing order ,  pass  steps and goal as 
	 *  e.g goal = 10 , step = 2 , output file will be  = 10 8 6 4 2 0
	 * @param input
	 * @param uuID
	 * @return
	 * @throws InterruptedException
	 */
	@Async
	@Override
    public Future<Output> generateNumber(Input input, UUID uuID) throws TaskException{
		
		try {
			FileWriter writer = new FileWriter(uuID+"_output.txt");
			
			// we can write into single line to by passing /t "
			for (int i = input.getGoal(); i >= 0; i=i-input.getStep()) {
				Thread.sleep(1000);
				writer.write( i+ "\n" + "");
			}
			writer.close();
		} catch (IOException | InterruptedException e) {
			throw new TaskException(e.getMessage());
		}
        return new AsyncResult<>(new Output(uuID, Constants.SUCCESS));
    }
	
	@Override
	public Output getGeneratedNumber(UUID uuID) throws TaskException {
		StringBuilder builder = new StringBuilder();
		try {
			 List<String> lines = Files.readAllLines(Paths.get(uuID+"_output.txt"));
			    for (String line : lines) {
			    	log.info(line);
			    	builder.append(line);
					if(!Constants.ZERO.equals(line))
						builder.append(",");
			    }

		} 
		catch (Exception e) {
			throw new TaskException(e.getMessage());
		}
		return new Output(uuID, builder.toString());
	}

}
