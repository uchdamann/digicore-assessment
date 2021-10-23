package com.digicore.devops.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BasicInfo extends CommonFields{
	private String firstName;
	private String lastName;
	private int age;
	
//	private BasicInfo() {
//		
//	}
	
	public static BasicInfoBuilder builder() {
		return new BasicInfoBuilder();
	}
	
//	private static class BasicInfoBuilder{
//		private String firstName;
//		private String lastName;
//		private int age;
//		
//		
//		public BasicInfoBuilder firstName(String firstName) {
//			this.firstName = firstName;
//			return this;
//		}
//		
//		public BasicInfoBuilder lastName(String lastName) {
//			this.lastName = lastName;
//			return this;
//		}
//		
//		public BasicInfoBuilder age(int age) {
//			this.age = age;
//			return this;
//		}
//		
//		public BasicInfo build() {
//			BasicInfo basicInfo = new BasicInfo();
//			basicInfo.firstName = this.firstName;
//			basicInfo.lastName = this.lastName;
//			basicInfo.age = this.age;
//			
//			return basicInfo;
//		}
//	}
}
