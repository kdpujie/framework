package org.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 此类改造与storm的RotatingMap。 在原有的基础上增加了访问第一个桶的方法。
 * @author pj
 * @param <K>
 * @param <V>
 */
public class RotatingMap<K,V> {

	private Lock lock = new ReentrantLock(false);
    //this default ensures things expire at most 50% past the expiration time
    private static final int DEFAULT_NUM_BUCKETS = 3;

    public static interface ExpiredCallback<K, V> {
        public void expire(K key, V val);
    }

    private LinkedList<HashMap<K, V>> _buckets;

    private ExpiredCallback<K, V> _callback;
    
    public RotatingMap(int numBuckets, ExpiredCallback<K, V> callback) {
        if(numBuckets<2) {
            throw new IllegalArgumentException("numBuckets must be >= 2");
        }
        _buckets = new LinkedList<HashMap<K, V>>();
        for(int i=0; i<numBuckets; i++) {
            _buckets.add(new HashMap<K, V>());
        }

        _callback = callback;
    }

    public RotatingMap(ExpiredCallback<K, V> callback) {
        this(DEFAULT_NUM_BUCKETS, callback);
    }

    public RotatingMap(int numBuckets) {
        this(numBuckets, null);
    }   
    /**
     * 去掉最后一个桶，删除不活跃数据。在链头增加一个新桶
     * @return
     */
    public Map<K, V> rotate() {
    	lock.lock();
        Map<K, V> dead = Collections.emptyMap();
		try {
			dead = _buckets.removeLast();
			_buckets.addFirst(new HashMap<K, V>());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
        if(_callback!=null) {
            for(Entry<K, V> entry: dead.entrySet()) {
                _callback.expire(entry.getKey(), entry.getValue());
            }
        }
        return dead;
    }
    /**
     * 获取第一个桶的副本<br>
     * 第一个桶的数据为执行{@link rotate}方法以后，期间有更新的数据
     * @return
     */
    public Map<K,V> cloneFirst(){
    	Map<K,V> temp = null;
    	lock.lock();
    	try {
    		temp = (HashMap<K, V>)(_buckets.getFirst().clone());
			temp.putAll(_buckets.getFirst());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
    	return temp;
    }
    
    public void put(K key, V value) {
    	lock.lock();
        Iterator<HashMap<K, V>> it = _buckets.iterator();
        HashMap<K, V> bucket = it.next();
        try {
			bucket.put(key, value);
			while(it.hasNext()) {
	            bucket = it.next();
	            bucket.remove(key);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
    }
    
    public boolean containsKey(K key) {
        for(HashMap<K, V> bucket: _buckets) {
            if(bucket.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        for(HashMap<K, V> bucket: _buckets) {
            if(bucket.containsKey(key)) {
                return bucket.get(key);
            }
        }
        return null;
    }
    
    
    public Object remove(K key) {
        for(HashMap<K, V> bucket: _buckets) {
            if(bucket.containsKey(key)) {
                return bucket.remove(key);
            }
        }
        return null;
    }

    public int size() {
        int size = 0;
        for(HashMap<K, V> bucket: _buckets) {
            size+=bucket.size();
        }
        return size;
    }    

}
