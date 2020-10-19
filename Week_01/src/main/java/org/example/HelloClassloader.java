package src.main.java.org.example;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author huanglk
 * 逐个字节读取,将读取的所有字节用255减去之后再写入
 */
public class HelloClassloader extends ClassLoader {
    private static final String HELLO_CLASS_FILE = "./Week_01/src/main/java/org/example/Hello.xlass";
    private static final String HELLO_CLASS_NAME = "Hello";
    private static final String HELLO_CLASS_METHOD = "hello";
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] myClassFileData = loadClassFile(name);
        return defineClass(name, myClassFileData, 0, myClassFileData.length);
    }

    private byte[] loadClassFile(String name) {
        byte[] bytes = null;

        FileInputStream in = null;
        ByteArrayOutputStream out = null;

        try {
            //此处绝对地址省略一部分
            in = new FileInputStream(new File(HELLO_CLASS_FILE));
            out = new ByteArrayOutputStream();
            int length;
            while (-1 != (length = in.read())) {
                length = 255 - length;
                out.write(length);
            }
            bytes = out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }


    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassloader().findClass(HELLO_CLASS_NAME);
            Method helloMethod = helloClass.getMethod(HELLO_CLASS_METHOD);
            helloMethod.invoke(helloClass.newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
