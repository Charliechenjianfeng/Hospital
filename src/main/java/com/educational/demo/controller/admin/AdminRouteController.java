package com.educational.demo.controller.admin;

import com.educational.demo.common.JsonResult;
import com.educational.demo.model.Department;
import com.educational.demo.model.Role;
import com.educational.demo.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author 17875
 */
@Api(tags = "后台：页面路由")
@Controller
@RequestMapping("/admin")
public class AdminRouteController {


    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UnitsService unitsService;

    @Autowired
    private RegistrationTypeService registrationTypeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DrugService drugService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IntroService introService;



    @ApiOperation("页面路由")
    @GetMapping("/page/{moduleName}/{pageName}")
    public ModelAndView getPage(@PathVariable("moduleName") String moduleName, @PathVariable("pageName") String pageName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/" + moduleName + "/" + pageName);
        return modelAndView;
    }



    @ApiOperation("更新用户页面")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @GetMapping("/user/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "admin/user/user-edit";
    }


    @ApiOperation("更新菜单页面")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @GetMapping("/menu/{id}")
    public String editMenu(@PathVariable("id") Long id, Model model) {
        model.addAttribute("menu", menuService.getById(id));
        return "admin/menu/menu-edit";
    }

    @ApiOperation("更新角色页面")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @GetMapping("/role/{id}")
    public String editRole(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.getById(id));
        return "admin/role/role-edit";
    }

    @ApiOperation("更新挂号页面")
    @PreAuthorize("hasAuthority('sys:registration:edit')")
    @GetMapping("/registration/{id}")
    public String editRegistration(@PathVariable("id") Long id, Model model) {
        model.addAttribute("registration", registrationService.selectById(id));
        return "admin/outpatient/registration-edit";
    }


    @ApiOperation("更新单位页面")
    @PreAuthorize("hasAuthority('sys:units:edit')")
    @GetMapping("/units/{id}")
    public String editUnits(@PathVariable("id") Long id, Model model) {
        model.addAttribute("units", unitsService.getById(id));
        return "admin/units/units-edit";
    }

    @ApiOperation("更新类型页面")
    @PreAuthorize("hasAuthority('sys:registrationType:edit')")
    @GetMapping("/registrationType/{id}")
    public String editRegistrationType(@PathVariable("id") Integer id, Model model){
        model.addAttribute("registrationType",registrationTypeService.getById(id));
        return "admin/registrationType/registrationType-edit";
    }

    @ApiOperation("更新科室页面")
    @PreAuthorize("hasAuthority('sys:department:edit')")
    @GetMapping("/department/{id}")
    public String editDepartment(@PathVariable("id") Integer id, Model model){
        model.addAttribute("department",departmentService.selectById(id));
        return "admin/department/department-edit";
    }

    @ApiOperation("更新医生页面")
    @PreAuthorize("hasAuthority('sys:doctor:edit')")
    @GetMapping("/doctor/{id}")
    public String editDoctor(@PathVariable("id") Integer id, Model model){
        model.addAttribute("doctor",doctorService.selectById(id));
        return "admin/doctor/doctor-edit";
    }

    @ApiOperation("更新医生页面")
    @PreAuthorize("hasAuthority('sys:doctor:edit')")
    @GetMapping("/drug/{id}")
    public String editDrug(@PathVariable("id") Integer id, Model model){
        model.addAttribute("drug",drugService.selectById(id));
        return "admin/drug/drug-edit";
    }


    @ApiOperation("更新医生页面")
    @PreAuthorize("hasAuthority('sys:project:edit')")
    @GetMapping("/project/{id}")
    public String editProject(@PathVariable("id") Integer id, Model model){
        model.addAttribute("project",projectService.selectById(id));
        return "admin/project/project-edit";
    }

    @ApiOperation("更新照片页面")
    @PreAuthorize("hasAuthority('blog:photo:edit')")
    @GetMapping("/photo/{id}")
    public String editPhoto(@PathVariable("id") Long id, Model model) {
        model.addAttribute("photo", photoService.getById(id));
        return "admin/photo/photo-edit";
    }

    @ApiOperation("更新文章页面")
    @PreAuthorize("hasAuthority('blog:article:edit')")
    @GetMapping("/article/{id}")
    public String editArticle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", articleService.getById(id));
        model.addAttribute("tagList", tagService.listByArticleId(id));
        return "admin/article/article-edit";
    }


    @ApiOperation("更新分类页面")
    @PreAuthorize("hasAuthority('blog:category:edit')")
    @GetMapping("/category/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        return "admin/category/category-edit";
    }

    @ApiOperation("更新标签页面")
    @PreAuthorize("hasAuthority('blog:tag:edit')")
    @GetMapping("/tag/{id}")
    public String editTag(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tag", tagService.getById(id));
        return "admin/tag/tag-edit";
    }

    @ApiOperation("更新简介页面")
    @PreAuthorize("hasAuthority('doctor:Intro:edit')")
    @GetMapping("/intro/{id}")
    public String editIntro(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", introService.getById(id));
        return "admin/intro/intro-edit";
    }
}
