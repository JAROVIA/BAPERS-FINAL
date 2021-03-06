package ACCOUNT;

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
	public boolean AddCustomer(int AccountNumber, String UserName, String Email, int Phone, String AccountStatus) {
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
	public String SetCustomerAccStatus(int AccountNumber, String NewCustomerStatus) {
		// TODO - implement Impl_CustomerAccount.SetCustomerAccStatus
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean CreateCustomer(String CustomerData) {
		return false;
	}

	/**
	 * 
	 * @param PaymentType
	 */
	public void MakePayment(String PaymentType) {
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