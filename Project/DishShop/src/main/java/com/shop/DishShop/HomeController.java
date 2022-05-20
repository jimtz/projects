
 /*
 
package com.shop.DishShop;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.lang.String;

@Controller
public class HomeController {
    

	

    private static final RequestMethod[] GET = null;
	@RequestMapping("home")
    public ModelAndView guestbook(HttpServletRequest request) {       
        // Prepare the result view (guest.jsp):
        return new ModelAndView("home.jsp");
    }
    @RequestMapping(value="home/sum/{i}", method=RequestMethod.GET)
    @ResponseBody
    public String add_foo(@PathVariable int i) {
    	//b.add_Dish(s.get_item(i)); 
        return "hello";
    }
   
    
}
*/

package com.shop.DishShop;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.lang.String;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class HomeController {
    

    
   
	
	@GetMapping(value="/home",produces = MediaType.APPLICATION_JSON_VALUE)
	//@RequestMapping(value = "/home", method = RequestMethod.GET, produces = { "application/json" })
    public ResponseEntity guestbook(HttpServletRequest request) {       
        // Prepare the result view (guest.jsp):
        //return new ModelAndView("home.jsp");
		Shop s=new Shop();
		//return ResponseEntity.ok(new Session());
		return ResponseEntity.ok(s.items);
    }
    
	

}