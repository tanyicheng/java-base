package com.barrett.vessel;


public class MyHashMap<K,V> {

    MyNode[] arr;
    int size;

    public static void main(String[] args) {
        MyHashMap<Integer,String> map = new MyHashMap<Integer, String>();
        map.put(11,"aa");
        map.put(5,"bb");
        map.put(9,"cc");
        map.put(9,"cc-update");

        map.put(21,"dd");
        map.put(37,"ee");
        map.put(53,"ff");

        System.out.println(map);

        System.out.println(map.get(53));
//        for (int i = 0; i < 100; i++) {
//            myHash(i,16);
//        }
    }

    public MyHashMap() {
        this.arr = new MyNode[16];
    }

    public void put(K key,V value){
        int hashCode = myHash(key.hashCode(), arr.length);
        MyNode<K,V> node = new MyNode<K, V>();
        node.hash=hashCode;
        node.key=key;
        node.value=value;
        node.next=null;

        //获取当前的节点
        MyNode now = arr[hashCode];
        //直接存入
        if(now == null){
            arr[hashCode]=node;
            size++;
        }else{
            //添加链表
            while(now != null){
                //key相同更新值
                if(now.key.equals(key)){
                    now.value=value;
                    break;
                }else if(now.next == null){
                    now.next=node;
                    size++;
                    break;
                }else{
                    now = now.next;
                }
            }
        }
    }

    public V get(K key){
        int hashCode = myHash(key.hashCode(), arr.length);
        MyNode<K,V> node = arr[hashCode];
        if(node == null){
            return null;
        }

        while (node != null){
            if(node.key.equals(key)){
                return node.value;
            }else{
                node = node.next;
            }
        }

        return null;
    }

    //计算hash值
    public static int myHash(int h,int length){
        System.out.println(h+"   "+ (h&(length-1)));
        //length为2的整数幂情况下，和取余的值一样
//        System.out.println(h%length);//取余数
        return h&(length-1);
    }


}
