package generics;

/**
 * 使用泛型声明类
 *
 * 在使用的时候为T传入实际类型，这样就可以生成Apply<String>, Apply<Double>等等形式的多个逻辑子类（物理上并不存在）
 */
public class Apple<T> {

    private T info;

    public Apple() {
    }
    public Apple(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
