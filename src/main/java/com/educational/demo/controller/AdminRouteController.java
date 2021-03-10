package com.educational.demo.controller;

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
    @PreAuthorize("hasAuthority('sys:registration:edit')")
    @GetMapping("/registrationType/{id}")
    public String editRegistrationType(@PathVariable("id") Integer id, Model model){
        model.addAttribute("registrationType",registrationTypeService.getById(id));
        return "admin/registrationType/registrationType-edit";
    }

    @ApiOperation("更新类型页面")
    @PreAuthorize("hasAuthority('sys:department:edit')")
    @GetMapping("/department/{id}")
    public String editDepartment(@PathVariable("id") Integer id, Model model){
        model.addAttribute("department",departmentService.selectById(id));
        return "admin/department/department-edit";
    }
}
