package com.educational.demo.controller.admin;

import com.educational.demo.config.WebMvcConfig;
import com.educational.demo.model.Registration;
import com.educational.demo.model.User;
import com.educational.demo.service.*;
import com.educational.demo.session.OnLineCount;
import com.educational.demo.vo.InitInfoVO;
import com.educational.demo.vo.IndexVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-18 15:48
 */
@Api(tags = "后台：控制面板")
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private AccessLogService accessLogService;
    @Autowired
    private BillingDetailsService billingDetailsService;
    @Autowired
    private RegistrationService registrationService;

    @ApiOperation("初始化菜单")
    @ResponseBody
    @GetMapping("/init")
    public ResponseEntity<Object> init(HttpSession session, HttpServletRequest request) {
        Long userId = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("userId".equals(cookie.getName())) {
                userId = Long.valueOf(cookie.getValue());
                break;
            }
        }
        if (userId == null) {
            Object o = session.getAttribute("user");
            if (o != null) {
                User user = (User) o;
                userId = user.getId();
            }
        }
        if (userId != null) {
            InitInfoVO initInfoVO = menuService.menu(userId);
            return new ResponseEntity<>(initInfoVO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("当前用户未登录，菜单初始化失败", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("访问后台首页")
    @GetMapping
    public String toIndex() {
        return "admin/home/index";
    }

    @ApiOperation("查询控制面板数据")
    @ResponseBody
    @GetMapping("/indexData")
    public ResponseEntity<Object> index() {
        IndexVo indexVO = new IndexVo();
        indexVO.setArticleCount(articleService.countAll());
        indexVO.setCategoryCount(categoryService.countAll());
        indexVO.setTagCount(tagService.countAll());
        indexVO.setArticles(articleService.listNewest());
        indexVO.setFrontViews(accessLogService.countByLast7Days());
        indexVO.setBackViews(accessLogService.countByLast7Days());
        indexVO.setAccessLogs(accessLogService.listNewest());
        indexVO.setCountOneDay(billingDetailsService.countOneDay());
        indexVO.setRegistrationCount(registrationService.countAll());
        indexVO.setRegistrationCountByDay(registrationService.countByDay());
        indexVO.setOnLinePeople(OnLineCount.online/2);
        indexVO.setCountArticleViews(articleService.countArticle());
        return new ResponseEntity<>(indexVO, HttpStatus.OK);
    }


    /**
     *查询在线人数
     */
    @RequestMapping("/online")
    public Integer online() {
        return  OnLineCount.online ;
    }

}
