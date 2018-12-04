package com.adidas.service.product.provider;

import com.adidas.service.product.entity.Data;

import java.io.Serializable;

/**
 * Defines a {@link Data} provider which gets data instance associated with a
 * specific resource ID.
 *
 * @author Pavel Savinov
 */
public interface DataProvider {

	public Data getData(Serializable resourceId);

}
