package CUSTOMER;

public abstract class Discount {

	private float JobPrice;
	private int AccountNumber;
	protected float VolumePerMonth = 0;
	private float FinalPrice;

	/**
	 * 
	 * @param DiscountRate
	 */
	public abstract int setDiscountRate(int DiscountRate);

	/**
	 * 
	 * @param JobPrice
	 * @param AccountNumber
	 * @param DiscountRate
	 */
	public abstract float SetFinalPrice(float JobPrice, int AccountNumber, int DiscountRate);

	public float getFinalPrice() {
		// TODO - implement Discount.getFinalPrice
		throw new UnsupportedOperationException();
	}

}