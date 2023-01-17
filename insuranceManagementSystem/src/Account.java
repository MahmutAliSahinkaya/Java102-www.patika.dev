import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Account implements Comparable<Account> {
    private AuthenticationStatus authStatus;
    private User user;
    private ArrayList<Insurance> insurances;

    public enum AuthenticationStatus {
        SUCCESS, FAIL
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = (ArrayList<Insurance>) insurances;
    }

    public AuthenticationStatus getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(AuthenticationStatus authStatus) {
        this.authStatus = authStatus;
    }

    public final void showUserInfo() {
        System.out.println("Hesap Bilgileri:" + "\n" +
                "Adı: " + user.getFirstName() + "\n" +
                "Soyadı: " + user.getLastName() + "\n" +
                "E-Mail: " + user.getEmail() + "\n" +
                "Mesleği: " + user.getProfession() + "\n" +
                "Yaşı: " + user.getAge() + "\n" +
                "Son Giriş Tarihi: " + user.getLastLogin());
        for (Address address : user.getAddress()) {
            System.out.println(address.getAddress());
        }
    }

    public void login(String email, String pass) throws InvalidAuthenticationException {
        if (email.equals(getUser().getEmail()) && pass.equals(getUser().getPassword())) {
            setAuthStatus(AuthenticationStatus.SUCCESS);
            System.out.println("Giriş başarılı.");
        } else {
            setAuthStatus(AuthenticationStatus.FAIL);
            throw new InvalidAuthenticationException("Kullanıcı bilgileri hatalı !");
        }
    }

    public abstract void addAddress(String address);

    public abstract void removeAddress(Address address);

    public void addInsurance(Insurance insurance) {
        insurance.calculate();
        this.getInsurances().add(insurance);
    }

    @Override
    public int compareTo(Account o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return Objects.equals(user, account.user);
    }
}

class Individual extends Account {
    public Individual() {
        this.setAuthStatus(AuthenticationStatus.FAIL);
        this.setInsurances(new ArrayList<>());
    }

    public Individual(User user) {
        this();
        this.setUser(user);
    }

    @Override
    public void addAddress(String address) {
        AddressManager.addAddress(this.getUser(), new HomeAddress(address));
    }
}

class Enterprise extends Account {
    public Enterprise() {
        this.setAuthStatus(AuthenticationStatus.FAIL);
        this.setInsurances(new ArrayList<>());
    }

    public Enterprise(User user) {
        this();
        this.setUser(user);
    }

    @Override
    public void addAddress(String address) {
        AddressManager.addAddress(this.getUser(), new BusinessAddress(address));
    }
}
