package com.digicore.devops.models;

import java.util.Date;

import lombok.Data;

@Data
public abstract class CommonFields {
	private final Date CREATEDON=new Date();
	private final Date UPDATEDON=new Date();
	private final Boolean ISACTIVE=true;
	
}
