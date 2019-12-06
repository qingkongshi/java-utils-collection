package red.kea.javautilscollection.return_utils.wrapper;

import lombok.Data;

import java.io.Serializable;


/**
 * The class Wrapper.
 *
 * @param <T> the type parameter @author paascloud.net@gmail.com
 */
@Data
public class ApiWrapper<T> implements Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 4893280118017319089L;

    /**
     * 成功码.
     */
    public static final int SUCCESS_CODE = 20000;

    /**
     * 成功信息.
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 错误码.
     */
    public static final int ERROR_CODE = 50000;

    /**
     * 错误信息.
     */
    public static final String ERROR_MESSAGE = "内部异常";

    /**
     * 错误码：参数非法
     */
    public static final int ILLEGAL_ARGUMENT_CODE = 40000;

    /**
     * 错误信息：参数非法
     */
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";

    /**
     * 错误码：无权限
     */
    public static final int NOT_AUTH_CODE = 40001;

    /**
     * 错误信息：无权限
     */
    public static final String NOT_AUTH_MESSAGE = "权限验证错误";

    /**
     * 编号.
     */
    private int result;

    /**
     * 信息.
     */
    private String resultInfo;

    /**
     * 结果数据
     */
    private T data;

    public ApiWrapper() {
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param result     the result
     * @param resultInfo the resultInfo
     */
    public ApiWrapper(int result, String resultInfo) {
        this(result, resultInfo, null);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param result     the result
     * @param resultInfo the resultInfo
     * @param data       the data
     */
    public ApiWrapper(int result, String resultInfo, T data) {
        super();
        this.result(result).resultInfo(resultInfo).data(data);
    }

    /**
     * Sets the 编号 , 返回自身的引用.
     *
     * @param result the new 编号
     * @return the wrapper
     */
    private ApiWrapper<T> result(int result) {
        this.setResult(result);
        return this;
    }

    /**
     * Sets the 信息 , 返回自身的引用.
     *
     * @param resultInfo the new 信息
     * @return the wrapper
     */
    private ApiWrapper<T> resultInfo(String resultInfo) {
        this.setResultInfo(resultInfo);
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param data the new 结果数据
     * @return the wrapper
     */
    public ApiWrapper<T> data(T data) {
        this.setData(data);
        return this;
    }

    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE == this.code
     */
    public boolean success() {
        return ApiWrapper.SUCCESS_CODE == this.result;
    }

    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
     */
    public boolean error() {
        return !success();
    }

}
