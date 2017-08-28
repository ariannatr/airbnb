package airbnb.controller;

import airbnb.authentication.IAuthenticationFacade;
import airbnb.model.OwnerEntity;
import airbnb.model.Pager;
import airbnb.model.UsersEntity;
import airbnb.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

/**
 * Created by Arianna on 25/8/2017.
 */
@Controller
public class AdminController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    @Autowired
    private UsersService userService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ModelAndView users(@RequestParam("pageSize") Optional<Integer> pageSize,
                              @RequestParam("page") Optional<Integer> page){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users");
        Authentication authentication = authenticationFacade.getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UsersEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<UsersEntity> users=null;
        Pager pager=null;
        users= userService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        pager= new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
        if(users.getTotalElements()!=0){
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("items", users);
        }
        //List<UsersEntity> users=userService.find
        modelAndView.addObject("url","users");
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        return modelAndView;
    }

    @RequestMapping(value="/renters", method = RequestMethod.GET)
    public ModelAndView renters(@RequestParam("pageSize") Optional<Integer> pageSize,
                              @RequestParam("page") Optional<Integer> page){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users");
        Authentication authentication = authenticationFacade.getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UsersEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<UsersEntity> users=null;
        Pager pager=null;
        users= userService.findAllRenters(new PageRequest(evalPage, evalPageSize));
        pager= new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
        if(users.getTotalElements()!=0){
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("items", users);
        }
        //List<UsersEntity> users=userService.find
        modelAndView.addObject("url","renters");
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        return modelAndView;
    }

    @RequestMapping(value="/owners", method = RequestMethod.GET)
    public ModelAndView owners(@RequestParam("pageSize") Optional<Integer> pageSize,
                              @RequestParam("page") Optional<Integer> page){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users");
        Authentication authentication = authenticationFacade.getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UsersEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<UsersEntity> users=null;
        Pager pager=null;
        users= userService.findAllOwners(new PageRequest(evalPage, evalPageSize));
        pager= new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
        if(users.getTotalElements()!=0){
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("items", users);
        }
        //List<UsersEntity> users=userService.find
        modelAndView.addObject("url","owners");
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        return modelAndView;
    }

    @RequestMapping(value="/accept", method = RequestMethod.GET)
    public ModelAndView requests(@RequestParam("pageSize") Optional<Integer> pageSize,
                                @RequestParam("page") Optional<Integer> page){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users");
        Authentication authentication = authenticationFacade.getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UsersEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<OwnerEntity> users=null;
        Pager pager=null;
        users= userService.findAllnotApproved(new PageRequest(evalPage, evalPageSize));
        pager= new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
        if(users.getTotalElements()!=0){
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("items", users);
        }
        //List<UsersEntity> users=userService.find
        modelAndView.addObject("requests","true");
        modelAndView.addObject("url","accept");
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        return modelAndView;
    }

    @RequestMapping(value="/accept/{userID}", method = RequestMethod.POST)
    public ModelAndView approve(@PathVariable("userID") String userID) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
//        modelAndView.addObject("uname", authentication.getName());
//        UsersEntity userS = userService.findByUsername(authentication.getName());
//        modelAndView.addObject("type", String.valueOf(userS.getType()));
        OwnerEntity owner = userService.findOwnerByUsername(userID);
        userService.approveOwner(owner);
        modelAndView.setViewName("redirect:/accept");
        return modelAndView;
    }
}