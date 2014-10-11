package jp.com.inotaku.web;
import jp.com.inotaku.domain.SaleDetail;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/saledetails")
@Controller
@RooWebScaffold(path = "saledetails", formBackingObject = SaleDetail.class)
public class SaleDetailController {
}
