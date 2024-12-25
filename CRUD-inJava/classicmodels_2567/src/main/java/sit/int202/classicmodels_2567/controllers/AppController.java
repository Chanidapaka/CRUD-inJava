package sit.int202.classicmodels_2567.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sit.int202.classicmodels_2567.entities.Customer;
import sit.int202.classicmodels_2567.entities.Product;
import sit.int202.classicmodels_2567.models.Cart;
import sit.int202.classicmodels_2567.models.CartItem;
import sit.int202.classicmodels_2567.repositories.CustomerRepository;
import sit.int202.classicmodels_2567.repositories.ProductRepository;
import sit.int202.classicmodels_2567.services.CartService;

import java.util.List;

@Controller
public class AppController {
    private final HttpSession httpSession;
    private final CartService cartService;
    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    public AppController(HttpSession httpSession,
                         CartService cartService,
                         sit.int202.classicmodels_2567.repositories.CustomerRepository customerRepository,
                         ProductRepository productRepository
                         ) {
        this.httpSession = httpSession;
        this.cartService = cartService;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/view_cart")
    public String viewCart(Model model) {
        if (httpSession.getAttribute("cart") == null) {
            model.addAttribute("message", "Empty cart !!!");
        }
        return "view_cart";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username
            , @RequestParam String password, Model model) {
        Customer c = customerRepository.findByName(username);
        if (c == null || ! c.getId().toString().equals(password)) {
            model.addAttribute("error", "Invalid username or password");
            return "login_form";
        }
        httpSession.setAttribute("user", c);
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/addToCart")
    public ResponseEntity<Object> addToCart(@RequestParam String pCode, Model model) {
        Cart<String, CartItem> cart = (Cart) httpSession.getAttribute("cart");
        if (cart == null) {
            cart = new Cart<String, CartItem>();
            httpSession.setAttribute("cart", cart);
        }
        System.out.println("asdfghjsdfghjk");
        cartService.addItem(pCode, cart);
        return ResponseEntity.ok(cart.getQuantity());
    }

    @GetMapping("/index")
    public String index(Model model
            , @CookieValue(value = "popup", defaultValue = "") String popup
            , @CookieValue(value = "JSESSIONID", defaultValue = "") String sid) {
        model.addAttribute("page", "index");
        model.addAttribute("popup", popup);
        model.addAttribute("sid", sid);
        httpSession.setAttribute("xxx", "New Data");
        return "index";
    }
//    @GetMapping("/login")
//    public String login(@RequestParam String username
//            , @RequestParam String password) {
//        httpSession.setAttribute("user", username);
//        return "index";
//    }

    @GetMapping("/create-cookie-user")
    public String createCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("user", "Khaitong-Lim");
        cookie.setMaxAge(8 * 24 * 60 * 60);
        response.addCookie(cookie);
        return "redirect:/index";
    }

    @GetMapping("/remove-cookie")
    public String removeCookie(Model model, HttpServletResponse response) {
        response.addCookie(new Cookie("popup", ""));
        return "redirect:/index";
    }

    @GetMapping("/test")
    public  String getContactFirstName(
            @RequestParam() Double price
    ){

//        List<Customer> c = customerRepository.findByContactFirstNameOrderByCustomerName("Kwai");
//        for (Customer customer : c) {
//            System.out.println(customer.getContactFirstName());
//            System.out.println(customer.getContactLastName());
//            System.out.println(customer.getCountry());
//            System.out.println(customer.getPhone());
//        }
//        List<Product> p = productRepository.findByBuyPriceGreaterThan(price);
        Page <Product> p = productRepository.findAll(
                PageRequest.of(
                        1,
                        10,
                        Sort.by("buyPrice").ascending().and(Sort.by("productVendor").ascending().and(Sort.by("productName")
                        )
                        )
                        )
                );

        for (Product product : p) {
            System.out.println(product.getProductName());
            System.out.println(product.getBuyPrice());
        }
        return "index";
    }
}
