package com.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bean.Employee;
import com.bean.EmployeeBean;
import com.bean.Province;
import com.bean.User;
import com.bean.UserConrona;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmployeeController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/getEmployees")
	public void getEmployees() {

		String url = "http://localhost:9090/employee/viewEmployee";

		String result = restTemplate.getForObject(url, String.class);
		System.out.println(result);

		System.out.println("********");

		ResponseEntity<EmployeeBean[]> res = restTemplate.getForEntity(url, EmployeeBean[].class);

		List<EmployeeBean> employees = Arrays.asList(res.getBody());

		for (EmployeeBean emp : employees) {

			System.out.println();
			System.out.println(emp.geteId());
			System.out.print(emp.geteName());
			System.out.print(emp.geteEmail());

		}

	}

	@RequestMapping(value = "/dummy")
	public void getDummyEmployeees() {

		String url = "http://dummy.restapiexample.com/api/v1/employees";

		String res = restTemplate.getForObject(url, String.class);
		System.out.println(res);

		ResponseEntity<Employee[]> emp = restTemplate.getForEntity(url, Employee[].class);

	}

	@RequestMapping("/getall")
	public void getAllData() {
		final String url = "http://dummy.restapiexample.com/api/v1/employees";
		ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class);
		List<Employee> users = Arrays.asList(responseEntity.getBody());
		for (Employee user : users) {
			//System.out.println(user.getId());
			//System.out.println(user.getEmployee_name());

		}
	}
	
	@RequestMapping("/getDataById")
	public void getDataBYId() {
		
		
		Map<String, String> params = new HashMap<>();
		params.put("id", "14");
		String url = "http://localhost:9090/employee/viewEmployee/{id}";
		
		EmployeeBean employeeBean = restTemplate.getForObject(url, EmployeeBean.class,params);
		System.out.println(employeeBean.geteName());
		System.out.println(employeeBean.geteEmail());
		
	}
	
	@RequestMapping("/delete")
	public void delete() {
		
		String url = "http://localhost:9090/employee/delete/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "16");
		restTemplate.delete(url, params);
		getEmployees();
		
		
		
	}
	
	@RequestMapping(value = "/corona")
	public void getCoronaData() {
		
		String url ="https://covid-19-data.p.rapidapi.com/report/country/name";
		
		//headers 
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-rapidapi-host", "covid-19-data.p.rapidapi.com");
		headers.set("x-rapidapi-key", "Enter your key here...");
		
		//entity
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
			
		Map<String, String> params = new HashMap<>();
		params.put("name", "India");
		params.put("date", "2020-05-01");
		
		//UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("name", "India").queryParam("date", "2020-05-01");
		//ResponseEntity<String> res = restTemplate.exchange(builder.buildAndExpand(params).toUri(), HttpMethod.GET,entity,String.class);
		
		
		String JSON = "[{\"country\":\"India\",\"provinces\":[{\"province\":\"India\",\"confirmed\":37257,\"recovered\":10007,\"deaths\":1223,\"active\":26027}],\"latitude\":20.593684,\"longitude\":78.96288,\"date\":\"2020-05-01\"}],[Cache-Control:\"private, must-revalidate\", Content-Type:\"application/json\", Date:\"Fri, 21 Aug 2020 07:00:53 GMT\", ETag:\"\"dee7f651e009f89602687f9f221b66d8\"\", expires:\"-1\", pragma:\"no-cache\", Server:\"RapidAPI-1.1.24\", Vary:\"Accept\", X-RapidAPI-Region:\"AWS - ap-southeast-1\", X-RapidAPI-Version:\"1.1.24\", X-RateLimit-Requests-Limit:\"50000\", X-RateLimit-Requests-Remaining:\"49996\", Content-Length:\"183\", Connection:\"keep-alive\"]";
		//String JSON = res.getBody().toString();
		
		ObjectMapper mapper = new ObjectMapper();
		
		UserConrona userConrona[];
		try {
			userConrona = mapper.readValue(JSON,UserConrona[].class);
			Province[] p = userConrona[0].getProvinces();
			System.out.println(p);
			
			System.out.println("Proivnce :-"+p[0].getProvince());
			System.out.println("Confirmed :-"+p[0].getConfirmed());
			System.out.println("Recovered :-"+p[0].getRecovered());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value = "/add")
	public void addData() {
		
		String url = "http://localhost:9090/employee/addEmployee";
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.seteName("rahul");
		employeeBean.seteEmail("rahul@gmail.com");
		employeeBean.setePassword("rahul@123");
		employeeBean.seteAge(20);
		employeeBean.seteSalary(120000);
		
		
		restTemplate.postForObject(url, employeeBean,String.class);
		
	}
	
	@RequestMapping(value = "/add1")
	public void add() {
		
		String url ="https://gorest.co.in/public-api/users";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer 87a6e693c6ad5316132fb9df78c80553c9fe17fbee4945d3704e2b55fd94c2b5");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		User user = new User();
		user.setName("raj");
		user.setEmail("raj@gmail.com");
		user.setGender("Male");
		user.setStatus("Active");
		
		restTemplate.postForObject(url, user, String.class, headers);
		
	}		
}
