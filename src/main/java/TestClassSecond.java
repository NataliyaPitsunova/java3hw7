public class TestClassSecond {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("-> TestClassSecond.methodBeforeSuit");
    }


    @AfterSuite
    public void afterSuite() {
        System.out.println("-> TestClassSecond.methodAfterSuit");
    }


    @Test(priority = 5)
    public void test1() {
        System.out.println("-> TestClassSecond.methodTest - priority 5");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("-> TestClassSecond.methodTest - priority 2");
    }

    @Test(priority = 7)
    public void test3() {
        System.out.println("-> TestClassSecond.methodTest - priority 7");
    }

    @Test(priority = 1)
    public void simpleMethodTest2First() {
        System.out.println("-> simpleMethodTest2First - priority 1");
    }

    @Test(priority = 5)
    public void simpleMethodTest2Sec() {
        System.out.println("-> simpleMethodTest2Second - priority 5");
    }
}



