package PAYMENT;

import java.sql.Date;

public interface I_Payment {

	abstract void StorePaymentInDatabase();

	/**
	 * 
	 * @param JobPrice
	 * @param cvc
	 * @param PaymentType
	 */
	abstract Payment MakePayment(float JobPrice, int cvc, String PaymentType);

	abstract Payment GetPayment();

	abstract int getPaymentID();

	/**
	 * 
	 * @param DateOfPayment
	 */
	abstract void setDateOfPayment(Date DateOfPayment);

	/**
	 * 
	 * @param PaymentType
	 */
	abstract void setPaymentType(String PaymentType);

	abstract float getFinalPrice();

	/**
	 * 
	 * @param FinalAmount
	 */
	abstract void setFinalPrice(float FinalAmount);

	abstract float getJobPrice();

}