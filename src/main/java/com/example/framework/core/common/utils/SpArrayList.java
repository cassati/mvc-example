package com.example.framework.core.common.utils;
import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class SpArrayList extends ArrayList {
	private static final long serialVersionUID = 1L; 
	private Class itemClass;
	public SpArrayList(Class itemClass) {
		this.itemClass = itemClass; 
	} 
	
	@SuppressWarnings("unchecked")
	public Object get(int index) {
		try { 
			while (index >= size()) { 
				add(itemClass.newInstance()); } 
			} catch (Exception e) {
				e.printStackTrace(); 
			} 
		return super.get(index); 
	}
} 