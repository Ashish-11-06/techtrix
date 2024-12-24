package com.prushaltech.techtrix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.QuotationRequest;
import com.prushaltech.techtrix.dto.QuotationResponse;
import com.prushaltech.techtrix.dto.TicketResponse;
import com.prushaltech.techtrix.entity.Customer;
import com.prushaltech.techtrix.entity.Quotation;
import com.prushaltech.techtrix.entity.Ticket.TicketType;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.CustomerRepository;
import com.prushaltech.techtrix.repository.QuotationRepository;
import com.prushaltech.techtrix.util.GenerateID;

@Service
public class QuotationService {

	@Autowired
	private QuotationRepository quotationRepository;

	@Autowired
	private QuotationProductService quotationProductService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	public QuotationResponse createQuotation(QuotationRequest request) {
		Quotation quotation = quotationRepository.findByTicketIdAndCreatedByAndStatus(request.getTicketId(),
				request.getCreatedBy(), Quotation.Status.Initiated);
		if (quotation == null) {
			quotation = new Quotation();
			quotation.setTicketId(request.getTicketId());
			quotation.setCreatedBy(request.getCreatedBy());
			quotation.setComments(request.getComments());
			quotation.setTaxes("Inclusive in quotation");
			quotation.setDelivery("3 or 4 days");
			quotation.setPayment("100 % advance");
			quotation.setWarrantyOrSupport("As per principal");
			quotation.setTransport("Ex Pune");
			quotation.setTotalTax(0.0d);
			quotation.setTotal18GstTax(0.0d);
			quotation.setTotal28GstTax(0.0d);
			quotation.setTotalAmount(0.0d);
			quotation.setFinalAmount(0L);
			quotation.setStatus(Quotation.Status.Initiated);

			if (request.getTicketId() != null) {
				Customer c = customerRepository.findByTicketId(request.getTicketId());
				if (c != null) {
					quotation.setC_customerId(c.getCustomerId());
					quotation.setC_Cust_ID(c.getCust_ID());
					quotation.setC_firstName(c.getFirstName());
					quotation.setC_lastName(c.getLastName());
					quotation.setC_aadharNumber(c.getAadharNumber());
					quotation.setC_email(c.getEmail());
					quotation.setC_phoneNumber(c.getPhoneNumber());
					quotation.setC_address(c.getAddress());
					quotation.setC_zipCode(c.getZipCode());
					quotation.setC_customerType(c.getCustomerType());
					quotation.setC_companyName(c.getCompanyName());
					quotation.setC_isPremium(c.getIsPremium());
				}
			}

			Quotation savedQuotation = quotationRepository.save(quotation);
			savedQuotation.setQuot_ID(GenerateID.generateQuotationId(savedQuotation.getQuotationId()));
			Quotation updatedQuotation = quotationRepository.save(savedQuotation);
			return modelMapper.map(updatedQuotation, QuotationResponse.class);
		}

		return modelMapper.map(quotation, QuotationResponse.class);
	}

	public QuotationResponse updateQuotation(Long quotationId, QuotationRequest request) {
		Quotation quotation = quotationRepository.findById(quotationId)
				.orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));
		if (request.getTicketId() != null) {
			quotation.setTicketId(request.getTicketId());
			Customer c = customerRepository.findByTicketId(request.getTicketId());
			if (c != null) {
				quotation.setC_customerId(c.getCustomerId());
				quotation.setC_Cust_ID(c.getCust_ID());
				quotation.setC_firstName(c.getFirstName());
				quotation.setC_lastName(c.getLastName());
				quotation.setC_aadharNumber(c.getAadharNumber());
				quotation.setC_email(c.getEmail());
				quotation.setC_phoneNumber(c.getPhoneNumber());
				quotation.setC_address(c.getAddress());
				quotation.setC_zipCode(c.getZipCode());
				quotation.setC_customerType(c.getCustomerType());
				quotation.setC_companyName(c.getCompanyName());
				quotation.setC_isPremium(c.getIsPremium());
			}
		}
		if (request.getCreatedBy() != null)
			quotation.setCreatedBy(request.getCreatedBy());
		if (request.getComments() != null)
			quotation.setComments(request.getComments());
		if (request.getTaxes() != null)
			quotation.setTaxes(request.getTaxes());
		if (request.getDelivery() != null)
			quotation.setDelivery(request.getDelivery());
		if (request.getPayment() != null)
			quotation.setPayment(request.getPayment());
		if (request.getWarrantyOrSupport() != null)
			quotation.setWarrantyOrSupport(request.getWarrantyOrSupport());
		if (request.getTransport() != null)
			quotation.setTransport(request.getTransport());
		if (request.getStatus() != null)
			quotation.setStatus(request.getStatus());
		if (quotation.getQuot_ID() == null)
			quotation.setQuot_ID(GenerateID.generateQuotationId(quotationId));
		Quotation savedQuotation = quotationRepository.save(quotation);
		QuotationResponse quotationResponse = modelMapper.map(savedQuotation, QuotationResponse.class);
		quotationResponse.setQuotationProducts(quotationProductService.getQuotationProductsByQuotationId(quotationId));
		return quotationResponse;
	}

	public QuotationResponse getQuotationById(Long qId) {
		Quotation quotation = quotationRepository.findById(qId)
				.orElseThrow(() -> new ResourceNotFoundException("Quotation not found with id = " + qId));
		QuotationResponse quotationResponse = modelMapper.map(quotation, QuotationResponse.class);
		quotationResponse.setQuotationProducts(quotationProductService.getQuotationProductsByQuotationId(qId));
		return quotationResponse;
	}

	public QuotationResponse getQuotationByTicketId(Long id) {
		Quotation quotation = quotationRepository.findByTicketId(id);
		if (quotation == null)
			throw new ResourceNotFoundException("Quotation not found with ticketId: " + id);
		QuotationResponse quotationResponse = modelMapper.map(quotation, QuotationResponse.class);
		quotationResponse.setQuotationProducts(quotationProductService.getQuotationProductsByQuotationId(id));
		return quotationResponse;
	}

	public QuotationResponse getQuotationByUserIdAndInitiatedStatus(Long userId) {
		List<Quotation> quotationList = quotationRepository.findByCreatedByAndStatus(userId,
				Quotation.Status.Initiated);
		if (quotationList == null || quotationList.size() == 0)
			throw new ResourceNotFoundException("Quotation not found with userId: " + userId);
		Quotation quotation = null;
		for (Quotation q : quotationList) {
			TicketResponse t = ticketService.getTicketById(q.getTicketId());
			if (t.getTicketType() == TicketType.Order) {
				quotation = q;
				break;
			}
		}
		if (quotation == null)
			throw new ResourceNotFoundException("Quotation not found with userId: " + userId);
		QuotationResponse quotationResponse = modelMapper.map(quotation, QuotationResponse.class);
		quotationResponse.setQuotationProducts(
				quotationProductService.getQuotationProductsByQuotationId(quotationResponse.getQuotationId()));
		return quotationResponse;
	}

	public List<QuotationResponse> getAllQuotations() {
		return quotationRepository.findByNotInitiatedStatus().stream()
				.map(quotation -> modelMapper.map(quotation, QuotationResponse.class)).collect(Collectors.toList());
	}

	public void deleteQuotation(Long id) {
		Quotation quotation = quotationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));
		quotationRepository.delete(quotation);
	}
}
