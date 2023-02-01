package com.zl.service.spring;

import javax.jws.WebService;

import com.zl.service.facade.WebServiceTest;
@WebService
public class WebServiceTestSpringImpl implements WebServiceTest {

	@Override
	public String cxfTest(String message) {
		// TODO Auto-generated method stub
		return "Send CXF message";
	}

}
