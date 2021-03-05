package BAPERS3.PAYMENT;

public interface I_Payment {

	abstract void StorePaymentInDatabase();

	/**
	 * 
	 * @param JobPrice
	 * @param cvc
	 * @param PaymentType
	 */
	abstract Payment MakePayment(float JobPrice, int cvc, string PaymentType);

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
	abstract void setPaymentType(string PaymentType);

	abstract float getFinalPrice();

	/**
	 * 
	 * @param FinalAmount
	 */
	abstract void setFinalPrice(float FinalAmount);

	abstract float getJobPrice();

}