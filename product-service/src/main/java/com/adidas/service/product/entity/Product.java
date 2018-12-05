package com.adidas.service.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic product representation based on the Adidas ECom API response
 * structure and Reviews service response.
 *
 * @author Pavel Savinov
 */
@ApiModel("Product description")
public class Product {

	public void put(ProductKeys productKey, Object data) {
		map.put(productKey, data);
	}

	@ApiModelProperty(name = "id", value = "Product ID")
	public String getId() {
		return map.getOrDefault(ProductKeys.id, "").toString();
	}

	@ApiModelProperty(name = "name", value = "Product name")
	public String getName() {
		return map.getOrDefault(ProductKeys.name, "").toString();
	}

	@ApiModelProperty(name="model_number", value = "Product model number")
	public String getModelNumber() {
		return map.getOrDefault(ProductKeys.model_number, "").toString();
	}

	@ApiModelProperty(name = "product_type", value = "Product type")
	public String getProductType() {
		return map.getOrDefault(ProductKeys.product_type, "").toString();
	}

	@ApiModelProperty(name = "meta_data", value = "Product metadata")
	public Map<String, String> getMetadata() {
		return (Map<String, String>) map.getOrDefault(
			ProductKeys.meta_data, new HashMap<>());
	}

	@ApiModelProperty(name = "view_list", value = "List of assets related to the product")
	public List<Map<String, String>> getViewList() {
		return (List<Map<String, String>>) map.getOrDefault(
			ProductKeys.view_list, new ArrayList<>());
	}

	@ApiModelProperty(name = "pricing_information", value = "Product pricing information")
	public Map<String, BigDecimal> getPricingInformation() {
		return (Map<String, BigDecimal>) map.getOrDefault(
			ProductKeys.pricing_information, new HashMap<>());
	}

	@ApiModelProperty(name = "attribute_list", value = "Product attributes")
	public Map<String, Object> getAttributeList() {
		return (Map<String, Object>) map.getOrDefault(
			ProductKeys.attribute_list, new HashMap<>());
	}

	@ApiModelProperty(name = "callouts", value = "Product callouts")
	public Map<String, Object> getCallouts() {
		return (Map<String, Object>) map.getOrDefault(
			ProductKeys.callouts, new HashMap<>());
	}

	@ApiModelProperty(name = "product_description", value = "Product description")
	public Map<String, Object> getProductDescription() {
		return (Map<String, Object>) map.getOrDefault(
			ProductKeys.product_description, new HashMap<>());
	}

	@ApiModelProperty(name = "product_link_list", value = "List of links related to the product")
	public List<Map<String, Object>> getProductLinkList() {
		return (List<Map<String, Object>>) map.getOrDefault(
			ProductKeys.product_link_list, new ArrayList<>());
	}

	@ApiModelProperty(name = "product_cards", value = "List of product cards")
	public List<Map<String, String>> getProductCards() {
		return (List<Map<String, String>>) map.getOrDefault(
			ProductKeys.product_cards, new ArrayList<>());
	}

	@ApiModelProperty(name = "breadcrumb_list", value = "List of product breadcrumbs")
	public List<Map<String, String>> getBreadcrumbList() {
		return (List<Map<String, String>>) map.getOrDefault(
			ProductKeys.view_list, new ArrayList<>());
	}

	@ApiModelProperty(name = "reviews_number", value = "Number of product reviews")
	public Integer getReviewsNumber() {
		return Integer.valueOf(
			map.getOrDefault(ProductKeys.reviews_number, "0").toString());
	}

	@ApiModelProperty(name = "average_score", value = "Average product score")
	public Double getAverageScore() {
		return Double.valueOf(
			map.getOrDefault(ProductKeys.average_score, "0").toString());
	}

	@ApiModelProperty(name = "error_message", value = "Error message (if an error occured)")
	public String getErrorMessage() {
		return map.getOrDefault(ProductKeys.error_message, "").toString();
	}

	@ApiModelProperty(access = "private")
	private EnumMap<ProductKeys, Object> map = new EnumMap<ProductKeys, Object>(
		ProductKeys.class);

}
