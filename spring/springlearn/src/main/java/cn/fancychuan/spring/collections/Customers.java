package cn.fancychuan.spring.collections;

import cn.fancychuan.spring.innerbean.Person;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Customers {
    private List<Object> lists;
    private Set<Object> sets;
    private Map<Object, Object> maps;
    private Properties properties;

    private Person person;

    public Customers(Person person) {
        this.person = person;
    }

    public Customers() {
    }

    @Override
    public String toString() {
        return "Customer [person=" + person + "]";
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Set<Object> getSets() {
        return sets;
    }

    public void setSets(Set<Object> sets) {
        this.sets = sets;
    }

    public Map<Object, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<Object, Object> maps) {
        this.maps = maps;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
