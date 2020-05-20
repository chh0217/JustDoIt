package ch.math.LRU;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.ToString;
import sun.misc.LRUCache;

/**
 * @author chenhang
 * @date 2020/5/12 下午9:28
 * @description 1. get的时候 refresh节点  将节点放置第一个位置 2. put的时候 判断是否已经存在节点 将节点放置第一个位置 2-1. 如果存在   直接刷新节点 2-2. 如果不存在 构建node
 * 放置在第一个位置 2-3. 如果长度超长 还要删除尾部节点
 */
public class LruCache {

    private Map<Integer, Node> map;

    private Node head;

    private Node tail;
    /**
     * 单一双向链表长度大于5，就要删除最早使用过的节点
     */
    private Integer limit = 5;

    public LruCache() {
        new LruCache(5);
    }

    public LruCache(Integer limit) {
        this.limit = limit;
        map = new HashMap<>(5);
    }

    /**
     * 节点越靠后，表示进来得越早
     */

    class Node {

        /**
         * 前节点
         */
        private Node prev;
        /**
         * 后节点
         */
        private Node next;
        private Integer key;
        /**
         * 值
         */
        private Integer val;

        public Node(Integer key,Integer val) {
            this.key=key;
            this.val = val;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }
    }

    public Integer get(Integer key) {
        Node n = map.get(key);
        if (null != n) {
            refreshNode(n);
        }
        return null != n ? n.getVal() : -1;
    }

    /**
     * 更新节点位置
     */
    private void refreshNode(Node n) {
        if (tail == head) {
            return;
        }
        removeNode(n);
        addNode(n);
    }

    //每个key里面是个双向链表
    //put的时候先get看看还有没有值
    //如果没有，新建Node放入map
    //如果有, 则进行判断，如果该值不存在，放到first(这步要对阈值进行判断，超长要将头结点删除)
    //如果该值存在，将前后node关联起来，并把当前node放在最前(这步要对阈值进行判断，超长要将头结点删除)
    public void put(Integer key, Integer t) {

        Node temp = map.get(key);
        //不存在
        if (temp == null) {
            Node n = new Node(key,t);
            addNode(n);
            // 判断长度
            if (map.size() == limit) {
                Integer delKey = removeNode(tail);
                map.remove(delKey);
            }
            map.put(key, n);
            return;
        }
        // 如果存在
        temp.setVal(t);
        refreshNode(temp);

    }

    private Integer removeNode(Node n) {
        // 头节点
        if (head == n && n == tail) {
            head = null;
            tail = null;
        } else if (n == head) {
            head = n.getNext();
            head.setPrev(null);
            n.setNext(null);
        } else if (n == tail) {
            tail = n.getPrev();
            tail.setNext(null);
            n.setPrev(null);
        } else {
            Node prev = n.getPrev();
            Node next = n.getNext();
            prev.setNext(next);
            next.setPrev(prev);
            n.setPrev(null);
            n.setNext(null);
        }
        return n.getKey();
    }

    public void addNode(Node n) {
        if (null != head) {
            n.next = head;
            head.setPrev(n);
        } else {
            tail = n;
        }
        head = n;
    }

    public static void main(String[] args) {
//        LruCache cache = new LruCache(2 /* 缓存容量 */);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.get(1);       // 返回  1
//        System.out.println(cache.get(1));
//        cache.put(3, 3);    // 该操作会使得密钥 2 作废
//        cache.get(2);       // 返回 -1 (未找到)
//        System.out.println(cache.get(2));
//        cache.put(4, 4);    // 该操作会使得密钥 1 作废
//        cache.get(1);       // 返回 -1 (未找到)
//        System.out.println(cache.get(1));
//        cache.get(3);       // 返回  3
//        System.out.println(cache.get(3));
//        cache.get(4);       // 返回  4
//        System.out.println(cache.get(4));

//        ["LRUCache","put","get","put","get","get"]
//[[1],[2,1],[2],[3,2],[2],[3]]
//        LruCache cache = new LruCache(1 /* 缓存容量 */);
//        cache.put(2, 1);
//        System.out.println(cache.get(2));
//        cache.put(3,2);
//        System.out.println(cache.get(2));
//        System.out.println(cache.get(3));

//        ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
//[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
        LruCache cache = new LruCache(3 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
    }


}
