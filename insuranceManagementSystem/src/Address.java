public interface Address {

        String getAddress();
    }

    class HomeAddress implements Address {
        private String address;

        public HomeAddress(String address) {
            this.address = address;
        }

        @Override
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    class BusinessAddress implements Address {
        private String address;

        public BusinessAddress(String address) {
            this.address = address;
        }

        @Override
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    class AddressManager {
        public static void addAddress(User user, Address address) {
            user.getAddress().add(address);
        }

        public static void removeAddress(User user, Address address) {
            user.getAddress().remove(address);
        }
    }
