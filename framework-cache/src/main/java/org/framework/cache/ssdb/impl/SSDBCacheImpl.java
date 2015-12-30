package org.framework.cache.ssdb.impl;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;







import org.common.util.StringUtil;
import org.framework.cache.config.SSDBWriper;
import org.framework.cache.ssdb.SSDBCache;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;

/**
 * ssdb 操作类
 * Date: 2014年8月23日 <br>
 * @author pj
 * */
public class SSDBCacheImpl implements SSDBCache
{
	public Charset charset = SSDBs.DEFAULT_CHARSET;
	
	//ssdb
	private SSDB ssdb;
	
	public SSDBCacheImpl(SSDBWriper ssdbWriper){
		ssdb = ssdbWriper.init();
	}

	/**
	 * 把list<btye[]> 里的数据按默认编码转换成map<string,string><br>\
	 * list的大小必须为偶数
	 * @throws IllegalArgumentException
	 * */
	protected Map<String,String> list2HashMap(List<byte[]> list){
		Map<String,String> map = Collections.emptyMap();
		if(list.size() == 0)return map;
		if (list.size() % 2 != 0)
			throw new IllegalArgumentException("not key-value pairs");
		map = new HashMap<String, String>(list.size()/2);
		Iterator<byte[]> it = list.iterator();
		while(it.hasNext()){
			map.put(StringUtil.byte2String(it.next(),charset),
					StringUtil.byte2String(it.next(),charset));
		}
		return map;
	}
	@Override
	public void set(String key,Object val){
		ssdb.set(key, val);
	}
	@Override
	public void hset(String key,Object hkey,Object hval){
		ssdb.hset(key, hkey, hval);
	}
	
	@Override
	public String get(String key){
		String val = null;
		Response value = ssdb.get(key);
		if(!value.notFound()){
			val = value.asString();
		}
		return val;
	}
	
	@Override
	public Map<String, String> hscanAll(String key){
		return this.hscan(key,-1);
	}
	
	@Override
	public Map<String, String> hscan(String key,int limit){
		return list2HashMap(ssdb.hscan(key,"", "",limit).datas);
	}

	@Override
	public List<String> zkeys(String key, String zkeyStart, Object scoreStart,Object scoreEnd, int limit) {
		List<String> zKeys = Collections.emptyList();
		Response rs = ssdb.zkeys(key, zkeyStart,scoreStart,scoreEnd,limit);
		if(!rs.notFound()){
			zKeys = rs.listString();
		}
		return zKeys;
	}

	@Override
	public List<String> zkeys(String key, int limit) {
		return zkeys(key,"","","",limit);
	}
	
	@Override
	public List<String> zscan(String key, String zkeyStart, Object scoreStart,Object scoreEnd, int limit){
		List<String> list = Collections.emptyList();
		Response rs = ssdb.zscan(key, zkeyStart, scoreStart, scoreEnd, limit);
		if(!rs.notFound()){
			list = rs.listString();
		}
		return list;
	}
	
	@Override
	public List<String> zscanLimit(String key,int limit){
		return zscan(key,"","","",limit);
	}
	
	@Override
	public List<String> zrscan(String key, String zkeyStart, Object scoreStart,Object scoreEnd, int limit){
		List<String> list = Collections.emptyList();
		Response rs = ssdb.zrscan(key, zkeyStart, scoreStart, scoreEnd, limit);
		if(!rs.notFound()){
			list = rs.listString();
		}
		return list;
	}
	
	@Override
	public List<String> zrscanLimit(String key,int limit){
		return zrscan(key,"","","",limit);
	}
}
