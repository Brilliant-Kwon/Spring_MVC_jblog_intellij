package jblog.controller;

import jblog.exception.UserDaoException;
import jblog.service.UserService;
import jblog.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@Controller
public class UsersController {
    @Autowired
    UserService userServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    //회원가입 관련
    @RequestMapping(value = "/join", method = RequestMethod.GET)//회원가입 폼으로 진행
    public String join(@ModelAttribute UserVo vo) {
        return "users/joinform";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinAction(@ModelAttribute @Valid UserVo vo, BindingResult result,
                             Model model) {


        logger.debug("회원가입 액션");
        logger.debug("회원가입 정보:" + vo);

        System.out.println(result.hasErrors());
        //오류 체크
        if (result.hasErrors()) {
            //검증 실패
            List<ObjectError> errors = result.getAllErrors(); // 모든 에러 가져옴
            for (ObjectError e : errors) {
                logger.error("ERROR:" + e);
            }
            model.addAllAttributes(result.getModel());
            return "users/joinform";
        }

        System.out.println("FORM:" + vo);

        boolean success = false;
        try {
            success = userServiceImpl.join(vo);
        } catch (UserDaoException e) {
            System.err.println("Dao:Error Vo : " + e.getVo());
            e.printStackTrace();//오류 메시지 통째로 출력
        }

        System.out.println("가입 결과 : " + success);
        if (success)
            return "redirect:/users/joinsuccess";
        else
            return "redirect:/users/join";
    }

    @RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
    public String joinSuccess() {
        return "users/joinsuccess";
    }

    //로그인 관련
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginform() {
        return "users/loginform";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "password", required = false) String password, HttpSession session) {
        System.out.println(id + ", " + password);
        if (id.length() == 0 || password.length() == 0) {
            System.err.println("입력값이 없습니다.");
            return "redirect:/users/login";
        } else {
            System.out.println("로그인 시작");
            UserVo authUser = userServiceImpl.getUser(id, password);
            System.out.println(authUser.toString());
            //사용자가 있다면 session에 authUser 적재
            if (authUser != null) {
                System.out.println("유저 있음, 로그인 가능");
                session.setAttribute("authUser", authUser);
                return "redirect:/";
            } else {
                System.out.println("유저 없음");
                return "redirect:/users/login";
            }
        }

    }

}
