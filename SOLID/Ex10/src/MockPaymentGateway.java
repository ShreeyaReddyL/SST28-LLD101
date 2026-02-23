public class MockPaymentGateway implements PaymentGateway {
    @Override
    public String charge(String studentId, double amount) { return "MOCK-TXN-000"; }
}