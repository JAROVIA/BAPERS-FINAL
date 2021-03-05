package BAPERS3.ACCOUNT;

public interface I_CustomerAccount {

	/**
	 * 
	 * @param AccountNumber
	 * @param NewCustomerStatus
	 */
	abstract string SetCustomerAccStatus(int AccountNumber, string NewCustomerStatus);

	/**
	 * 
	 * @param CustomerData
	 */
	abstract boolean CreateCustomer(string CustomerData);

	/**
	 * 
	 * @param PaymentType
	 */
	abstract void MakePayment(string PaymentType);

	/**
	 * 
	 * @param AccountNumber
	 * @param UserName
	 * @param Email
	 * @param Phone
	 * @param AccountStatus
	 */
	abstract boolean AddCustomer(int AccountNumber, string UserName, string Email, int Phone, string AccountStatus);

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