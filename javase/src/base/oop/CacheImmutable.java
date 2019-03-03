package base.oop;

/**
 * 缓存不可变类
 *
 * 这个案例缓存的是String这个不可变类，缓存String创建出来的实例
 */
public class CacheImmutable {
    private static int MAX_SIZE = 100;
    private static CacheImmutable[] cache = new CacheImmutable[MAX_SIZE];
    // 记录缓存实例在缓存中的位置，cache[pos-1]是最新缓存的实例
    private static int pos = 0;
    private final String name;

    public CacheImmutable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 这个方法可以对实例进行缓存，如果已存在，直接返回缓存的实例，不存在则往数组中增加一个
     * 如果缓存池满了，就把对象放在最开始的位置
     */
    public static CacheImmutable valueOf(String name) {
        for (int i = 0; i < MAX_SIZE; i++) {
            if (cache[i] != null && cache[i].getName().equals(name)) {
                return cache[i];
            }
        }

        if (pos == MAX_SIZE) {
            cache[0] = new CacheImmutable(name);
            pos = 1;
        }
        else {
            cache[pos++] = new CacheImmutable(name);
        }
        return cache[pos -1];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == CacheImmutable.class) {
            CacheImmutable ci = (CacheImmutable) obj;
            return name.equals(ci.getName());
        }
        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }
}
