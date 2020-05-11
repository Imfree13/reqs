package One;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorExample {

    public static void main(String[] args) {
        //建立线程池并管理
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        try {
//            for (int i =0;i<10;i++){
//                Future future =
//                        executorService.submit(
//                                new Callable< String >() {
//                                    @Override
//                                    public String call() throws Exception {
//                                        Thread.sleep(1000);
//                                        return "hello callable";
//                                    }
//                                });
//            }
//
//
//                System.out.println("欢迎："+future.get());
//        }catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//
//        finally {
//            executorService.shutdown();
//        }
        ExecutorService executorService2 = Executors.newFixedThreadPool(20);
        CompletionService<Integer> completionService = new ExecutorCompletionService(executorService2);
        for (int i=0;i<20;i++){
            final int req = i;
            completionService.submit(new Callable< Integer >() {
                @Override
                public Integer call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+"call---"+req);
                    Thread.sleep(new Random().nextInt(3000));
                    System.out.println("");
                    return req;
                }
            });
        }

        try {
            for (int i=0;i<20;i++){
                if (completionService.take().get()==9){
                    System.out.println("输出了什么");
                    System.out.println(Thread.currentThread().getName()+"aaaaaaaaaasdf："+completionService.take().get());
                }
              //  System.out.println("输出的："+Thread.currentThread().getName()+"call---"+i);
                System.out.println(Thread.currentThread().getName()+"输出了什么："+completionService.take().get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
