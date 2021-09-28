package ua.training.constants;

public class Constants {

    /**
     * Paths
     */
    public static final String REDIRECT_HOME = "redirect:/";
    public static final String REDIRECT_LOGIN_JSP = "redirect:/login.jsp";
    public static final String WEB_INF_ERROR_JSP = "/WEB-INF/error/error.jsp";
    public static final String INSUFFICIENT_FUNDS_ERROR_JSP = "/WEB-INF/error/insufficientFundsError.jsp";
    public static final String ERROR_JSP = "/WEB-INF/error/error.jsp";
    public static final String USER_LIST = "userList";
    public static final String CLIENT_LIST_JSP = "/manager/managerClientList.jsp";
    public static final String REDIRECT_SUCCESS_JSP = "redirect:/success.jsp";
    public static final String SING_UP_JSP = "/singUp.jsp";
    public static final String INDEX_JSP = "/index.jsp";
    public static final String REDIRECT_USERPROFILE_JSP = "redirect:/user/userprofile.jsp";
    public static final String USER_USERBASIS_JSP = "/user/userOrders.jsp";

    /**
     * Constants
     */
    public static final String AMOUNT = "amount";
    public static final String USER_PROFILE = "userProfile";
    public static final String ERROR = "error";
    public static final String INVALID_DATE = "invalidDate";
    public static final String LOGIN_INVALID = "loginInvalid";
    public static final String ROLE = "role";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String INCORRECT = "incorrect";
    public static final String PASS_ERROR = "passError";
    public static final String LOGINED_ERROR = "loginedError";
    public static final String SORT_BY = "sortBy";
    public static final String PAGE = "page";
    public static final String NO_OF_PAGES = "noOfPages";
    public static final String USER_ID = "userID";
    public static final String USER_ORDER_VIEW_JSP = "/user/userOrderView.jsp";
    public static final String USER_MAIN_JSP = "/user/userMain.jsp";
    public static final String MANAGER_ORDER_LIST_JSP = "/manager/managerOrderList.jsp";
    public static final int EARTH_RADIUS = 6371000; //meters


    /**
     * User Regex
     */
    public static final String LOGIN_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$";
    public static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]+";
    public static final String EMAIL_REG = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
    public static final String FIRST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{1,}";
    public static final String LAST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
}
