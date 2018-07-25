package com.zzqfsy.test.reactiveTest;

public class Observable<T> {
    final OnSubscribe<T> onSubscribe;

    private Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        subscriber.onStart();
        onSubscribe.call(subscriber);
    }

    public interface OnSubscribe<T> {
        void call(Subscriber<? super T> subscriber);
    }

    public <R> Observable<R> map(Transformer<? super T, ? extends R> transformer) {
        return create(new MapOnSubscribe<T, R>(this, transformer));
    }

    public class MapOnSubscribe<T, R> implements Observable.OnSubscribe<R> {
        final Observable<T> source;
        final Observable.Transformer<? super T, ? extends R> transformer;
        public MapOnSubscribe(Observable<T> source, Observable.Transformer<? super T, ? extends R> transformer) {
            this.source = source;
            this.transformer = transformer;
        }
        @Override
        public void call(Subscriber<? super R> subscriber) {
            source.subscribe(new MapSubscriber<R, T>(subscriber, transformer));
        }
    }
    public class MapSubscriber<T, R> extends Subscriber<R> {
        final Subscriber<? super T> actual;
        final Observable.Transformer<? super R, ? extends T> transformer;
        public MapSubscriber(Subscriber<? super T> actual, Observable.Transformer<? super R, ? extends T> transformer) {
            this.actual = actual;
            this.transformer = transformer;
        }
        @Override
        public void onCompleted() {
            actual.onCompleted();
        }
        @Override
        public void onError(Throwable t) {
            actual.onError(t);
        }
        @Override
        public void onNext(R var1) {
            actual.onNext(transformer.call(var1));
        }
    }

    public <R> Observable<R> mapSingle(Transformer<? super T, ? extends R> transformer) {
        return create(new OnSubscribe<R>() { // 生成一个桥接的Observable和 OnSubscribe
            @Override
            public void call(Subscriber<? super R> subscriber) {
                Observable.this.subscribe(new Subscriber<T>() { // 订阅上层的Observable
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }
                    @Override
                    public void onError(Throwable t) {
                        subscriber.onError(t);
                    }
                    @Override
                    public void onNext(T var1) {
                        // 将上层的onSubscribe发送过来的Event，通过转换和处理，转发给目标的subscriber
                        subscriber.onNext(transformer.call(var1));
                    }
                });
            }
        });
    }

    public interface Transformer<T, R> {
        R call(T from);
    }

    // region 多线程
    public Observable<T> subscribeOn(Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onStart();
                // 将事件的生产切换到新的线程。
                scheduler.createWorker().schedule(new Runnable() {
                    @Override
                    public void run() {
                        Observable.this.onSubscribe.call(subscriber);
                    }
                });
            }
        });
    }

    public Observable<T> observeOn(Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onStart();
                Scheduler.Worker worker = scheduler.createWorker();
                Observable.this.onSubscribe.call(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onCompleted();
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable t) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onError(t);
                            }
                        });
                    }
                    @Override
                    public void onNext(T var1) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onNext(var1);
                            }
                        });
                    }
                });
            }
        });
    }
    // endregion
}