package org.yqj.source.vm;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/28
 */
public class HeapOOM {
    public static void main(String[] args) throws InterruptedException {

//        while (true) {
//            byte[] b = new byte[1024*1024];
//            Thread.sleep(1000);
//        }
//        List<OOMObject> oomObjects = new ArrayList<>();
//        while (true){
//            oomObjects.add(new OOMObject());
//        }
        System.out.println("test");
    }

    public static class OOMObject{}
}
