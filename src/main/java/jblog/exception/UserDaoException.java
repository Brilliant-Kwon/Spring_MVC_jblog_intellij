package jblog.exception;


import jblog.vo.UserVo;

public class UserDaoException extends RuntimeException {
    private UserVo vo = null;

    public UserDaoException() {

    }

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserDaoException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }


    public UserVo getVo() {
        return vo;
    }

    public void setVo(UserVo vo) {
        this.vo = vo;
    }
}
