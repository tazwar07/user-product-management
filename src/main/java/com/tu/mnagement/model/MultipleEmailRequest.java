package com.tu.mnagement.model;

import java.util.List;

import lombok.Data;

@Data
public class MultipleEmailRequest {
	
	private List<String> to;
    private String subject;
    private String body;
}