# number_generate_task
The project conatins 3 Api 
1. Generate Number and write into the file
2. Status 
3. Get Generated List

Documentation to run the API : 

Step1: Run GenerateNumbersApplication.java program 
Step2: You can run Test class or Do testing through Post man
Step3: For Rest API you can check swagger documentaion.
        http://localhost:8080/swagger-ui.html


Explanation of End Point URL'S

1.API_1(Request Method ="POST")

URL End Point. : http://localhost:8080/api/generate
  Request :  
       {
        "goal":10,
        "step":2
        }
  Response :
  {
    "uuId": "980baedb-f385-4ad7-9677-566c29babbbc",
    "result": "IN_PROGRESS",
    "url": "/api/tasks/980baedb-f385-4ad7-9677-566c29babbbc/status"
  }
  
 2.API_1(Request Method ="GET")
 URL End Point. : http://localhost:8080/api/tasks/{uuID}/status
 pass uuID vairable in End Point 
 
 To check Success and In Progress State Kept delay of 1 sec , so beofre 10 sec if we check status it will give In progress state , 
 after 10 sec if check the url then it will result as success
 
 Respoonse : SUCCESS 
 
 {
    "uuId": "4931f19e-cfdd-446f-8040-ce4c12612955",
    "result": "SUCCESS",
  }

Respoonse : IN_PROGRESS 
 
 {
    "uuId": "4931f19e-cfdd-446f-8040-ce4c12612955",
    "result": "SUCCESS",
 }
 
 3.API_1(Request Method ="GET")
 URL End Point. : http://localhost:8080/api/tasks/{uuID}?action=get_numlist
 
 response :
 {
    "task": "4931f19e-cfdd-446f-8040-ce4c12612955",
    "result": "10,8,6,4,2,0"
}

