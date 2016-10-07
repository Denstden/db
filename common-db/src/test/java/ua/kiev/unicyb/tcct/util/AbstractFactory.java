package ua.kiev.unicyb.tcct.util;

/**
 * @Author Denys Storozhenko.
 */
public abstract class AbstractFactory<T> {
	public abstract T create(String name);
}
