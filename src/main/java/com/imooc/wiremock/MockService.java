package com.imooc.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;




public class MockService {

	public static void main(String[] args) throws IOException {
		configureFor(8062);
		removeAllMappings();
		mock("/order/1", "01");
		mock("/order/2", "02");

	}
	
	public static void mock(String url,String filename) throws IOException{
		ClassPathResource classPathResource = new ClassPathResource("mock/response/"+filename+".txt");
		String content = StringUtils.join(FileUtils.readLines(classPathResource.getFile(), "UTF-8").toArray(), "\n");
		stubFor(get(urlEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
	}

}
