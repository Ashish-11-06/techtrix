package com.prushaltech.techtrix.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.ProductRequest;
import com.prushaltech.techtrix.dto.ProductResponse;
import com.prushaltech.techtrix.dto.QuotationProductRequest;
import com.prushaltech.techtrix.dto.QuotationProductResponse;
import com.prushaltech.techtrix.dto.QuotationResponse;
import com.prushaltech.techtrix.entity.Product;
import com.prushaltech.techtrix.entity.Product.ProductType;
import com.prushaltech.techtrix.entity.Quotation;
import com.prushaltech.techtrix.entity.Quotation.Status;
import com.prushaltech.techtrix.entity.QuotationProduct;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.ProductRepository;
import com.prushaltech.techtrix.repository.QuotationProductRepository;
import com.prushaltech.techtrix.repository.QuotationRepository;
import com.prushaltech.techtrix.util.ModelMapperEx;

@Service
public class QuotationProductService {

	@Autowired
	private QuotationProductRepository quotationProductRepository;

	@Autowired
	private QuotationRepository quotationRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapperEx modelMapper;

//	public QuotationProductResponse createQuotationProduct(QuotationProductRequest request) {
//		QuotationProduct qP = quotationProductRepository.findByQuotationIdAndProductId(request.getQuotationId(),
//				request.getProductId());
//		Quotation quotation = quotationRepository.findById(request.getQuotationId()).orElseThrow(
//				() -> new ResourceNotFoundException("Quotation not found with id = " + request.getQuotationId()));
//		Product product = productRepository.findById(request.getProductId()).orElseThrow(
//				() -> new ResourceNotFoundException("Product not found with id = " + request.getProductId()));
//
//		if (qP != null) {
//			if (product.getProductType() == ProductType.Service)
//				throw new IllegalArgumentException("Cannot add Product of Service type again");
//			product.setQuantity(product.getQuantity() + 1);
//			product = productRepository.save(product);
//		}
//
//		Integer gst = 0;
//		if (product.getGst() != null)
//			gst = product.getGst();
//
//		Integer quantity = 1;
//		if (product.getProductType() == ProductType.Hardware) {
//			quantity = product.getQuantity();
//		}
//
//		Double totalGst = quantity * product.getPrice().doubleValue() * (gst / 100.0);
//
//		if (gst == 18)
//			quotation.setTotal18GstTax(quotation.getTotal18GstTax() + totalGst);
//		else if (gst == 28)
//			quotation.setTotal28GstTax(quotation.getTotal28GstTax() + totalGst);
//
//		quotation.setTotalTax(quotation.getTotalTax() + totalGst);
//		quotation.setTotalAmount(quotation.getTotalAmount() + quantity * product.getPrice().doubleValue());
//		quotation.setFinalAmount(Math.round(quotation.getTotalAmount() + quotation.getTotalTax()));
//		quotation.setStatus(Status.Pending);
//		quotationRepository.save(quotation);
//
//		if (qP == null) {
//			QuotationProduct quotationProduct = new QuotationProduct();
//			quotationProduct.setQuotationId(quotation.getQuotationId());
//			quotationProduct.setProductId(product.getProductId());
//
//			return modelMapper.map(quotationProductRepository.save(quotationProduct), QuotationProductResponse.class);
//		} else {
//			return modelMapper.map(qP, QuotationProductResponse.class);
//		}
//	}
	
	public List<QuotationProductResponse> createQuotationProducts(List<QuotationProductRequest> requests) {
	    List<QuotationProductResponse> responses = new ArrayList<>();

	    for (QuotationProductRequest request : requests) {
	        QuotationProduct qP = quotationProductRepository.findByQuotationIdAndProductId(
	                request.getQuotationId(), request.getProductId());

	        Quotation quotation = quotationRepository.findById(request.getQuotationId()).orElseThrow(
	                () -> new ResourceNotFoundException("Quotation not found with id = " + request.getQuotationId()));
	        
	        Product product = productRepository.findById(request.getProductId()).orElseThrow(
	                () -> new ResourceNotFoundException("Product not found with id = " + request.getProductId()));

	        if (qP != null) {
	            if (product.getProductType() == ProductType.Service) {
	                throw new IllegalArgumentException("Cannot add Product of Service type again");
	            }
	            product.setQuantity(product.getQuantity() + 1);
	            product = productRepository.save(product);
	        }

	        Integer gst = (product.getGst() != null) ? product.getGst() : 0;
	        Integer quantity = (product.getProductType() == ProductType.Hardware) ? product.getQuantity() : 1;
	        Double totalGst = quantity * product.getPrice().doubleValue() * (gst / 100.0);

	        if (gst == 18) {
	            quotation.setTotal18GstTax(quotation.getTotal18GstTax() + totalGst);
	        } else if (gst == 28) {
	            quotation.setTotal28GstTax(quotation.getTotal28GstTax() + totalGst);
	        }

	        quotation.setTotalTax(quotation.getTotalTax() + totalGst);
	        quotation.setTotalAmount(quotation.getTotalAmount() + quantity * product.getPrice().doubleValue());
	        quotation.setFinalAmount(Math.round(quotation.getTotalAmount() + quotation.getTotalTax()));
	        quotation.setStatus(Status.Pending);
	        quotationRepository.save(quotation);

	        if (qP == null) {
	            QuotationProduct quotationProduct = new QuotationProduct();
	            quotationProduct.setQuotationId(quotation.getQuotationId());
	            quotationProduct.setProductId(product.getProductId());

	            responses.add(modelMapper.map(quotationProductRepository.save(quotationProduct), QuotationProductResponse.class));
	        } else {
	            responses.add(modelMapper.map(qP, QuotationProductResponse.class));
	        }
	    }

	    return responses;
	}


