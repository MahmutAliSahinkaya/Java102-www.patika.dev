public class Main {
    public static void main(String[] args) {

        User user1 = new User("12345678901", "sifre1", 5000);
        User user2 = new User("23456789012", "sifre2", 3000);

        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);


        String tcNo = "12345678901";
        String password = "sifre1";
        boolean verification = bank.authenticateUser(tcNo, password);
        if (verification) {
            System.out.println(tcNo + " numaralı kullanıcı girişi başarılı.");
        } else {
            System.out.println("Hata: Kullanıcı girişi başarısız.");
        }


        String senderTcNo = "12345678901";
        String receiverTcNo = "23456789012";
        double amount = 1000;
        bank.moneyTransfer(senderTcNo, receiverTcNo, amount);



        String creditTcNo = "23456789012";
        double creditAmount = 2000;
        bank.creditPayment(creditTcNo, creditAmount);

        String ekstreTcNo = "12345678901";
        double ekstreAmount = 1000;
        bank.statementPayment(ekstreTcNo, ekstreAmount);
    }
}

