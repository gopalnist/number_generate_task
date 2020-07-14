package com.gopal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gopal.constant.Constants;
import com.gopal.controller.TaskController;
import com.gopal.model.ResponseObject;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TaskControllerTest {
	
	private TaskController controller;
	private MockMvc mockMvc;
	public static final String CONTENT_TYPE = "Content-Type";
	
	
	@BeforeAll
	public void setUp() {
	    controller = new TaskController();
	    mockMvc = MockMvcBuilders
	            .standaloneSetup(controller)
	            .build();
	}
	
	
	@Test
	public void generateTest() throws Exception {
		UUID uuID = UUID.randomUUID();
		 ResponseObject responseObject = new ResponseObject(uuID,Constants.IN_PROGRESS, "/api/tasks/"+uuID+"/status");
	        ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);   
	        
		MvcResult result = mockMvc
                .perform(get("/api/generate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mockMvc
                .perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
               // .andExpect(responseEntity);
      
	}

}
