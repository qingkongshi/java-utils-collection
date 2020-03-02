package red.kea.javautilscollection.mybatis_mapping_class_enum;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bmr
 * @time 2020-03-02 10:25
 */
public class UniversalEnumHandler<E extends BaseEnum> extends BaseTypeHandler<E> {
    private Class<E> type;
    private E[] enums;

    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     */
    public UniversalEnumHandler(Class<E> type){
        if(type ==null){
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type=type;
//        this.enums=type.getEnumConstants();
//        if(this.enums==null){
//            throw new IllegalArgumentException(type.getSimpleName()+" does not represent an enum type.");
//        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i,(Integer)e.getCode(),jdbcType.TYPE_CODE);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Object code=resultSet.getInt(columnName);
        if(resultSet.wasNull()){
            return null;
        }else{
           return locateEnumStatus(code);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Object code=resultSet.getInt(columnIndex);
        if(resultSet.wasNull()){
            return null;
        }else{
            return locateEnumStatus(code);
        }
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Object code=callableStatement.getInt(columnIndex);
        if(callableStatement.wasNull()){
            return null;
        }else{
            return locateEnumStatus(code);
        }
    }

    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷
     * @param code 数据库中存储的自定义code属性
     * @return code对应的枚举类
     */
    private E locateEnumStatus(Object code){
        final E[] enums = type.getEnumConstants();
        for(E e:enums){
            if(e.getCode().equals(code)){
                return e;
            }
        }

        throw new IllegalArgumentException("未知的枚举类型："+code+",请核对"+type.getSimpleName());
    }
}
