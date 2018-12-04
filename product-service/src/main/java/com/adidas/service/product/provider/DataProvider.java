package com.adidas.service.product.provider;

import com.adidas.service.product.entity.Data;

import java.io.Serializable;

/**
 * @author Pavel Savinov
 */
public interface DataProvider {

	public Data getData(Serializable resourceId);

}
