package com.tu.mnagement.model;

import java.util.List;

import lombok.Data;

@Data
public class EmailRequest {

    private List <String> to;
    private String name;
    private String message;
	public String getSubject() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getBody() {
		// TODO Auto-generated method stub
		return null;
	}

}
