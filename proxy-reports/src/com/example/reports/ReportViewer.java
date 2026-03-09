package com.example.reports;

/**
 * Viewer now depends on Report abstraction instead of concrete file class.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}