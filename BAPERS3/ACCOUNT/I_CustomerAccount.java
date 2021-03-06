package ACCOUNT;

public interface I_CustomerAccount {

	/**
	 * 
	 * @param AccountNumber
	 * @param NewCustomerStatus
	 */
	abstract String SetCustomerAccStatus(int AccountNumber, String NewCustomerStatus);

	/**
	 * 
	 * @param CustomerData
	 */
	abstract boolean CreateCustomer(String CustomerData);

	/**
	 * 
	 * @param PaymentType
	 */
	abstract void MakePayment(String PaymentType);

	/**
	 * 
	 * @param AccountNumber
	 * @param UserName
	 * @param Email
	 * @param Phone
	 * @param AccountStatus
	 */
	abstract boolean AddCustomer(int AccountNumber, String UserName, String Email, int Phone, String AccountStatus);

	/**
	 * 
	 * @param AccountNumber
	 */
	abstract CustomerAccountDetails RetrieveCustomer(int AccountNumber);

	/**
	 * 
	 * @param AccountNumber
	 */
	abstract void RemoveCustomer(int AccountNumber);

}