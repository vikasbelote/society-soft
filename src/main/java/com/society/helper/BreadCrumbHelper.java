package com.society.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.society.helper.model.BreadCrumb;

@Component
public class BreadCrumbHelper {
	
	public List<BreadCrumb> getBreadCrumbList(String[] breadCrumbs) {
		
		List<BreadCrumb> breadCrumbList = new ArrayList<BreadCrumb>();
		
		int size = breadCrumbs.length;
		for(int i = 0; i < (size - 1); i++) {
			BreadCrumb breadCrumb = new BreadCrumb();
			breadCrumb.setMenuName(breadCrumbs[i]);
			breadCrumb.setIsLast(false);
			breadCrumbList.add(breadCrumb);
		}
		
		if(size > 0) {
			BreadCrumb breadCrumb = new BreadCrumb();
			breadCrumb.setMenuName(breadCrumbs[size - 1]);
			breadCrumb.setIsLast(true);
			breadCrumbList.add(breadCrumb);
		}
		return breadCrumbList;
	}
}
