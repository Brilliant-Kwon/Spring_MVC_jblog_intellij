package jblog.controller;

import jblog.exception.UserDaoException;
import jblog.service.BlogService;
import jblog.service.CateService;
import jblog.service.UserService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@Controller
public class UsersController {
    @Autowired
    UserService userServiceImpl;
    @Autowired
    BlogService blogServiceImpl;
    @Autowired
    CateService cateServiceImpl;

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
        if (success) {//성공했으면
            BlogVo blogVo = new BlogVo();//블로그 vo 만들고
            UserVo userVo = userServiceImpl.getUser(vo.getId()); //성공한 아이디를 가져와서
            //userNo를 blogVo에 붙여야함
            blogVo.setUserNo(userVo.getUserNo());
            blogVo.setBlogTitle(userVo.getUserName() + "의 블로그 입니다.");
            boolean blogSuccess = blogServiceImpl.create(blogVo);
            if (blogSuccess) {
                System.out.println("블로그 생성 성공");
            } else {
                System.out.println("블로그 생성 실패");
            }

            CategoryVo cateVo = new CategoryVo();
            cateVo.setUserNo(userVo.getUserNo());
            cateVo.setCateName("미분류");
            cateVo.setDescription("미분류");

            boolean cateSuccess = cateServiceImpl.createDefault(cateVo);
            if (cateSuccess) {
                System.out.println("미분류 카테고리 생성 성공");
            } else {
                System.out.println("미분류 카테고리 생성 실패");
            }

            return "redirect:/users/joinsuccess";
        } else
            return "redirect:/users/join";
    }

    @RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
    public String joinSuccess() {
        return "users/joinsuccess";
    }

    //로그인 관련
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginform(@ModelAttribute UserVo vo) {
        return "users/loginform";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(@ModelAttribute UserVo vo, BindingResult result, Model model, HttpSession session) {
        System.out.println("로그인 시작");
        System.out.println(vo.getId() + ", " + vo.getPassword());

        System.out.println(result.hasErrors());
        //오류 체크
//        if (result.hasErrors()) {
//            //검증 실패
//            List<ObjectError> errors = result.getAllErrors(); // 모든 에러 가져옴
//            for (ObjectError e : errors) {
//                logger.error("ERROR:" + e);
//            }
//            model.addAllAttributes(result.getModel());
//            return "users/loginform";
//        }

        if (vo.getId().length() == 0 || vo.getPassword().length() == 0) {
            System.err.println("입력값이 없습니다.");
            return "redirect:/users/login";
        } else {
            System.out.println("FORM:" + vo);

            UserVo authUser = userServiceImpl.getUser(vo.getId(), vo.getPassword());
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

    //로그아웃
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        //로그인 정보 삭제
        session.removeAttribute("authUser");
        //세션 무효화
        session.invalidate();
        return "redirect:/";
    }

    //중복 아이디 체크
    @ResponseBody
    @RequestMapping(value = "/idcheck", method = RequestMethod.GET)
    public Object exist(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
        System.out.println("중복체크 매핑");
        System.out.println("id : " + id);

        UserVo vo = userServiceImpl.getUser(id);
        System.out.println("vo:" + vo);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        map.put("data", vo != null ? "exist" : "not exist");
        System.out.println("data: " + map.get("data"));

        return map;
    }
}
