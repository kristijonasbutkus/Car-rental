package sda.cars.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.entity.Branches;
import sda.cars.carrental.service.BranchService;

@Controller
public class BranchesController {

    @Autowired
    private BranchService branchService;

    @RequestMapping(value = "/branch", method = RequestMethod.GET)
    public String ShowBranchesList(Model model) {
        model.addAttribute("branchList", branchService.findAll());
        return "branches/branch-list";
    }

    @GetMapping("/branch/{id}")
    public Branches getBranches (@PathVariable Long id){
        return branchService.findById(id);
    };

    @PostMapping("/branch/new")
    public String newBranch (@RequestBody Branches newBranch) {
        branchService.save(newBranch);
        return "branches/branch-list";
    }

//    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
//    public String deleteBranch(@RequestParam("id") long id) {
//        branchService.deleteById(id);
//        return "redirect:branches/branch-list";
//    }
}
