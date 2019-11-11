package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import javax.sql.rowset.spi.SyncResolver;
import java.util.List;

public class CarService {
    private static CarService carService;
    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public CarDao getCarDao() {
        return new CarDao(sessionFactory.openSession());
    }

    public boolean addCar(Car car) {
        boolean success = false;
        if (car != null) {
            getCarDao().addCar(car);
            success = true;
        }
        return success;
    }

    public void deleteCar(Car car) {
        getCarDao().removeCar(car);
    }

    public List<Car> getAllCars() {
        return getCarDao().getAllCars();
    }

    public void removeAll() {
        getCarDao().deleteAllCars();
    }
}
