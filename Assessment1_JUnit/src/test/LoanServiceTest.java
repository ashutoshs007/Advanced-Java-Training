package test;

import org.example.LoanService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    LoanService loanService = new LoanService();

    @Test
    void testValidEligibility() {
        assertTrue(loanService.isEligible(30, 30000));
    }

    @Test
    void testInvalidAge() {
        assertFalse(loanService.isEligible(18, 30000));
    }

    @Test
    void testInvalidSalary() {
        assertFalse(loanService.isEligible(30, 20000));
    }

    @Test
    void testValidEMI() {
        double emi = loanService.calculateEMI(120000, 1);
        assertEquals(10000, emi);
    }

    @Test
    void testInvalidLoanAmount() {
        assertThrows(IllegalArgumentException.class, () ->
                loanService.calculateEMI(0, 2));
    }

    @Test
    void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class, () ->
                loanService.calculateEMI(100000, 0));
    }

    @Test
    void testPremiumCategory() {
        assertEquals("Premium", loanService.getLoanCategory(800));
    }

    @Test
    void testStandardCategory() {
        assertEquals("Standard", loanService.getLoanCategory(650));
    }

    @Test
    void testHighRiskCategory() {
        assertEquals("High Risk", loanService.getLoanCategory(500));
    }


    @Test
    void testNotNullCategory() {
        String category = loanService.getLoanCategory(700);
        assertNotNull(category);
    }


    @Test
    void testBoundaryValues() {
        assertAll(
                () -> assertTrue(loanService.isEligible(21, 25000)),
                () -> assertTrue(loanService.isEligible(60, 25000)),
                () -> assertEquals("Standard", loanService.getLoanCategory(600)),
                () -> assertEquals("Premium", loanService.getLoanCategory(750))
        );
    }
}
