package com.demo.others.serviceloader.serviceloaderImpl;

import com.demo.others.serviceloader.serviceloaderInterface.IMyServiceLoader;

public class MyServiceLoaderImpl2 implements IMyServiceLoader {
   @Override
   public String sayHello() {
       return "hello2";
   }

   @Override
   public String getName() {
       return "name2";
   }
}
