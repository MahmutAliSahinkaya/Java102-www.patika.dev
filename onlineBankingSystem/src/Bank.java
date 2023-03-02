import java.util.ArrayList;

public class Bank {
    private ArrayList<User> users;

    public Bank() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean authenticateUser(String tcNo, String password) {
        for (User user : users) {
            if (user.getTcNo().equals(tcNo) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public User getUser(String tcNo) {
        for (User user : users) {
            if (user.getTcNo().equals(tcNo)) {
                return user;
            }
        }
        return null;
    }
    public void moneyTransfer(String senderTcNo, String receiverTcNo, double amount) {
        User sender = getUser(senderTcNo);
        User receiver = getUser(receiverTcNo);
        if (sender != null && receiver != null) {
            if (sender.getBalance() >= amount) {
                sender.setBalance(sender.getBalance() - amount);
                receiver.setBalance(receiver.getBalance() + amount);
                System.out.println(amount + " TL " + sender.getTcNo() + " numaralı kullanıcının hesabından " + receiver.getTcNo() + " numaralı kullanıcının hesabına başarılı bir şekilde transfer edilmiştir.");
            } else {
                System.out.println("Hata: Gonderen kullanıcının bakiyesi yetersiz.");
            }
        } else {
            System.out.println("Hata: Gonderen veya alici kullanıcı bulunamadı.");
        }
    }

    public void creditPayment(String tcNo, double amount) {
        User user = getUser(tcNo);
        if (user != null) {
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                System.out.println(amount + " TL kredi ödemesi başarılı bir şekilde yapılmıştır. Yeni bakiye: " + user.getBalance() + " TL");
            } else {
                System.out.println("Hata: Kullanıcının bakiyesi yetersiz.");
            }
        } else {
            System.out.println("Hata: Kullanıcı bulunamadı.");
        }
    }

    public void statementPayment(String tcNo, double amount) {
        User user = getUser(tcNo);
        if (user != null) {
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                System.out.println(amount + " TL kredi kartı ekstresi başarılı bir şekilde ödenmiştir. Yeni bakiye: " + user.getBalance() + " TL");
            } else {
                System.out.println("Hata: Kullanıcının bakiyesi yetersiz.");
            }
        } else {
            System.out.println("Hata: Kullanıcı bulunamadı.");
        }
    }
}
