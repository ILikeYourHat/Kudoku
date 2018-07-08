package pl.laskowski.marcin.playground;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String... args) throws InvocationTargetException, IllegalAccessException {
        TestPlayground playground = new TestPlayground();
        Class curClass = TestPlayground.class;
        Method[] allMethods = curClass.getMethods();
        for(Method method : allMethods) {
            System.out.println(method.getName());
            method.invoke(playground);
        }
    }

}
