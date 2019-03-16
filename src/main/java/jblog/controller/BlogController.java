package jblog.controller;

import jblog.service.BlogService;
import jblog.service.UserService;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class BlogController {
    @Autowired
    BlogService blogServiceImpl;
    @Autowired
    UserService userServiceImpl;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String myBlog(@PathVariable("id") String id, HttpSession session) {
        System.out.println("블로그 컨트롤러");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        System.out.println("블로그 정보:" + blogVo.toString());

//        UserVo vo = (UserVo) session.getAttribute("authUser");

        if (session.getAttribute("blogVo") != null) {
            session.removeAttribute("");
        }
        session.setAttribute("blogVo", blogVo);


        if (blogVo != null) {
            return "blog/main";
        } else {
            return "redirect:/";
        }
    }

}
