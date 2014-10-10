package jp.com.inotaku.web;
import jp.com.inotaku.domain.Sale;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sales")
@Controller
@RooWebScaffold(path = "sales", formBackingObject = Sale.class)
public class SaleController {
}
