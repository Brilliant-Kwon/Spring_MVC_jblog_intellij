package jblog.controller;

import jblog.service.BlogService;
import jblog.service.CateService;
import jblog.service.PostService;
import jblog.service.UserService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {
    @Autowired
    BlogService blogServiceImpl;
    @Autowired
    UserService userServiceImpl;
    @Autowired
    CateService cateServiceImpl;
    @Autowired
    PostService postServiceImpl;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String myBlog(@PathVariable("id") String id, HttpSession session) {
        System.out.println("블로그 메인");
        System.out.println("블로그 컨트롤러");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        System.out.println("블로그 정보:" + blogVo.toString());

//        UserVo vo = (UserVo) session.getAttribute("authUser");

//        if (session.getAttribute("blogVo") != null) {
//            return "redirect:/";
//        }
        if (session.getAttribute("blogVo") != null)
            session.removeAttribute("blogVo");
        session.setAttribute("blogVo", blogVo);
        System.out.println("세션에 블로그 정보 등록");

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        System.out.println("카테고리 리스트 : " + cateList);

        if (session.getAttribute("cateList") != null)
            session.removeAttribute("cateList");
        session.setAttribute("cateList", cateList);

        List<PostVo> wholePost = new ArrayList<>();
        for (int i = 0; i < cateList.size(); i++) {
            List<PostVo> postList = postServiceImpl.getList(cateList.get(i));
            wholePost.addAll(postList); // 리스트에 리스트 추가
        }

        System.out.println("전체 포스트 : " + wholePost);
        if (session.getAttribute("wholePost") != null)
            session.removeAttribute("wholePost");
        session.setAttribute("wholePost", wholePost);

        if (blogVo != null) {
            return "blog/main";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = {"/{id}/admin/basic", "/{id}/admin/"}, method = RequestMethod.GET)
    public String admin(@PathVariable("id") String id, HttpSession session) {
        System.out.println("블로그 관리");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        BlogVo blogVo = (BlogVo) session.getAttribute("blogVo");
        List<CategoryVo> cateList = (List<CategoryVo>) session.getAttribute("cateList");

        return "blog/admin";
    }

    @RequestMapping(value = {"/{id}/admin/category"}, method = RequestMethod.GET)
    public String adminCate(@PathVariable("id") String id, HttpSession session) {
        System.out.println("카테고리 관리");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        System.out.println("카테고리 리스트 : " + cateList);

        Map<Long, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < cateList.size(); i++) {
            List<PostVo> postList = postServiceImpl.getList(cateList.get(i));
            for (int k = 0; k < postList.size(); k++) {
                count++;
            }
            map.put(cateList.get(i).getCateNo(), count);
            count=0;
        }

        System.out.println("map : " + map);
        if (session.getAttribute("postCount") != null) {
            session.setAttribute("postCount",map);
        }


        return "blog/admin_category";
    }

    @RequestMapping(value = {"/{id}/admin/write"}, method = RequestMethod.GET)
    public String blogWrite(@PathVariable("id") String id, HttpSession session) {
        System.out.println("글쓰기 화면");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        return "blog/write";
    }

    @RequestMapping(value = {"/{id}/admin/write"}, method = RequestMethod.POST)
    public String blogWriteAction(@PathVariable("id") String id, @ModelAttribute PostVo postVo, HttpSession session) {
        System.out.println("글쓰기 액션");
        System.out.println("id: " + id);

        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        System.out.println("postVo: " + postVo);

        boolean success = postServiceImpl.write(postVo);

        if (success) {
            System.out.println("글쓰기 성공");
            return "redirect:/" + id;
        } else {
            System.out.println("글쓰기 실패");
            return "blog/write";
        }

    }
}
