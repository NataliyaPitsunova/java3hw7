public class TestClassFirst {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("-> TestClassFirst.methodBeforeSuit");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("-> TestClassFirst.methodAfterSuit");
    }

    /*  @AfterSuite
      public void afterSuite2() {
          System.out.println("TestClassFirst.methodAfterSuit");
      }*/
    @Test(priority = 3)
    public void test1() {
        System.out.println("-> TestClassFirst.methodTest - priority 3");
    }

    @Test(priority = 8)
    public void test2() {
        System.out.println("-> TestClassFirst.methodTest - priority 8");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("-> TestClassFirst.methodTest - priority 1");
    }

    @Test(priority = 1)
    public void simpleMethodTest1First() {
        System.out.println("-> simpleMethodTest1First - priority 1");
    }

    @Test(priority = 3)
    public void simpleMethodTest1Sec() {
        System.out.println("-> simpleMethodTest1Second - priority 3");
    }
}
