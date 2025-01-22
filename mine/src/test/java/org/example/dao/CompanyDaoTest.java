//package org.example.dao;
//
//import org.example.entity.Company;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CompanyDaoTest {
//
//    private static SessionFactory sessionFactory;
//    private Session session;
//
//    private static CompanyDao companyDao;
//
//    @BeforeAll
//    static void setUp() {
//        // Initialize your SessionFactory here (e.g., Hibernate configuration)
//        sessionFactory = HibernateUtil.getSessionFactory(); // Assumes you have a HibernateUtil class
//        companyDao = new CompanyDao();
//    }
//
//    @BeforeEach
//    void beforeEachTest() {
//        // Open a new session before each test
//        session = sessionFactory.openSession();
//        companyDao.setSession(session); // Assuming your DAO allows session injection
//    }
//
//    @Test
//    void getCompanyById() {
//        Company company = new Company("TestComp",
//                LocalDate.of(1990, 1, 1),
//                BigDecimal.valueOf(9000));
//        companyDao.saveCompany(company);
//
//        Company retrievedCompany = companyDao.getCompanyById(company.getId());
//        assertNotNull(retrievedCompany);
//        assertEquals("TestComp", retrievedCompany.getName());
//    }
//
//    @Test
//    public void givenCompany_whenSave_thenGetOk() {
//        Company company = new Company("TestComp",
//                LocalDate.of(1990, 1, 1),
//                BigDecimal.valueOf(9000));
//        companyDao.saveCompany(company);
//
//        Company savedCompany = companyDao.getCompanyById(company.getId());
//        assertNotNull(savedCompany);
//        assertEquals(company.getId(), savedCompany.getId());
//    }
//
//    private List<String> validate(Company company) {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        return validator.validate(company)
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());
//    }
//
//    @Test
//    public void whenInvalidCompanyNameStartsWithSmallLetter_thenAssertConstraintViolations() {
//        Company company = new Company("nestle",
//                LocalDate.of(1990, 1, 1),
//                BigDecimal.valueOf(9000));
//
//        List<String> messages = validate(company);
//
//        assertEquals(1, messages.size());
//        assertEquals("Company name has to start with capital letter!", messages.get(0));
//    }
//
//    @Test
//    public void whenInvalidCompanyName_thenAssertConstraintViolations() {
//        Company company = new Company("Company",
//                LocalDate.of(1990, 1, 1),
//                BigDecimal.valueOf(9000));
//
//        List<String> messages = validate(company);
//
//        assertEquals(1, messages.size());
//        assertTrue(messages.contains("Company and Firm are not valid names!"));
//    }
//
//    @Test
//    public void whenInvalidCompanyInitialCapital_thenAssertConstraintViolations() {
//        Company company = new Company("Nestle",
//                LocalDate.of(1990, 1, 1),
//                BigDecimal.valueOf(9500));
//
//        List<String> messages = validate(company);
//
//        assertEquals(1, messages.size());
//        assertEquals("Initial capital has to be less than or equal to 9000.00", messages.get(0));
//    }
//}
