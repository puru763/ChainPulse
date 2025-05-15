package com.chainplus.supplier.entity;

@Document(collection = "suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @Id
    private ObjectId id;
    private String name;
    private ContactInfo contactInfo;
    private PerformanceMetrics performanceMetrics;
    private List<PriceTrend> pricingTrends;
    private int riskScore;
}

