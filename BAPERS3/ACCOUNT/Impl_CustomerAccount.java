package BAPERS3.ACCOUNT;

public class Impl_CustomerAccount implements I_CustomerAccount {

	ListOfCustomers listOfCustomers;

	/**
	 * 
	 * @param AccountNumber
	 * @param UserName
	 * @param Email
	 * @param Phone
	 * @param AccountStatus
	 */
	public boolean AddCustomer(int AccountNumber, string UserName, string Email, int Phone, string AccountStatus) {
		// TODO - implement Impl_CustomerAccount.AddCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AccountNumber
	 */
	public CustomerAccountDetails RetrieveCustomer(int AccountNumber) {
		// TODO - implement Impl_CustomerAccount.RetrieveCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AccountNumber
	 * @param NewCustomerStatus
	 */
	public string SetCustomerAccStatus(int AccountNumber, string NewCustomerStatus) {
		// TODO - implement Impl_CustomerAccount.SetCustomerAccStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param PaymentType
	 */
	public void MakePayment(string PaymentType) {
		// TODO - implement Impl_CustomerAccount.MakePayment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AccountNumber
	 */
	public void RemoveCustomer(int AccountNumber) {
		// TODO - implement Impl_CustomerAccount.RemoveCustomer
		throw new UnsupportedOperationException();
	}

	public static Impl_CustomerAccount Impl_CustomerAccount() {
		// TODO - implement Impl_CustomerAccount.Impl_CustomerAccount
		throw new UnsupportedOperationException();
	}

}