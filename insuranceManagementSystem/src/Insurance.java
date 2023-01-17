import java.util.Calendar;
import java.util.Date;

public abstract class Insurance {
    private String name;
    private double price;
    private Date insuranceStartDate;
    private Date insuranceEndDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public abstract void calculate();
}

class HealthInsurance extends Insurance {
    @Override
    public void calculate() {
        // TODO hesaplama kriterleri verilince implemente edilecek
    }
}

class ResidenceInsurance extends Insurance {
    @Override
    public void calculate() {
        // TODO hesaplama kriterleri verilince implemente edilecek
    }
}

class TravelInsurance extends Insurance {
    @Override
    public void calculate() {
        // TODO hesaplama kriterleri verilince implemente edilecek
    }
}

class CarInsurance extends Insurance {
    public CarInsurance() {
        this.setName("Kasko");
        this.setPrice(2750);
        this.setInsuranceStartDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 2);
        this.setInsuranceEndDate(calendar.getTime());
    }

    @Override
    public void calculate() {
        this.setPrice(this.getPrice() * 1.2);
    }
}


