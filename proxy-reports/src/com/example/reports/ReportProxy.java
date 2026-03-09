package com.example.reports;

/**
 * Proxy handling access control, lazy loading, and caching.
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    private final AccessControl accessControl = new AccessControl();

    private RealReport realReport; // cached real object

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {

        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED -> " + user.getName()
                    + " cannot access report " + reportId);
            return;
        }

        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }

        realReport.display(user);
    }
}