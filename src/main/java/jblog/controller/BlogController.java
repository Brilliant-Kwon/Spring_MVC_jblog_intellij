package jblog.controller;

import jblog.service.*;
import jblog.vo.*;
import oracle.jdbc.proxy.annotation.Post;
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
    CommentService commentServiceImpl;
    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String myBlog(@PathVariable("id") String id, HttpSession session, Model model) {
        System.out.println("블로그 메인");
        System.out.println("블로그 컨트롤러");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

//        if (session.getAttribute("blogUser") != null)
//            session.removeAttribute("blogUser");
//        session.setAttribute("blogUser", userVo);

        model.addAttribute("blogUser", userVo);

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        System.out.println("블로그 정보:" + blogVo.toString());
        model.addAttribute("blogVo", blogVo);

//        UserVo vo = (UserVo) session.getAttribute("authUser");

//        if (session.getAttribute("blogVo") != null) {
//            return "redirect:/";
//        }
//        if (session.getAttribute("blogVo") != null)
//            session.removeAttribute("blogVo");
//        session.setAttribute("blogVo", blogVo);
//        System.out.println("세션에 블로그 정보 등록");

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        System.out.println("카테고리 리스트 : " + cateList);
        model.addAttribute("cateList", cateList);

//        if (session.getAttribute("cateList") != null)
//            session.removeAttribute("cateList");
//        session.setAttribute("cateList", cateList);

        List<PostVo> wholePost = new ArrayList<>();
        for (int i = 0; i < cateList.size(); i++) {
            List<PostVo> postList = postServiceImpl.getList(cateList.get(i));
            wholePost.addAll(postList); // 리스트에 리스트 추가
        }

        System.out.println("전체 포스트 : " + wholePost);
        model.addAttribute("wholePost", wholePost);
//        if (session.getAttribute("wholePost") != null)
//            session.removeAttribute("wholePost");
//        session.setAttribute("wholePost", wholePost);

        Iterator<CategoryVo> cateIter = cateList.iterator();
        System.out.println("이터레이터 : " + cateIter);
        List<PostVo> basicPosts = postServiceImpl.getList(cateIter.next());

        if (!basicPosts.isEmpty()) {
            System.out.println("기본 포스트 리스트 : " + basicPosts);
//            if (session.getAttribute("postList") != null)
//                session.removeAttribute("postList");
//            session.setAttribute("postList", basicPosts);
            model.addAttribute("postList", basicPosts);

//        if (basicPosts.get(0) != null) {
            System.out.println("기본 포스트의 첫번째 : " + basicPosts.get(0));
            model.addAttribute("postContent", basicPosts.get(0));

            List<CommentVo> commList = commentServiceImpl.getComments(basicPosts.get(0).getPostNo());
            if (!commList.isEmpty()) {
                System.out.println("댓글 목록 : " + commList);
                model.addAttribute("commentList", commList);
            } else {
                System.out.println("댓글 없음");
            }
//            if (session.getAttribute("postContent") != null)
//                session.removeAttribute("postContent");
//            session.setAttribute("postContent", basicPosts.get(0));
        } else {//포스트 리스트가 비었으면
            System.out.println("포스트가 없어");
            PostVo postVo = new PostVo();
            postVo.setPostTitle("제목이 없습니다.");
            postVo.setPostContent("내용이 없습니다.");
            model.addAttribute("postContent", postVo);

//            if (session.getAttribute("postContent") != null)
//                session.removeAttribute("postContent");
//            session.setAttribute("postContent", postVo);
        }

        if (blogVo != null) {
            return "blog/main";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/{id}/whole", method = RequestMethod.GET)
    public String myBlogWhole(@PathVariable("id") String id, HttpSession session, Model model) {
        System.out.println("블로그 메인");
        System.out.println("블로그 컨트롤러");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());
        System.out.println("전체 오픈");

//        if (session.getAttribute("blogUser") != null)
//            session.removeAttribute("blogUser");
//        session.setAttribute("blogUser", userVo);

        model.addAttribute("blogUser", userVo);

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        System.out.println("블로그 정보:" + blogVo.toString());
        model.addAttribute("blogVo", blogVo);

//        UserVo vo = (UserVo) session.getAttribute("authUser");

//        if (session.getAttribute("blogVo") != null) {
//            return "redirect:/";
//        }
//        if (session.getAttribute("blogVo") != null)
//            session.removeAttribute("blogVo");
//        session.setAttribute("blogVo", blogVo);
//        System.out.println("세션에 블로그 정보 등록");

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        System.out.println("카테고리 리스트 : " + cateList);
        model.addAttribute("cateList", cateList);

//        if (session.getAttribute("cateList") != null)
//            session.removeAttribute("cateList");
//        session.setAttribute("cateList", cateList);

        List<PostVo> wholePost = new ArrayList<>();
        for (int i = 0; i < cateList.size(); i++) {
            List<PostVo> postList = postServiceImpl.getList(cateList.get(i));
            wholePost.addAll(postList); // 리스트에 리스트 추가
        }

        System.out.println("전체 포스트 : " + wholePost);
        model.addAttribute("wholePost", wholePost);
//        if (session.getAttribute("wholePost") != null)
//            session.removeAttribute("wholePost");
//        session.setAttribute("wholePost", wholePost);
//
//        Iterator<CategoryVo> cateIter = cateList.iterator();
//        System.out.println("이터레이터 : " + cateIter);
//        List<PostVo> basicPosts = postServiceImpl.getList(cateIter.next());

        if (!wholePost.isEmpty()) {
            System.out.println("전체 포스트 리스트 : " + wholePost);
//            if (session.getAttribute("postList") != null)
//                session.removeAttribute("postList");
//            session.setAttribute("postList", basicPosts);
            model.addAttribute("postList", wholePost);

//        if (basicPosts.get(0) != null) {
            System.out.println("전체 포스트의 첫번째 : " + wholePost.get(0));
            model.addAttribute("postContent", wholePost.get(0));

            List<CommentVo> commList = commentServiceImpl.getComments(wholePost.get(0).getPostNo());
            if (!commList.isEmpty()) {
                System.out.println("댓글 목록 : " + commList);
                model.addAttribute("commentList", commList);
            } else {
                System.out.println("댓글 없음");
            }
//            if (session.getAttribute("postContent") != null)
//                session.removeAttribute("postContent");
//            session.setAttribute("postContent", basicPosts.get(0));
        } else {//포스트 리스트가 비었으면
            System.out.println("포스트가 없어");
            PostVo postVo = new PostVo();
            postVo.setPostTitle("제목이 없습니다.");
            postVo.setPostContent("내용이 없습니다.");
            model.addAttribute("postContent", postVo);

//            if (session.getAttribute("postContent") != null)
//                session.removeAttribute("postContent");
//            session.setAttribute("postContent", postVo);
        }

        if (blogVo != null) {
            return "blog/main";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/{id}/category/{cateNum}", method = RequestMethod.GET)
    public String openCate(@PathVariable("id") String id, @PathVariable("cateNum") Long cateNum, HttpSession session, Model model) {
        System.out.println("카테고리 오픈 : " + cateNum);

        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        model.addAttribute("blogUser", userVo);

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        System.out.println("블로그 정보:" + blogVo.toString());
        model.addAttribute("blogVo", blogVo);

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        System.out.println("카테고리 리스트 : " + cateList);
        model.addAttribute("cateList", cateList);

        CategoryVo cateVo = new CategoryVo();
        cateVo.setCateNo(cateNum);

        List<PostVo> postList = postServiceImpl.getList(cateVo);
        if (!postList.isEmpty()) {
            System.out.println("가져온 포스트 리스트 : " + postList);
            model.addAttribute("postList", postList);
//            if (session.getAttribute("postList") != null)
//                session.removeAttribute("postList");
//            session.setAttribute("postList", postList);

            System.out.println("포스트 리스트의 첫번째 : " + postList.get(0));
            model.addAttribute("postContent", postList.get(0));

            List<CommentVo> commList = commentServiceImpl.getComments(postList.get(0).getPostNo());
            if (!commList.isEmpty()) {
                System.out.println("댓글 목록 : " + commList);
                model.addAttribute("commentList", commList);
            } else {
                System.out.println("댓글 없음");
            }
//            if (session.getAttribute("postContent") != null)
//                session.removeAttribute("postContent");
//            session.setAttribute("postContent", postList.get(0));
            System.out.println("포스트 컨텐트 셋완료");
        } else {//포스트 리스트가 비었으면
            System.out.println("포스트가 없어");
            PostVo postVo = new PostVo();
            postVo.setPostTitle("제목이 없습니다.");
            postVo.setPostContent("내용이 없습니다.");

            model.addAttribute("postContent", postVo);

//            if (session.getAttribute("postContent") != null)
//                session.removeAttribute("postContent");
//            session.setAttribute("postContent", postVo);
        }

        return "blog/main";
    }

    @RequestMapping(value = "/{id}/post/{postNum}", method = RequestMethod.GET)
    public String post(@PathVariable("id") String id, @PathVariable("postNum") Long postNum, Model model) {
        System.out.println("포스트 오픈");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        model.addAttribute("blogUser", userVo);

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        model.addAttribute("blogVo", blogVo);

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        model.addAttribute("cateList", cateList);

        PostVo postVo = postServiceImpl.getPost(postNum);
        model.addAttribute("postContent", postVo);

        System.out.println("포스트 번호 : " + postVo.getPostNo());
        List<CommentVo> commList = commentServiceImpl.getComments(postVo.getPostNo());
        if (!commList.isEmpty()) {
            System.out.println("댓글 목록 : " + commList);
            model.addAttribute("commentList", commList);
        } else {
            System.out.println("댓글 없음");
        }

        CategoryVo cateVo = cateServiceImpl.getbyNo(postVo.getCateNo());
        List<PostVo> postList = postServiceImpl.getList(cateVo);
        System.out.println("가져온 포스트 리스트 : " + postList);
        model.addAttribute("postList", postList);

        return "blog/main";
    }

    @RequestMapping(value = {"/{id}/admin/basic", "/{id}/admin/"}, method = RequestMethod.GET)
    public String admin(@PathVariable("id") String id, Model model) {
        System.out.println("블로그 관리");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        model.addAttribute("blogVo", blogVo);

        return "blog/admin";
    }

    @RequestMapping(value = {"/{id}/admin/category"}, method = RequestMethod.GET)
    public String adminCate(@PathVariable("id") String id, HttpSession session, Model model) {
        System.out.println("카테고리 관리");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        model.addAttribute("blogVo", blogVo);

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        System.out.println("카테고리 리스트 : " + cateList);

        model.addAttribute("cateList", cateList);

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
    public String writeCate(@PathVariable("id") String id, @ModelAttribute CategoryVo cateVo, Model model) {
        System.out.println("카테고리 작성");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        model.addAttribute("blogVo", blogVo);

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

    @RequestMapping(value = "/{id}/admin/delete/{cateNo}")
    public String cateDelete(@PathVariable("id") String id, @PathVariable("cateNo") Long cateNo) {
        System.out.println("카테고리 삭제 컨트롤러");

        boolean success = cateServiceImpl.deleteCate(cateNo);
        if (success) {
            System.out.println("카테고리 삭제 성공");
        } else {
            System.out.println("카테고리 삭제 실패");
        }
        return "redirect:/" + id + "/admin/category";
    }

    @RequestMapping(value = {"/{id}/admin/write"}, method = RequestMethod.GET)
    public String blogWrite(@PathVariable("id") String id, Model model) {
        System.out.println("글쓰기 화면");
        System.out.println("id: " + id);
        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        model.addAttribute("blogVo", blogVo);

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        System.out.println("카테고리 리스트 : " + cateList);

        model.addAttribute("cateList", cateList);

        return "blog/write";
    }

    @RequestMapping(value = {"/{id}/admin/write"}, method = RequestMethod.POST)
    public String blogWriteAction(@PathVariable("id") String id, @ModelAttribute PostVo postVo, Model model) {
        System.out.println("글쓰기 액션");
        System.out.println("id: " + id);

        UserVo userVo = userServiceImpl.getUser(id);
        System.out.println("userVo : " + userVo.toString());

        BlogVo blogVo = blogServiceImpl.getBlog(userVo);
        model.addAttribute("blogVo", blogVo);

        List<CategoryVo> cateList = cateServiceImpl.getList(userVo);
        System.out.println("카테고리 리스트 : " + cateList);

        model.addAttribute("cateList", cateList);


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

    @RequestMapping(value = "{id}/comment/{postNo}/{authNo}", method = RequestMethod.POST)
    public String CommentWrite(@PathVariable("id") String id, @PathVariable("postNo") Long postNo, @PathVariable("authNo") Long authNo, @ModelAttribute CommentVo commentVo) {
        System.out.println("댓글 작성");

        System.out.println("댓글 vo: " + commentVo);
        boolean success = commentServiceImpl.create(commentVo);

        if (success) {
            System.out.println("댓글 작성 성공");
        } else {
            System.out.println("댓글 작성 실패");
        }

        return "redirect:/" + id + "/post/" + postNo;
    }


}
