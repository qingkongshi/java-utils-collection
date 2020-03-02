package red.kea.javautilscollection.mybatis_mapping_class_enum;

/**
 * @author bmr
 * @time 2020-03-02 10:18
 */
public interface BaseEnum<E extends Enum<?>,T> {
    public T getCode();
    public String getName();
}
