package com.adidas.service.product.provider.impl;

import com.adidas.service.product.entity.Data;
import com.adidas.service.product.entity.ProductData;
import com.adidas.service.product.entity.ProductKeys;
import com.adidas.service.product.provider.DataProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

/**
 * Implementation of {@link DataProvider} which gets product data from Adidas
 * ECom API by product ID.
 *
 * @author Pavel Savinov
 */
@Service("adidasECom")
public class AdidasEComProductDataProvider implements DataProvider {

	@Cacheable(value = "products")
	@CacheEvict(value = "products", condition = "#result.containsKey('error_message')")
	@Override
	public Data getData(Serializable resourceId) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
			new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());

		clientHttpRequestFactory.setReadTimeout(200);

		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

		HttpHeaders headers = new HttpHeaders();

		headers.set("Accept", "*/*");
		headers.set("Accept-Encoding", "gzip");
		headers.set("Accept-Language", "en-US;en;");
		headers.set("Connection", "keep-alive");
		headers.set("DNT", "1");
		headers.set("Host", "www.adidas.co.uk");
		headers.set("Upgrade-Insecure-Requests", "1");
		headers.set(
			"User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5)");

		HttpEntity entity = new HttpEntity(headers);

		String errorMessage = "Unable to get data from the Adidas ECom service";

		try {
			ResponseEntity<ProductData> response = restTemplate.exchange(
				String.format("%s%s", endpoint, resourceId), HttpMethod.GET,
				entity,
				ProductData.class, "");

			return response.getBody();
		}
		catch (HttpClientErrorException hcee) {
			errorMessage = hcee.getStatusText();

			logger.error(errorMessage, hcee);
		}
		catch (Exception e) {
			logger.error(errorMessage, e);
		}

		ProductData productData = new ProductData(resourceId.toString());

		productData.put(
			ProductKeys.error_message.name(),
			errorMessage);

		return productData;
	}

	private Logger logger = LoggerFactory.getLogger(
		AdidasEComProductDataProvider.class);


	@Value("${adidas.ecom.endpoint}")
	private String endpoint;
}
