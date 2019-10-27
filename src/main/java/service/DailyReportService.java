package service;

import DAO.DailyReportDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {
    private static DailyReportService dailyReportService;
    private SessionFactory sessionFactory;
    private Long earnings;
    private Long cars;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public DailyReportDao getDailyReportDAO() {
        return new DailyReportDao(sessionFactory.openSession());
    }

    public Long getTotalSoldCars() {
        return cars;
    }

    public Long getTotalEarnings() {
        return earnings;
    }

    public List<DailyReport> getAllDailyReports() {
        return getDailyReportDAO().getAllDailyReport();
    }

    public void closingDay() {
        getDailyReportDAO().addReport(new DailyReport(getTotalEarnings(), getTotalSoldCars()));
    }

    public DailyReport getLastReport() {
        int size = getAllDailyReports().size();
        return getDailyReportDAO().getAllDailyReport().get(size);
    }

    public void deleteAllReports() {
        getDailyReportDAO().removeAllReports();
    }

    public void buyCar(String brand, String model, String licensePlate) {
        Car buyingCar = CarService.getInstance().getCarDao().getCarByParam(brand, model, licensePlate);
        earnings += buyingCar.getPrice();
        cars++;
        CarService.getInstance().getCarDao().removeCar(buyingCar);
    }
}
