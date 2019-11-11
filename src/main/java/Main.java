import model.Car;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.SessionFactory;
import service.CarService;
import service.DailyReportService;
import servlet.CustomerServlet;
import servlet.DailyReportServlet;
import servlet.NewDayServlet;
import servlet.ProducerServlet;
import util.DBHelper;

public class Main {
    public static void main(String[] args) throws Exception {
        CustomerServlet customer = new CustomerServlet();
        DailyReportServlet report = new DailyReportServlet();
        NewDayServlet day = new NewDayServlet();
        ProducerServlet producer = new ProducerServlet();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(customer), "/customer");
        contextHandler.addServlet(new ServletHolder(report), "/report/*");
        contextHandler.addServlet(new ServletHolder(day), "/newday");
        contextHandler.addServlet(new ServletHolder(producer), "/producer");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
     /*   CarService carService = CarService.getInstance();
        DailyReportService dailyReportService = DailyReportService.getInstance();
        Car car1 = new Car("brand1", "model1", "licensePlate1", 111L);
        Car car2 = new Car("brand2", "model2", "licensePlate2", 222L);
        Car car3 = new Car("brand3", "model3", "licensePlate3", 333L);
        carService.addCar(car1);
        carService.addCar(car2);
        carService.addCar(car3);
        dailyReportService.buyCar("brand1", "model1", "licensePlate1");
        dailyReportService.closingDay();
        dailyReportService.buyCar("brand2", "model2", "licensePlate2");
        dailyReportService.buyCar("brand3", "model3", "licensePlate3");
        dailyReportService.closingDay();
        System.out.println(dailyReportService.getLastReport().getSoldCars());*/
        server.join();
    }
}
