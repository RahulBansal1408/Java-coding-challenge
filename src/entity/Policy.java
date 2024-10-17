package entity;

public class Policy {
    private int policyId;
    private String policyName;
    private String policyDetails;

    // Constructors
    public Policy() {}

    public Policy(int policyId, String policyName, String policyDetails) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyDetails = policyDetails;
    }

    // Getters and Setters
    public int getPolicyId() { return policyId; }
    public void setPolicyId(int policyId) { this.policyId = policyId; }

    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }

    public String getPolicyDetails() { return policyDetails; }
    public void setPolicyDetails(String policyDetails) { this.policyDetails = policyDetails; }

    // toString
    @Override
    public String toString() {
        return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyDetails=" + policyDetails + "]";
    }
}
