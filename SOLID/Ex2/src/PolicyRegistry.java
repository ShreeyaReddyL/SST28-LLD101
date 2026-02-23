import java.util.*;

public class PolicyRegistry {
    private final Map<String, TaxPolicy> taxPolicies = new HashMap<>();
    private final Map<String, DiscountPolicy> discountPolicies = new HashMap<>();

    public void registerTaxPolicy(String type, TaxPolicy policy) { 
        taxPolicies.put(type.toLowerCase(), policy); 
    }
    
    public void registerDiscountPolicy(String type, DiscountPolicy policy) { 
        discountPolicies.put(type.toLowerCase(), policy); 
    }

    public TaxPolicy getTaxPolicy(String type) {
        return taxPolicies.getOrDefault(type.toLowerCase(), () -> 8.0); // 8.0 is default fallback
    }

    public DiscountPolicy getDiscountPolicy(String type) {
        return discountPolicies.getOrDefault(type.toLowerCase(), (sub, lines) -> 0.0); // 0.0 is default
    }
}