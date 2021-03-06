package PAYMENT;

import java.sql.Date;

public class CardDetails {

	private String cardType;
	private int cardNumber;
	private String cardHolderName;
	private Date expiryDate;
	private int CVCNumber;

	public String getCardType() {
		return this.cardType;
	}

	public int getCardNumber() {
		return this.cardNumber;
	}

	public String getCardHolderName() {
		return this.cardHolderName;
	}

	public Date getExpiryDate() {
		// TODO - implement CardDetails.getExpiryDate
		throw new UnsupportedOperationException();
	}

	public int getCVCNumber() {
		// TODO - implement CardDetails.getCVCNumber
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param CardDetailsData
	 */
	public static CardDetails CardDetails(String CardDetailsData) {
		// TODO - implement CardDetails.CardDetails
		throw new UnsupportedOperationException();
	}

}