public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        Account account = accountManager.login();
        System.out.println("Hoşgeldiniz " + account.getUser().getFirstName() + " " +
                account.getUser().getLastName());
        account.addAddress("Beyoğlu / İstanbul");
        account.addAddress("Muratpaşa / Antalya");
        account.addAddress("Menteşe / Muğla");
        account.addInsurance(new CarInsurance());
        account.showUserInfo();
    }
}


