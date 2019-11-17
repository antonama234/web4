package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.List;

public class DailyReportDao {
    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public void addReport(DailyReport report) {
        Transaction transaction = session.beginTransaction();
        session.save(report);
        transaction.commit();
        session.close();
    }

    public void removeAllReports() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        transaction.commit();
        session.close();
    }

    public DailyReport last() {
        Transaction transaction = session.beginTransaction();
        BigInteger id = (BigInteger) session.createQuery("select max (day.id) from DailyReport day");
        DailyReport report = (DailyReport) session.createQuery("from DailyReport where id=:id").setParameter("id", id);
        transaction.commit();
        session.close();
        return report;
    }
}