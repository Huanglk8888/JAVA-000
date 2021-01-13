package tech.jiangchen.redisdemo.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLockHelper {

    private long sleepTime = 100;

    /**
     * 直接使用setnx + expire方式获取分布式锁
     * 非原子性
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    @Deprecated
    public boolean lockSetNX(Jedis jedis, String key, String value, int timeout) {
        Long result = jedis.setnx(key, value);
        // result = 1时，设置成功，否则设置失败
        if (result == 1L) {
            return jedis.expire(key, timeout) == 1L;
        } else {
            return false;
        }
    }

    /**
     * 使用Lua脚本，脚本中使用setnex+expire命令进行加锁操作
     *
     * @param jedis
     * @param key
     * @param UniqueId
     * @param seconds
     * @return
     */
    public boolean LockWithLua(Jedis jedis, String key, String UniqueId, int seconds) {
        String lua_scripts = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then" +
                "redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add(key);
        values.add(UniqueId);
        values.add(String.valueOf(seconds));
        Object result = jedis.eval(lua_scripts, keys, values);
        //判断是否成功
        return result.equals(1L);
    }

    /**
     * 在Redis的2.6.12及以后中,使用 set key value [NX] [EX] 命令
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public boolean lock(Jedis jedis,String key, String value, int timeout, TimeUnit timeUnit) {
        SetParams params = new SetParams();
        if (timeUnit.equals(TimeUnit.SECONDS)) {
            params.ex(timeout);
        } else if (timeUnit.equals(TimeUnit.MILLISECONDS)) {
            params.px(timeout);
        }
        // 若锁不存在才进行写操作
        params.nx();
        return "OK".equals(jedis.set(key, value, params));
    }

    /**
     * 自定义获取锁的超时时间
     *
     * @param jedis
     * @param key
     * @param value
     * @param timeout
     * @param waitTime
     * @param timeUnit
     * @return
     * @throws InterruptedException
     */
    public boolean lockWithWaitTime(Jedis jedis, String key, String value, int timeout, long waitTime, TimeUnit timeUnit) throws InterruptedException {
        while (waitTime >= 0) {
            SetParams params = new SetParams();
            if (timeUnit.equals(TimeUnit.SECONDS)) {
                params.ex(timeout);
            } else if (timeUnit.equals(TimeUnit.MILLISECONDS)) {
                params.px(timeout);
            }
            params.nx();
            String result = jedis.set(key, value, params);
            if ("OK".equals(result)) {
                return true;
            }
            waitTime = waitTime - sleepTime;
            Thread.sleep(sleepTime);
        }
        return false;
    }

    /**
     * 错误的解锁方法—直接删除key
     *
     * @param key
     */
    @Deprecated
    public void unlockWithDel(Jedis jedis, String key) {
        jedis.del(key);
    }

    /**
     * 使用Lua脚本进行解锁操纵，解锁的时候验证value值
     *
     * @param jedis
     * @param key
     * @param value
     * @return
     */
    public boolean unlock(Jedis jedis,String key,String value) {
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        return jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
    }
}
