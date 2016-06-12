package com.br.zeroaedes;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import java.io.PrintWriter;
import java.io.StringWriter;

import dagger.ObjectGraph;


public class CustomApplication extends Application {

    private static ContainerInject containerInject;
    private ObjectGraph mObjectGraph;

    public static CustomApplication get(Context context) {
        return (CustomApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (containerInject == null)
            containerInject = new ContainerInject(this);

        mObjectGraph = ObjectGraph.create(containerInject);

//        Thread.setDefaultUncaughtExceptionHandler(new InterceptExceptionHandler(this,this.getPackageManager().getClass()));

    }

    public final void inject(Object object) {
        mObjectGraph.inject(object);
    }
}

class InterceptExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final Context myContext;
    private final Class<?> myActivityClass;

    public InterceptExceptionHandler(Context context, Class<?> c) {

        myContext = context;
        myActivityClass = c;
    }

    public void uncaughtException(Thread thread, Throwable exception) {

        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        System.err.println(stackTrace);
        Intent intent = new Intent(myContext, myActivityClass);
        String s = stackTrace.toString();
        intent.putExtra("stacktrace", s);
        myContext.startActivity(intent);
        intent.putExtra("uncaughtException", "Exception is: " + stackTrace.toString());
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
