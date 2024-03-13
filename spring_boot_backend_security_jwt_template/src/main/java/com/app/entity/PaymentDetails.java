package com.app.entity;

import com.app.enums.PaymentMethod;
import com.app.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDetails {

	private PaymentMethod paymentMethod;

	private PaymentStatus status;

	private String paymentId;

	private String razorpayPaymentLinkId;

	private String razorpayPaymentLinkReferenceId;

	private String razorpayPaymentLinkStatus;

	private String razorpayPaymentId​;

}
