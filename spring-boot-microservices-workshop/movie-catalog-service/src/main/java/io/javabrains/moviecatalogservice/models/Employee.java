package io.javabrains.moviecatalogservice.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties("emp")
@Setter
@Getter
@NoArgsConstructor
public class Employee {
	
	private String name;
	private String id;
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + "]";
	}
	
}
