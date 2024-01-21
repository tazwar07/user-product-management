package com.tu.mnagement.model;

import lombok.Data;

@Data
public class SubMenuAllModel {
	
	private int smId;
	private String smName;
	private int mhId;
	private String mhName;
	private int smSeq;
	private String url;
	private String status;

}