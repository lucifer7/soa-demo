package util;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j;
import rpc.service.SayHelloService;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucifer on 2016-7-3.
 * Class 反射工具类
 */
@Log4j
public class ReflectUtil {
    /*
    获取同一路径下的子类与实现类
     */
    public static List<Class<?>> getAllAssignedClass(Class<?> cls) throws ClassNotFoundException {
        List<Class<?>> classList = Lists.newArrayList();
        for (Class<?> clz : getClasses(cls)) {
            if (cls.isAssignableFrom(clz) && !cls.equals(clz)) {
                classList.add(clz);
            }
        }
        return classList;
    }

    private static List<Class<?>> getClasses(Class<?> cls) throws ClassNotFoundException {
        String pkg = cls.getPackage().getName();
        String path = pkg.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(path);
        File file = new File(url.getFile());

        return getPackageClass(file, pkg);
    }

    private static List<Class<?>> getPackageClass(File file, String pkg) throws ClassNotFoundException {
        List<Class<?>> classList = Lists.newArrayList();
        if (!file.exists()) {
            return classList;
        }

        for (File f : file.listFiles()) {
            if(f.isDirectory()) {
                classList.addAll(getPackageClass(f, pkg + '.' + f.getName()));
            } else {
                String name = f.getName();
                classList.add(Class.forName(pkg + '.' + name.substring(0, name.length() - 6)));
            }
        }

        return classList;
    }

    public static void main(String[] args) {
        try {
            for (Class<?> clz : getAllAssignedClass(SayHelloService.class)) {
                log.info(clz.getName());
            }
        } catch (ClassNotFoundException e) {
            log.error("Class not found.", e);
        }
    }
}
