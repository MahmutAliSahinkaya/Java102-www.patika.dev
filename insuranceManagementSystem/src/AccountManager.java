import java.util.Scanner;
import java.util.TreeSet;

public class AccountManager {
    private TreeSet<Account> accounts;

    public AccountManager() {
        accounts = new TreeSet<>();
        accounts.add(new Enterprise(new User("Ahmet","Bulut","ahmetbulut@gmail.com","123","çalışmıyor",35)));
        accounts.add(new Individual(new User("Fatih","Kaya","fatihkaya@hotmail.com","1234","öğrenci",22)));
        accounts.add(new Individual(new User("Burcu","Aksu","burcuaksu@gmail.com","12345","öğrenci",23)));
    }
    public Account login() {
        String[] infos = getLoginInfos();
        Account tempAccount = null;

        for (Account account : accounts) {
            if (account.getUser().getEmail().equals(infos[0])) {
                tempAccount = account;
                break;
            }
        }
        if (tempAccount != null) {
            try {
                tempAccount.login(infos[0], infos[1]);
            } catch (InvalidAuthenticationException e) {
                throw new RuntimeException(e);
            }
        }
        return tempAccount;
    }

    public String[] getLoginInfos() {
        String[] infos = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lütfen giriş yapmak için e-mail ve şifrenizi giriniz:");
        System.out.print("E-mail: ");
        infos[0] = scanner.nextLine();
        System.out.print("Şifre: ");
        infos[1] = scanner.nextLine();
        scanner.close();
        return infos;
    }

    public TreeSet<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(TreeSet<Account> accounts) {
        this.accounts = accounts;
    }
}
