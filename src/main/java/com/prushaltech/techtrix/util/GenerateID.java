package com.prushaltech.techtrix.util;

import org.apache.commons.lang3.StringUtils;

import com.prushaltech.techtrix.entity.Customer.CustomerType;

public class GenerateID {
	public static String generateCustomerId(CustomerType customerType, long cid) {
		String rNo = String.valueOf(cid);
		String prefix = "CI";
		if (customerType == null || customerType.equals(CustomerType.Corporate))
			prefix = "CC";
		String customerId = prefix + StringUtils.leftPad(rNo, 6, "0");
		return customerId;
	}
	
	public static String generateCustomerId(long cid) {
		return generateCustomerId(null, cid);
	}
	
	public static String generateQuotationId(long qid) {
		String rNo = String.valueOf(qid);
		String quotationId = "Q" + StringUtils.leftPad(rNo, 4, "0");
		return quotationId;
	}

	public static String generateProductId(long pid) {
		String rNo = String.valueOf(pid);
		String productId = "PR" + StringUtils.leftPad(rNo, 10, "0");
		return productId;
	}
}
