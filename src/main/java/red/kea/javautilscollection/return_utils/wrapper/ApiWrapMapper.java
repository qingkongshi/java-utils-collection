package red.kea.javautilscollection.return_utils.wrapper;


import org.apache.commons.lang3.StringUtils;

public class ApiWrapMapper {

    private ApiWrapMapper() {
    }

    public static <E> ApiWrapper<E> illegalParameter() {
        return wrap(ApiWrapper.ILLEGAL_ARGUMENT_CODE, ApiWrapper.ILLEGAL_ARGUMENT_MESSAGE);
    }

    public static <E> ApiWrapper<E> illegalParameter(String resultInfo) {
        return wrap(ApiWrapper.ILLEGAL_ARGUMENT_CODE, StringUtils.isBlank(resultInfo) ? ApiWrapper.ILLEGAL_ARGUMENT_MESSAGE : resultInfo);
    }

    public static <E> ApiWrapper<E> error() {
        return wrap(ApiWrapper.ERROR_CODE, ApiWrapper.ERROR_MESSAGE);
    }

    public static <E> ApiWrapper<E> error(String resultInfo) {
        return wrap(ApiWrapper.ERROR_CODE, StringUtils.isBlank(resultInfo) ? ApiWrapper.ERROR_MESSAGE : resultInfo);
    }

    public static <E> ApiWrapper<E> success() {
        return wrap(ApiWrapper.SUCCESS_CODE, ApiWrapper.SUCCESS_MESSAGE);
    }

    public static <E> ApiWrapper<E> success(E o) {
        return wrap(ApiWrapper.SUCCESS_CODE, ApiWrapper.SUCCESS_MESSAGE, o);
    }

    public static <E> ApiWrapper<E> wrap(int result, String resultInfo, E o) {
        return new ApiWrapper<>(result, resultInfo, o);
    }

    public static <E> ApiWrapper<E> wrap(int result, String resultInfo) {
        return wrap(result, resultInfo, null);
    }
}
