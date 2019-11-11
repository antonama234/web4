package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {
    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public void addCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public void removeCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public List<Car> getAllCars() {
        Transaction transaction = session.beginTransaction();
        List<Car> list = session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();
        return list;
    }

    public Car getCarByParam(String brand, String model, String licensePlate) {
        Transaction transaction = session.beginTransaction();
        Car car = (Car) session.createQuery("FROM Car WHERE brand=:brand AND model=:model AND licensePlate=:licensePlate")
                .setParameter("brand", brand)
                .setParameter("model", model)
                .setParameter("licensePlate", licensePlate)
                .uniqueResult();
        transaction.commit();
        session.close();
        return car;
    }

    public void deleteAllCars() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE from Car").executeUpdate();
        transaction.commit();
    }
}
