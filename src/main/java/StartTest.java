import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeMap;

public class StartTest {
    public static void main(String[] args) {
        start(TestClassFirst.class);
        start(TestClassSecond.class);
    }

    public static void start(Class<?> testClassName) {
        final int minPriority = 1;
        final int maxPriority = 10;
        System.out.println("Начинается тестирование классов с аннотацией @Test в классе " + testClassName.getSimpleName());
        TreeMap<Integer, Method> methodsMap = new TreeMap<>();
        int iterator = 1;
        for (Method method : testClassName.getDeclaredMethods()
        ) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {     //тк приоритеты только в тестаннотации ставим до 1 приоритета beforе
                if (methodsMap.containsKey(minPriority - 1)) {
                    throw new RuntimeException("В классе " + testClassName + " дублируется beforeSuite");
                } else { //тк приоритеты только в тестаннотации ставим после максим приоритета after
                    methodsMap.put((minPriority - 1), method);
                    ;
                }
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (methodsMap.containsKey(maxPriority * 1000 + 1)) {
                    throw new RuntimeException("В классе " + testClassName + " дублируется afterSuite");
                } else { //тк приоритеты только в тест аннотации ставим после максим приоритета after
                    methodsMap.put(maxPriority * 1000 + 1, method);
                }
            }

            if (method.getAnnotation(Test.class) != null) {
                Test test = method.getAnnotation(Test.class);
                if (methodsMap.containsKey(test.priority() * 1000)) {
                    methodsMap.put(test.priority() * 1000 + iterator, method);
                    iterator++;
                } else {
                    methodsMap.put(test.priority() * 1000, method);
                }
                ;
            }
        }
//это было для теста выстроения, можно закомментить
    /*  System.out.println("Порядок выполнения методов теста " + testClassName.getSimpleName() + ":");
        for (Integer key : methodsMap.keySet()
        ) {
            System.out.println("    Приоритет метода: " + key + "; Исполняемый метод класса: " + methodsMap.get(key).getName());
        }*/
        try {
            Object test = testClassName.newInstance();
            System.out.println("Начало выполнения методов теста " + testClassName.getSimpleName() + ":");
            for (Integer key : methodsMap.keySet()
            ) {
                test.getClass().getMethod(methodsMap.get(key).getName()).invoke(test);
            }
            System.out.println("Тестирование " + testClassName.getSimpleName() + " ОКОНЧЕНО\n");

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
