public class MockDriverAllocator implements DriverAllocator {
    @Override
    public String allocate(String studentId) { return "MOCK-DRV-99"; }
}