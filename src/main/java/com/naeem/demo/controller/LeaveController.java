package com.naeem.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naeem.demo.model.Account;
import com.naeem.demo.model.AdminAccount;
import com.naeem.demo.model.Leave;
import com.naeem.demo.repo.AdminRepository;
import com.naeem.demo.repo.LeaveRepository;
import com.naeem.demo.repo.Repositorys;

import jakarta.servlet.http.HttpSession;

@Controller
public class LeaveController {
	
	@Autowired
	Repositorys repo;
	
	@Autowired
	LeaveRepository leaveRepo;
	
	@Autowired
	AdminRepository AdminRepo;
	
	@RequestMapping("/")
	public String home() {
		return"login";
	}
	
	
	@RequestMapping("/createAccount")
	public String CreateAccount() {
		return "createAccount";
		
	}
	
	@RequestMapping("/loginAccount")
	public String loginAccount() {
		return "login";
		
	}
	
	@RequestMapping("/AdmincreateAccount")
	public String AdminCreateAccount() {
		return "AdmincreateAccount";
		
	}
	
	@RequestMapping("/Adminlogin")
	public String Adminlogin() {
		return "Admin-login";
		
	}
	
	@RequestMapping("/CreateAccounts")
	public String account(@RequestParam String name,
            @RequestParam String Email,
            @RequestParam String pass,
            @RequestParam String Cpass,
            Model model) {

// Password length validation
if (pass.length() < 6 || pass.length() > 20) {
model.addAttribute("error", "Password must be between 6 and 20 characters");
return "createAccount";
}

// Password match validation
if (!pass.equals(Cpass)) {
model.addAttribute("error", "Passwords do not match");
return "createAccount";
}

Account acc = new Account(name, Email, pass, Cpass);
repo.save(acc);

return "login";
}
	
	@RequestMapping("/loginAccounts")
	public String LoginPages(@RequestParam String name,@RequestParam String pass, Model model,HttpSession session) {
		Account acc =repo.findByname(name);
		
		if (acc == null  ) {
            model.addAttribute("title", "Error");
            model.addAttribute("message", "Account Not Found");
            
            return "result";
        }
		if(!acc.getPass().equals(pass)) {
			 model.addAttribute("title", "Error");
	            model.addAttribute("message", "Account Not Found");
	            
	            return "result";
		}
    	else {
    		
    		session.setAttribute("user", name);
    		return "redirect:/dashboard"; 
	}
	}
	
	
	@RequestMapping("/AdminCreateAccounts")
	public String AdminAccount(@RequestParam String name,
            @RequestParam String Email,
            @RequestParam String pass,
            @RequestParam String Cpass,
            Model model) {

// Password length validation
if (pass.length() < 6 || pass.length() > 20) {
model.addAttribute("error", "Password must be between 6 and 20 characters");
return "createAccount";
}

// Password match validation
if (!pass.equals(Cpass)) {
model.addAttribute("error", "Passwords do not match");
return "createAccount";
}

AdminAccount acc = new AdminAccount(name, Email, pass, Cpass);
AdminRepo.save(acc);

return "Admin-login";
}
	
	@RequestMapping("/AdminloginAccounts")
	public String AdminLoginPages(@RequestParam String name,@RequestParam String pass, Model model,HttpSession session) {
		AdminAccount acc =AdminRepo.findByname(name);
		
		if (acc == null  ) {
            model.addAttribute("title", "Error");
            model.addAttribute("message", "Account Not Found");
            
            return "result";
        }
		if(!acc.getPass().equals(pass)) {
			 model.addAttribute("title", "Error");
	            model.addAttribute("message", "Account Not Found");
	            
	            return "result";
		}
    	else {
    		
    		session.setAttribute("admin", name);   

    	    return "redirect:/managerDashboard";
	}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
	
	@RequestMapping("/applyLeave")
	public String applyLeave() {
		return"apply-leave";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {

	    String user = (String) session.getAttribute("user");

	    if (user == null) {
	        return "redirect:/";
	    }

	   
	    List<Leave> leaves = leaveRepo.findByUsername(user);

	    model.addAttribute("leaves", leaves);

	    return "dashboard";
	}
	
	@RequestMapping("/saveLeave")
	public String SaveLeave(
	        @RequestParam String type,
	        @RequestParam String fromDate,
	        @RequestParam String toDate,
	        @RequestParam String reason,
	        HttpSession session) {

	    String user = (String) session.getAttribute("user");

	    Leave leave = new Leave(user, type, fromDate, toDate, reason, "PENDING");

	    leaveRepo.save(leave);

	    return "redirect:/dashboard";
	}
	
	
	@RequestMapping("/managerDashboard")
	public String managerDashboard(HttpSession session, Model model) {

//	    String user = (String) session.getAttribute("user");
//
//	    if (user == null) {
//	        return "redirect:/";
//	    }
//
//	    // 🔥 GET ALL LEAVES (not by user)
	    List<Leave> allLeaves = leaveRepo.findAll();
//
	    model.addAttribute("leaves", allLeaves);

	    return "manager-dashboard";
	}
	
	@RequestMapping("/updateStatus")
	public String updateStatus(@RequestParam int id,
	                           @RequestParam String action) {

	    Leave leave = leaveRepo.findById(id).orElse(null);

	    if (leave != null) {
	        if (action.equals("approve")) {
	            leave.setStatus("APPROVED");
	        } else {
	            leave.setStatus("REJECTED");
	        }

	        leaveRepo.save(leave);
	    }

	    return "redirect:/managerDashboard";
	}
	

}
