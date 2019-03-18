package jblog.controller;

import jblog.service.*;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    @Autowired
    FileUploadService fileUploadService;

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

        Iterator<CategoryVo> cateIter = cateList.iterator();
        System.out.println("이터레이터 : " + cateIter);
        List<PostVo> basicPosts = postServiceImpl.getList(cateIter.next());


        if (!basicPosts.isEmpty()) {
            System.out.println("기본 포스트 : " + basicPosts);
            if (session.getAttribute("postList") != null)
                session.removeAttribute("postList");
            session.setAttribute("postList", basicPosts);

//        if (basicPosts.get(0) != null) {
            System.out.println("기본 포스트의 첫번째 : " + basicPosts.get(0));
            if (session.getAttribute("postContent") != null)
                session.removeAttribute("postContent");
            session.setAttribute("postContent", basicPosts.get(0));
        } else {
            System.out.println("포스트가 없어");
            PostVo postVo = new PostVo();
            postVo.setPostTitle("제목이 없습니다.");
            postVo.setPostContent("내용이 없습니다.");

            if (session.getAttribute("postContent") != null)
                session.removeAttribute("postContent");
            session.setAttribute("postContent", postVo);
        }

        if (blogVo != null) {
            return "blog/main";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/{id}/{cateNum}", method = RequestMethod.GET)
    public String openCate(@PathVariable("id") String id, @PathVariable("cateNum") Long cateNum, HttpSession session) {
        System.out.println("카테고리 오픈 : " + cateNum);

        //todo : 카테고리 넘버로 포스트 리스트 받아오는 것 필요함.
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        return "redirect:/+id";
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
            count = 0;
        }

        System.out.println("map : " + map);
        if (session.getAttribute("postCount") != null) {
            session.removeAttribute("postCount");
        }
        session.setAttribute("postCount", map);

        return "blog/admin_category";
    }

    @RequestMapping(value = {"/{id}/admin/category"}, method = RequestMethod.POST)
    public String writeCate(@PathVariable("id") String id, @ModelAttribute CategoryVo cateVo, HttpSession session) {
        System.out.println("카테고리 작성");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);

        System.out.println("받아온 form : " + cateVo);
        cateVo.setUserNo(userVo.getUserNo());
        System.out.println("유저넘버 추가 : " + cateVo);

        boolean success = cateServiceImpl.createDefault(cateVo);
        if (success) {
            System.out.println("카테고리 추가 성공");
            return "redirect:/" + id;
        } else {
            System.out.println("카테고리 추가 실패");
            return "blog/admin_category";
        }


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

    @RequestMapping(value = "/{id}/admin/upload", method = RequestMethod.POST)
    public String logoUpload(@PathVariable("id") String id, @RequestParam("title") String title, @RequestParam("logo_File") MultipartFile mFile, Model model) {
        if (mFile != null) {
            String saveFilename = fileUploadService.store(mFile);
            //Image URL 생성
            String url = "upload-images/" + saveFilename;

            System.out.println("이미지 저장 : " + url);
            model.addAttribute("urlImage", url);

            UserVo userVo = userServiceImpl.getUser(id);
            BlogVo blogVo = blogServiceImpl.getBlog(userVo);
            blogVo.setBlogTitle(title);
            blogVo.setLogoFile(url);

            boolean success = blogServiceImpl.modify(blogVo);
            if (success) {
                System.out.println("블로그 수정 완료");
                return "redirect:/" + id;
            } else {
                System.out.println("블로그 수정 실패");
                return "blog/admin";
            }


        } else {
            System.out.println("파일 선택 없음");

            return "blog/admin";
        }

    }

}
