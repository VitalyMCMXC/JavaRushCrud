package test;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class MainController {

    @Resource(name="UserService")
    private UserService userService;

    /**
     * Handles and retrieves all persons and show it in a JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getPersons(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "count", required = false) Integer count,
                             Model model) {
        int itemsPerPage;

        if (count == null || count == 0) itemsPerPage = userService.tableSize();
        else itemsPerPage = count;

        int nOfPages;
        if (itemsPerPage == 0 || userService.tableSize() == 0) nOfPages = 1;
        else nOfPages = (userService.tableSize() % itemsPerPage) == 0 ? (userService.tableSize() / itemsPerPage) : (userService.tableSize()/ itemsPerPage) + 1;

        model.addAttribute("users", userService.getUsersList(page, itemsPerPage));
        model.addAttribute("currentPage", page);
        model.addAttribute("nOfPages", nOfPages);
        model.addAttribute("count", itemsPerPage);
        model.addAttribute("user", new User());

        return "userpage";
    }

    /**
     * Retrieves the add page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String getAdd(Model model) {

        model.addAttribute("userAttribute", new User());

        return "addpage";
    }

    /**
     * Adds a new person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("userAttribute") User user) {

        user.setCreateDate(new Timestamp(new Date().getTime()));
        userService.add(user);

        return "addedpage";
    }

    /**
     * Deletes an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/users/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Integer id,
                         Model model) {
        userService.delete(id);
        model.addAttribute("id", id);

        return "deletedpage";
    }

    /**
     * Retrieves the edit page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/users/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id", required=true) Integer id,
                          Model model) {
        model.addAttribute("userAttribute", userService.get(id));

        return "editpage";
    }

    /**
     * Edits an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("userAttribute") User user,
                           @RequestParam(value="id", required=true) Integer id,
                           Model model) {
        user.setId(id);
        userService.edit(user);
        model.addAttribute("id", id);

        return "editedpage";
    }

    /**
     * Handles and retrieves searched users and show it in a JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public String Search(@RequestParam(value="name", required=true) String name,
                         Model model) {
        model.addAttribute("user", userService.search(name));

        return "searchpage";
    }
}
