package com.zzqfsy.test.reactiveTest;

public class Test {

    @org.junit.Test
    public void Test1(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }
            @Override
            public void onNext(Integer var1) {
                System.out.println(var1);
            }
        });
        System.out.println("end");
    }

    @org.junit.Test
    public void test2(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).map(new Observable.Transformer<Integer, String>() {
            @Override
            public String call(Integer from) {
                return "maping " + from;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String var1) {
                System.out.println(var1);
            }
            @Override
            public void onCompleted() {}
            @Override
            public void onError(Throwable t) {}
        });
        System.out.println("end");
    }

    @org.junit.Test
    public void test3(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ "+Thread.currentThread().getName()); //new Thread
//                subscriber.onNext(1);
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }})
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onNext(Integer var1) {
                        System.out.println("Subscriber@ "+Thread.currentThread().getName()); // new Thread
                        System.out.println(var1);
                    }
                });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    @org.junit.Test
    public void Test4(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ " + Thread.currentThread().getName()); // main
//                subscriber.onNext(1);
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        })
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onNext(Integer var1) {
                        System.out.println("Subscriber@ " + Thread.currentThread().getName()); // new Thread
                        System.out.println(var1);
                    }
                });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
