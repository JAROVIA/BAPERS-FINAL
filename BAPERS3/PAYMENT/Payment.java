package PAYMENT;

import java.sql.Date;

public class Payment implements I_Payment {

	SQL_PaymentHelper sql_helper;
	private int PaymentID;
	private String PaymentType;
	private Date DateOfPayment;
	private float FinalPrice;
	private float JobPrice;

	public float getFinalPrice() {
		// TODO - implement Payment.getFinalPrice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param FinalAmount
	 */
	public void setFinalPrice(float FinalAmount) {
		// TODO - implement Payment.setFinalPrice
		throw new UnsupportedOperationException();
	}

	public int getPaymentID() {
		// TODO - implement Payment.getPaymentID
		throw new UnsupportedOperationException();
	}

	public String getPaymentType() {
		// TODO - implement Payment.getPaymentType
		throw new UnsupportedOperationException();
	}

	public Date getDateOfPayment() {
		// TODO - implement Payment.getDateOfPayment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param DateOfPayment
	 */
	public void setDateOfPayment(Date DateOfPayment) {
		// TODO - implement Payment.setDateOfPayment
		throw new UnsupportedOperationException();
	}

	public void StorePaymentInDatabase() {
		// TODO - implement Payment.StorePaymentInDatabase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param PaymentType
	 */
	public void setPaymentType(String PaymentType) {
		// TODO - implement Payment.setPaymentType
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobPrice
	 * @param cvc
	 * @param PaymentType
	 */
	public Payment MakePayment(float JobPrice, int cvc, String PaymentType) {
		// TODO - implement Payment.MakePayment
		throw new UnsupportedOperationException();
	}

	public Payment GetPayment() {
		// TODO - implement Payment.GetPayment
		throw new UnsupportedOperationException();
	}

	public float getJobPrice() {
		// TODO - implement Payment.getJobPrice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param FinalPrice
	 * @param PaymentType
	 * @param DateOfPayment
	 * @param PaymentID
	 */
	public static Payment Payment(float FinalPrice, String PaymentType, Date DateOfPayment, int PaymentID) {
		// TODO - implement Payment.Payment
		throw new UnsupportedOperationException();
	}

}