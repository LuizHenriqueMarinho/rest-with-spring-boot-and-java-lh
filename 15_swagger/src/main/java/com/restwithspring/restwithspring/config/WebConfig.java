package com.restwithspring.restwithspring.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.restwithspring.restwithspring.serialization.converter.YamlJackson2HttpMesageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMesageConverter());
	}


	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//PARA PASSAR OS PARAMETROS PELA URL
//		//via QUERY PARAM: http://localhost:8080/api/person/v1?mediaType=xml
//		configurer.favorParameter(true) //aceita parametros
//		.parameterName("mediaType") //pega o que tiver em mediaTYpe, no caso do exemplo, xml
//		.ignoreAcceptHeader(true) //ignora o que vier no header
//		.useRegisteredExtensionsOnly(false)
//		.defaultContentType(MediaType.APPLICATION_JSON) // deixa o json como padrão
//			.mediaType("json", MediaType.APPLICATION_JSON) //define o que vai aceitar
//			.mediaType("xml", MediaType.APPLICATION_XML);
		
		
		//PARA PASSAR OS PARAMETROS VIA HEADER
		//via QUERY PARAM: http://localhost:8080/api/person/v1 => adicionar o parametro no header do postman key: accept, value: application/xml
		configurer.favorParameter(false) //aceita parametros
		.ignoreAcceptHeader(false) //deixa de ignorar o header
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON) // deixa o json como padrão
			.mediaType("json", MediaType.APPLICATION_JSON) //define o que vai aceitar
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML); //MEDIA_TYPE_APPLICATION_YML é a constante criada lá em cima
	}

}