	public QuotationResponse updateQuotationProduct(Long qId, Long pId, ProductRequest request) {
		Quotation quotation = quotationRepository.findById(qId)
				.orElseThrow(() -> new ResourceNotFoundException("Quotation not found with id = " + qId));
		ProductResponse product = productService.getProductById(pId);

		ProductResponse updatedProduct = productService.updateProduct(pId, request);
		Integer gst = 0, updatedGst = 0;
		if (product.getGst() != null)
			gst = product.getGst();
		if (updatedProduct.getGst() != null)
			updatedGst = updatedProduct.getGst();

		Integer quantity = 1, updatedQuantity = 1;
		if (product.getProductType() == ProductType.Hardware) {
			quantity = product.getQuantity() == 0 ? 1 : product.getQuantity();
			updatedQuantity = updatedProduct.getQuantity() == 0 ? 1 : updatedProduct.getQuantity();
		}

		Double totalGst = quantity * product.getPrice().doubleValue() * (gst / 100.0);
		Double updatedTotalGst = updatedQuantity * updatedProduct.getPrice().doubleValue() * (updatedGst / 100.0);

		if (gst == 18)
			quotation.setTotal18GstTax(quotation.getTotal18GstTax() - totalGst + updatedTotalGst);
		else if (product.getGst() == 28)
			quotation.setTotal28GstTax(quotation.getTotal28GstTax() - totalGst + updatedTotalGst);

		quotation.setTotalTax(quotation.getTotalTax() - totalGst + updatedTotalGst);
		quotation.setTotalAmount(quotation.getTotalAmount() - quantity * product.getPrice().doubleValue()
				+ updatedQuantity * updatedProduct.getPrice().doubleValue());
		quotation.setFinalAmount(Math.round(quotation.getTotalAmount() + quotation.getTotalTax()));
		Quotation updatedQuotation = quotationRepository.save(quotation);

		QuotationResponse quotationResponse = modelMapper.map(updatedQuotation, QuotationResponse.class);

		List<QuotationProductResponse> quotationProducts = modelMapper.mapList(
				quotationProductRepository.findByQuotationId(updatedQuotation.getQuotationId()),
				QuotationProductResponse.class);
		quotationResponse.setQuotationProducts(quotationProducts);

		return quotationResponse;
	}

	public void deleteQuotationProduct(Long qpId) {
		QuotationProduct qP = quotationProductRepository.findById(qpId)
				.orElseThrow(() -> new ResourceNotFoundException("QuotationProduct not found with Id: " + qpId));
		if (qP != null) {
			deleteQuotationProduct(qP);
		}
	}

	public void deleteQuotationProduct(QuotationProductRequest request) {
		QuotationProduct qP = quotationProductRepository.findByQuotationIdAndProductId(request.getQuotationId(),
				request.getProductId());
		if (qP != null) {
			deleteQuotationProduct(qP);
		}
	}

	public void deleteQuotationProduct(QuotationProduct request) {
		Quotation quotation = quotationRepository.findById(request.getQuotationId())
				.orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));
		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		Integer gst = 0;
		if (product.getGst() != null)
			gst = product.getGst();

		Integer quantity = 0;
		if (product.getProductType() == ProductType.Hardware) {
			quantity = product.getQuantity();
		}

		Double totalGst = quantity * product.getPrice().doubleValue() * (gst / 100.0);

		if (gst == 18)
			quotation.setTotal18GstTax(quotation.getTotal18GstTax() - totalGst);
		else if (gst == 28)
			quotation.setTotal28GstTax(quotation.getTotal28GstTax() - totalGst);
		quotation.setTotalTax(quotation.getTotalTax() - totalGst);
		quotation.setTotalAmount(quotation.getTotalAmount() - quantity * product.getPrice().doubleValue());
		quotation.setFinalAmount(Math.round(quotation.getTotalAmount() - quotation.getTotalTax()));
		quotationRepository.save(quotation);
		quotationProductRepository.delete(request);
	}

	public List<QuotationProductResponse> getQuotationProductsByQuotationId(Long qid) {
		Quotation quotation = quotationRepository.findById(qid)
				.orElseThrow(() -> new ResourceNotFoundException("Quotation not found with id: " + qid));
		List<QuotationProduct> quotationProducts = quotationProductRepository
				.findByQuotationId(quotation.getQuotationId());
		return modelMapper.mapList(quotationProducts, QuotationProductResponse.class);
	}
}
