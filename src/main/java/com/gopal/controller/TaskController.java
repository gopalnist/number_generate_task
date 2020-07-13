package com.gopal.controller;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.gopal.constant.Constants;
import com.gopal.model.ErrorObject;
import com.gopal.model.Input;
import com.gopal.model.Output;
import com.gopal.model.ResponseObject;
import com.gopal.service.TaskService;


@RestController
public class TaskController {
	
    @Autowired
    private TaskService taskService;

    private HashMap<UUID, Future<Output>> futureObjects = new HashMap<>();
	
	    /**
	     * 
	     * @param input
	     * @return
	     * @throws InterruptedException
	     */
	
	 	@PostMapping("/api/generate")
	    DeferredResult<ResponseEntity<?>> generate(@RequestBody Input input)
	            throws InterruptedException {
		 	UUID uuID = UUID.randomUUID();
	        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
	        Future<Output> output = taskService.generateNumber(input, uuID);                                                       
	        futureObjects.put(uuID, output);                                                                                     
	        ResponseObject responseObject = new ResponseObject(uuID,Constants.IN_PROGRESS, "/api/tasks/"+uuID+"/status");
	        ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);                  
	        deferredResult.setResult(responseEntity);
	        return deferredResult;
	    }

	 	/**
	 	 * 
	 	 * @param uuID
	 	 * @return
	 	 * @throws InterruptedException
	 	 */
	    @GetMapping("/api/tasks/{uuID}/status")
	    DeferredResult<ResponseEntity<?>> getStatus(@PathVariable UUID uuID) throws InterruptedException {
	        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
	        try {
	            Future<Output> futureOutput = futureObjects.get(uuID);                                                           
	            if (futureOutput.isDone()) {                                                                                         	                
	            	Output output = futureOutput.get();
	                ResponseEntity<Output> responseEntity = new ResponseEntity<>(output, HttpStatus.OK);               
	                deferredResult.setResult(responseEntity);
	            } else {
	            	ResponseObject responseObject = new ResponseObject(uuID,Constants.IN_PROGRESS, "/api/tasks/"+uuID+"/status");
	            	ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);                  
	     	        deferredResult.setResult(responseEntity);
	            }
	        } catch (Exception e) {
	            ErrorObject errorObject = new ErrorObject(uuID, Constants.ERROR, e.getMessage());
	            ResponseEntity<ErrorObject> responseEntity = new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);     
	            deferredResult.setResult(responseEntity);
	        }
	        return deferredResult;
	    }
	    
	    
	    /**
	     * 
	     * @param uuID
	     * @return
	     * @throws InterruptedException
	     */
	    @GetMapping("/api/tasks/{uuID}")
	    DeferredResult<ResponseEntity<?>> getNumList(@PathVariable UUID uuID, @RequestParam("action") String action) throws InterruptedException {
	        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
	        try {
	            Future<Output> futureOutput = futureObjects.get(uuID);                                                           
	            if (futureOutput.isDone() && "num_list".equals(action)) {                                                                                         	                
	            	Output output = taskService.getGeneratedNumber(uuID);
	                ResponseEntity<Output> responseEntity = new ResponseEntity<>(output, HttpStatus.OK);               
	                deferredResult.setResult(responseEntity);
	            } else {
	            	ResponseObject responseObject = new ResponseObject(uuID,Constants.IN_PROGRESS, "/api/tasks/"+uuID+"/status");
	            	ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);                  
	     	        deferredResult.setResult(responseEntity);
	            }
	        } catch (Exception e) {
	            ErrorObject errorObject = new ErrorObject(uuID, Constants.ERROR, e.getMessage());
	            ResponseEntity<ErrorObject> responseEntity = new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);     
	            deferredResult.setResult(responseEntity);
	        }
	        return deferredResult;
	    }

}
