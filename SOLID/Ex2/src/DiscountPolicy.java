public interface DiscountPolicy {
    double getDiscountAmount(double subtotal, int distinctLines);
}